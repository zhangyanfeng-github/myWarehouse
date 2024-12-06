import com.zhang.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBook {

    @Test
    public void testBook() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Book.xml");

        Book book = context.getBean("book", Book.class);
        System.out.println(book);
    }

    @Test
    public void testConstructor() {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("Bean_Book.xml");

        Book book = context.getBean("bookCon", Book.class);
        System.out.println(book);
    }
}
