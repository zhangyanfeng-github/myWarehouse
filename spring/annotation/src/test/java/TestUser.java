import com.zhang.spring.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    @Test
    public void testUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
