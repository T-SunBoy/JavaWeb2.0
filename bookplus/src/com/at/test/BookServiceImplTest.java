package com.at.test;

import com.at.bean.Book;
import com.at.bean.Page;
import com.at.service.BookService;
import com.at.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-20 18:08
 */
public class BookServiceImplTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "射雕", "金庸", new BigDecimal(200), 100, 999, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(43);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "射雕英雄传", "金庸", new BigDecimal(18), 100, 900, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(20);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void page(){
        Page page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }

    @Test
    public void pageQueryByPrice(){
        Page<Book> bookPage = bookService.pageQueryByPrice(0, 4, 10, 50);
        System.out.println(bookPage);

    }
}