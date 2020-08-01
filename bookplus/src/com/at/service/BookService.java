package com.at.service;

import com.at.bean.Book;
import com.at.bean.Page;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:数据库数据的服务（SERVICE）层
 * @date 2020-07-20 17:52
 */
public interface BookService {
    /**
     * Description:添加图书
     *
     * @param book
     * @return void
     * @Author tao
     * @Date 17:53 2020/7/20
     **/

    public void addBook(Book book);

    /**
     * Description:通过id删除指定的图书
     *
     * @param id
     * @return java.lang.Integer
     * @Author tao
     * @Date 17:54 2020/7/20
     **/

    public void deleteBookById(Integer id);

    /**
     * Description:修改图书信息
     *
     * @param book
     * @return java.lang.Integer
     * @Author tao
     * @Date 17:55 2020/7/20
     **/

    public void updateBook(Book book);

    /**
     * Description:通过指定的id查询图书信息
     *
     * @param id
     * @return com.at.bean.Book
     * @Author tao
     * @Date 17:56 2020/7/20
     **/

    public Book queryBookById(Integer id);

    /**
     * Description:查询所有图书信息
     *
     * @param
     * @return java.util.List<com.at.bean.Book>
     * @Author tao
     * @Date 17:57 2020/7/20
     **/

    public List<Book> queryBooks();

    /**
     * Description:设置分页的各项数据（当前页，每页显示数据数量，总页数，当前页数据，数据总量）
     *
     * @param pageNow
     * @param pageSize
     * @return com.at.bean.Page
     * @Author tao
     * @Date 16:29 2020/7/22
     **/

    public Page page(Integer pageNow, Integer pageSize);
    /**
     * Description:获取区间内的所有商品的有关信息
     * @Author tao
     * @Date 18:15 2020/7/23
     * @param pageNow 当前页
     * @param pageSize 当前页显示的数据数量
     * @param min 最小区间
     * @param max 最大区间
     * @return com.at.bean.Page<com.at.bean.Book>  将获取的信息封装到页面模型对象中
     **/

    public Page<Book> pageQueryByPrice(Integer pageNow, Integer pageSize, Integer min, Integer max);
}
