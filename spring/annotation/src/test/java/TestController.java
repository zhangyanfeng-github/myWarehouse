import com.zhang.spring.config.SpringConfig;
import com.zhang.spring.resource.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestController {

    @Test
    public void testController() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        UserController controller = context.getBean(UserController.class);
        controller.add();
    }

}
