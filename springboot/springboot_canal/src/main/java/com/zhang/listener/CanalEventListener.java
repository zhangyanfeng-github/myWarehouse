package com.zhang.listener;

import com.zhang.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Author : 张彦锋
 * Date : 2024/12/8  14:56
 * Text :
 */
@Component
@PropertySource(value = "classpath:application.properties", encoding = "utf-8")
public class CanalEventListener {

    @Autowired
    public RedisService redisService;

    public final Integer _60SECONDS = 60;

    //Canal服务的IP，因为和Redis在同一机器上，所以暂时使用Redis的主机号，也是虚拟机的主机号
    @Value("${spring.data.redis.host}")
    public String REDIS_IP_ADDR;

    @Value("${canal.listener.db}")
    public String LISTENER_DB;


    public void printEntry(List<CanalEntry.Entry> entrys) {
        for (CanalEntry.Entry entry : entrys) {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN ||
                    entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                continue;
            }

            CanalEntry.RowChange rowChage = null;
            try {
                //获取变更的row数据
                rowChage = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error,data:" + entry.toString(), e);
            }
            //获取变动类型
            CanalEntry.EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));

            for (CanalEntry.RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.INSERT) {
                    redisService.redisInsert(rowData.getAfterColumnsList());
                } else if (eventType == CanalEntry.EventType.DELETE) {
                    redisService.redisDelete(rowData.getBeforeColumnsList());
                } else {//EventType.UPDATE
                    redisService.redisUpdate(rowData.getAfterColumnsList());
                }
            }
        }
    }

    /**
     * PostConstruct：是JDK提供的，用于在依赖注入完成后执行初始化方法
     */
//    @PostConstruct
    public void run() {
        System.out.println("---------O(∩_∩)O哈哈~ CanalEventListener 中的 run方法-----------");

        //=================================
        // 创建链接canal服务端，Canal端口号默认11111。host是Canal服务的IP
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(REDIS_IP_ADDR,
                11111), "example", "", "");
        int batchSize = 1000;
        //空闲空转计数器
        int emptyCount = 0;
        System.out.println("---------------------canal init OK，开始监听mysql变化------");
        try {
            connector.connect();
            //connector.subscribe(".*\\..*");
            connector.subscribe(LISTENER_DB);
            connector.rollback();//回滚到未进行ack确认的地方，确保从最后一个未确认的位置开始获取数据。
            int totalEmptyCount = 10 * _60SECONDS;
            while (emptyCount < totalEmptyCount) {
                System.out.println("canal正在监听，1秒一次 ：" + UUID.randomUUID().toString());
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //计数器重新置零
                    emptyCount = 0;
                    printEntry(message.getEntries());
                }
                connector.ack(batchId); // 提交确认
                // connector.rollback(batchId); // 处理失败, 回滚数据
            }
            System.out.println("已经监听了" + totalEmptyCount + "秒，无任何消息，监听已结束......");
        } finally {
            connector.disconnect();
        }
    }
}
