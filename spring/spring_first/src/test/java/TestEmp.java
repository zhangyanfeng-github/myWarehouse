import com.zhang.Dept;
import com.zhang.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmp {

    @Test
    public void testEmp1() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Emp.xml");

        Emp emp = context.getBean("emp1", Emp.class);
        emp.work();
    }

    @Test
    public void testEmp2() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Emp.xml");

        Emp emp = context.getBean("emp2", Emp.class);
        emp.work();
    }

    @Test
    public void testEmp3() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Emp.xml");

        Emp emp = context.getBean("emp3", Emp.class);
        emp.work();
    }

    @Test
    public void testEmpArray() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Emp.xml");

        Emp emp = context.getBean("emp4", Emp.class);
        emp.work();
    }

    @Test
    public void testEmpList() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Emp.xml");

        Dept dept = context.getBean("dept5", Dept.class);
        dept.info();
    }
}
