package com.zhang.controller;

import com.zhang.pojo.Book;
import com.zhang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : 张彦锋
 * Date : 2024/12/1  20:09
 * Text :
 */
@RequestMapping("book")
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public Object getBook() {
        return bookService.getBook();
    }

    @PostMapping
    public Object addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping
    public Object updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping
    public Object deleteBook(@RequestParam Integer id) {
        return bookService.deleteBook(id);
    }

    @GetMapping("{id}")
    public Object getBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }

    @GetMapping("{pageNum}/{pageSize}")
    public Object getBookPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize) {
        return bookService.getBookPage(pageNum,pageSize);
    }
}
