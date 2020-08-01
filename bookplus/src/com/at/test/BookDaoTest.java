package com.at.test;

import com.at.bean.Book;
import com.at.dao.BookDao;
import com.at.dao.impl.BookDaoImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-20 16:58
 */
public class BookDaoTest {
    private BookDao bookDao =new BookDaoImpl();

    @Test
    public void addBook() {
        int i = bookDao.addBook(new Book(null, "天龙八部", "金庸", new BigDecimal(18), 0, 1000, null));
        System.out.println(i);
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        int i = bookDao.updateBook(new Book(42, "天龙八部", "金庸", new BigDecimal(18), 100, 900, null));
        System.out.println(i);
    }

    @Test
    public void queryBookById() {

        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        books.forEach(System.out::println);
    }
    @Test
    public void queryForPageTotalCountByPrice(){
        Integer integer = bookDao.queryForPageTotalCountByPrice(10, 40);
        System.out.println(integer);

    }

    @Test
    public void queryForPageItemsByPrice(){
        List<Book> list = bookDao.queryForPageItemsByPrice(0, 4, 10, 40);
        list.forEach(System.out::println);

    }

}