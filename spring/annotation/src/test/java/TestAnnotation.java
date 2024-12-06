import com.zhang.spring.config.SpringConfig;
import com.zhang.spring.resource.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnnotation {

    //测试全注解开发
    @Test
    public void testAnnotation(){
        //加载配置类，不需要加载XML配置文件
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController controller = context.getBean(UserController.class);
        controller.add();
    }
}
