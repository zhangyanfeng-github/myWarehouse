import com.zhang.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestStudent {

    @Test
    public void testStudentMap() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Student.xml");
        Student student = context.getBean("student", Student.class);
        student.run();
    }

}
