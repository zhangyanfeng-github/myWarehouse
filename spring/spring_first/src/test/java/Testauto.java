import com.zhang.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testauto {

    @Test
    public void testAuto(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean_auto.xml");
        UserController controller = context.getBean("userController", UserController.class);
        controller.addUser();
    }
}
