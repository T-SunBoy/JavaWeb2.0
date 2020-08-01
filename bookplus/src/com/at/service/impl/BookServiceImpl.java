package com.at.service.impl;

import com.at.bean.Book;
import com.at.bean.Page;
import com.at.dao.BookDao;
import com.at.dao.impl.BookDaoImpl;
import com.at.service.BookService;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:依赖于BookDao操作数据库
 * @date 2020-07-20 17:57
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page page(Integer pageNow, Integer pageSize) {
        Page<Book> page = new Page<>();

        //设置每页显示数量
        page.setPageSize(pageSize);

        //求出总共数据量
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总数据量
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        //可能遇见总数据数不能整除每页显示的数据数量
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页,
        page.setPageNow(pageNow);

        //求每页显示的数据、以及开始索引
        Integer begin = (page.getPageNow() - 1) * pageSize;
        List<Book> list = bookDao.queryForPageItems(begin, pageSize);
        //5、设置当前页数据
        page.setItems(list);

        return page;
    }

    @Override
    public Page<Book> pageQueryByPrice(Integer pageNow, Integer pageSize, Integer min, Integer max) {

        Page<Book> page = new Page<>();

        //设置每页显示数量
        page.setPageSize(pageSize);

        //求出总共数据量
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总数据量
        page.setPageTotalCount(pageTotalCount);

        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        //可能遇见总数据数不能整除每页显示的数据数量
        if (pageTotalCount % pageSize > 0) {
            pageTotal++;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //设置当前页,
        page.setPageNow(pageNow);

        //求每页显示的数据、以及开始索引
        Integer begin = (page.getPageNow() - 1) * pageSize;
        List<Book> list = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //5、设置当前页数据
        page.setItems(list);

        return page;
    }
}
