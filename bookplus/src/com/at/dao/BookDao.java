package com.at.dao;

import com.at.bean.Book;
import com.at.bean.Page;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:图书管理的数据操作
 * @date 2020-07-20 16:10
 */
public interface BookDao {
    /**
     * Description:向数据库中添加图书
     *
     * @param book 需要添加的图书
     * @return int 返回-1表示失败，其余表示sql影响的行数
     * @Author tao
     * @Date 16:13 2020/7/20
     **/

    public int addBook(Book book);

    /**
     * Description:删除图书信息
     *
     * @param id 需要删除的图书id
     * @return int 返回影响的行数，-1表示删除失败
     * @Author tao
     * @Date 16:15 2020/7/20
     **/

    public int deleteBookById(Integer id);

    /**
     * Description:修改图书的信息
     *
     * @param book 需要修改的图书
     * @return com.at.bean.Book 返回修改成功后对应的图书信息
     * @Author tao
     * @Date 16:16 2020/7/20
     **/


    public int updateBook(Book book);

    /**
     * Description:根据图书id查询图书信息
     *
     * @param id 被查询图书的id
     * @return com.at.bean.Book
     * @Author tao
     * @Date 16:36 2020/7/20
     **/

    public Book queryBookById(Integer id);

    /**
     * Description:批量查询图书信息
     *
     * @param
     * @return java.util.List<com.at.bean.Book>
     * @Author tao
     * @Date 16:37 2020/7/20
     **/

    public List<Book> queryBooks();

    /**
     * Description:查询数据总记录数
     *
     * @param
     * @return java.lang.Integer
     * @Author tao
     * @Date 16:44 2020/7/22
     **/


    public Integer queryForPageTotalCount();

    /**
     * Description:查询每页的显示数据
     *
     * @param begin    每页显示数据的索引
     * @param pageSize 每页显示的条数
     * @return java.util.List<com.at.bean.Book>
     * @Author tao
     * @Date 16:45 2020/7/22
     **/

    public List<Book> queryForPageItems(Integer begin, Integer pageSize);

    /**
     * Description:查询价格区间内的所有商品的总记录数
     *
     * @param min
     * @param max
     * @return java.lang.Integer
     * @Author tao
     * @Date 18:27 2020/7/23
     **/

    public Integer queryForPageTotalCountByPrice(Integer min, Integer max);


    /**
     * Description:查询价格区间内的所有商品的信息
     * @Author tao
     * @Date 18:30 2020/7/23
     * @param begin 开始索引
     * @param pageSize 显示的数据量
     * @return java.util.List<com.at.bean.Book>
     **/
    public  List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max);

}
