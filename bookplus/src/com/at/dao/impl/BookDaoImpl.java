package com.at.dao.impl;

import com.at.bean.Book;
import com.at.bean.Page;
import com.at.dao.BookDao;

import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-20 16:17
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path` ) VALUES(?,?,?,?,?,?)";
        int update = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());

        return update;
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql ="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
        return update(sql,book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book ";
        return queryForList(Book.class,sql  );
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(Integer begin, Integer pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        List<Book> list = queryForList(Book.class, sql, begin, pageSize);
        return list;
    }

    @Override
    public Integer queryForPageTotalCountByPrice(Integer min, Integer max) {
        String sql = "select count(*) from t_book where `price` between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(Integer begin, Integer pageSize, Integer min, Integer max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where `price` between ? and ? order by `price` limit ?,?";
        List<Book> list = queryForList(Book.class, sql, min, max, begin, pageSize);
        return list;
    }
}
