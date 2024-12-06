package com.zhang.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.pojo.Book;
import com.zhang.service.BookService;
import com.zhang.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.assertj.core.util.Sets.set;

/**
 * @author 张彦锋
 * @description 针对表【book】的数据库操作Service实现
 * @createDate 2024-11-27 16:16:46
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public Object getAllBook() {
        return bookMapper.selectList(null);
    }

    @Override
    public Object addBook(Book book) {
        return bookMapper.insert(book);
    }

    @Override
    public Object updateBook(Book book) {
        LambdaUpdateWrapper<Book> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Book::getId, book.getId())
                .set(Book::getName, book.getName())
                .set(Book::getAuthor, book.getAuthor())
                .set(Book::getPrice, book.getPrice())
                .set(Book::getIsbn, book.getIsbn())
                .set(Book::getPublishDate, book.getPublishDate());

        return bookMapper.update(null, lambdaUpdateWrapper);
    }

    @Override
    public Object deleteBook(int id) {
        return bookMapper.deleteById(id);
    }

    @Override
    public Object getBookById(int id) {
        return bookMapper.selectById(id);
    }

    @Override
    public Object getBookByPage(int pageNum, int pageSize) {
        IPage<Book> page = new Page<>(pageNum, pageSize);
        return bookMapper.selectPage(page, null);
    }


}




