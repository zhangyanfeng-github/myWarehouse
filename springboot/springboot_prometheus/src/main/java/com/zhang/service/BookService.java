package com.zhang.service;

import com.zhang.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 张彦锋
 * @description 针对表【book】的数据库操作Service
 * @createDate 2024-12-01 20:00:14
 */
public interface BookService extends IService<Book> {

    Object getBook();

    Object addBook(Book book);

    Object updateBook(Book book);

    Object deleteBook(Integer id);

    Object getBookById(Integer id);

    Object getBookPage(Integer pageNum, Integer pageSize);
}
