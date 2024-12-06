package com.zhang.health;

import com.zhang.component.MyComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * Author : 张彦锋
 * Date : 2024/11/30  20:21
 * Text :
 */
@Component
public class MyComponentHealthIndicator extends AbstractHealthIndicator {

    @Autowired
    private MyComponent myComponent;

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {

        //自定义检查方法
        int check = myComponent.check();

        if (check == 1) {
            //存活
            builder.up()
                    .withDetail("code", "200")
                    .withDetail("msg", "活的很好")
                    .withDetail("data", "你好")
                    .build();
        } else {
            //下线
            builder.down()
                    .withDetail("code", "400")
                    .withDetail("msg", "走的安详")
                    .withDetail("data", "走了")
                    .build();
        }
    }
}
