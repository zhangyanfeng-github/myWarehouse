import com.zhang.Stu;
import com.zhang.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    @Test
    public void testUserAdd() {
        //加载Spring配置文件，进行对象的创建
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean.xml");

        //创建获取的对象
        User user = (User) context.getBean("user");
        System.out.println(user);
        Stu stu = (Stu) context.getBean("stu");
        System.out.println(stu);

        //使用对象调用的方法进行测试
        user.add();
        stu.add();

        //使用不同的方式来获取Bean
        //第一种：根据ID获取Bean
        User user1 = (User) context.getBean("user");
        System.out.println(user1);

        //第二种：根据类型获取Bean，getBean(Class<?extends Object>,requiredType)
        User user2 = context.getBean(User.class);
        System.out.println(user2);

        //第三种：根据ID和类型获取Bean，getBean(String name, Class<User> requiredType)
        User user3 = context.getBean("user", User.class);
        System.out.println(user3);
    }

}
