package com.zhang.spring6.xmltx.service;

import com.zhang.spring6.xmltx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    //购买图书，传入用户ID和图书ID

    @Override
    public void buyBook(Integer bookId, Integer userId) {
        //根据图书的ID查询图书价格
        Integer price = bookDao.getBookPriceByBookId(bookId);

        //更新图书表库存量 - 1
        bookDao.updateStock(bookId);

        //更新用户表用户余额 - 图书价格
        bookDao.updateUserBalance(userId, price);
    }
}
