package com.zhang.service;

import com.zhang.pojo.Book;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 张彦锋
* @description 针对表【book】的数据库操作Service
* @createDate 2024-11-27 16:16:46
*/
public interface BookService extends IService<Book> {

    Object getAllBook();

    Object addBook(Book book);

    Object updateBook(Book book);

    Object deleteBook(int id);

    Object getBookById(int id);

    Object getBookByPage(int pageNum, int pageSize);
}
