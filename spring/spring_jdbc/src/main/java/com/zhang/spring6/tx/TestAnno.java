package com.zhang.spring6.tx;

import com.zhang.spring6.tx.config.SpringConfig;
import com.zhang.spring6.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAnno {

    @Test
    public void testTxAllAnnotation() {
        ApplicationContext applicationContext = new
                AnnotationConfigApplicationContext(SpringConfig.class);
        BookController accountService = applicationContext.getBean("bookController",
                BookController.class);
        accountService.buyBook(1, 1);
    }
}
