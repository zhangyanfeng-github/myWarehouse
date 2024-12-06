package com.zhang.spring6.xmltx.controller;

import com.zhang.spring6.xmltx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //购买图书，传入用户ID和图书ID
    public void buyBook(Integer bookId, Integer userId) {
        //传给业务逻辑层
        bookService.buyBook(bookId, userId);
    }
}
