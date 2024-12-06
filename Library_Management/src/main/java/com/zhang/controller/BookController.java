package com.zhang.controller;

import com.zhang.pojo.Book;
import com.zhang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author : 张彦锋
 * Date : 2024/11/27  16:21
 * Text :
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Gets all book.
     *
     * @return the all book
     */
    @GetMapping
    public Object getAllBook() {
        return bookService.getAllBook();
    }

    /**
     * Add book object.
     *
     * @param book the book
     * @return the object
     */
    @PostMapping
    public Object addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    /**
     * Update book object.
     *
     * @param book the book
     * @return the object
     */
    @PutMapping
    public Object updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    /**
     * Delete book object.
     *
     * @param id the id
     * @return the object
     */
    @DeleteMapping
    public Object deleteBook(@RequestParam int id) {
        return bookService.deleteBook(id);
    }

    /**
     * Gets book by id.
     *
     * @param id the id
     * @return the book by id
     */
    @GetMapping("/{id}")
    public Object getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    /**
     * Gets book by page.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the book by page
     */
    @GetMapping("{pageNum}/{pageSize}")
    public Object getBookByPage(@PathVariable int pageNum, @PathVariable int pageSize) {
        return bookService.getBookByPage(pageNum, pageSize);
    }

}
