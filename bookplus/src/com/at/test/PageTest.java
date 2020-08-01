package com.at.test;

import com.at.bean.Book;
import com.at.dao.BookDao;
import com.at.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-22 16:52
 */
public class PageTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void queryForPageTotalCount(){
        Integer integer = bookDao.queryForPageTotalCount();
        System.out.println(integer);

    }
    @Test
    public void queryForPageItems(){
        List<Book> list = bookDao.queryForPageItems(0, 4);
        list.forEach(System.out::println);

    }
}
