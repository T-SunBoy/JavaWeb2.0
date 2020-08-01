package com.at.web;

import com.at.bean.Book;
import com.at.bean.Page;
import com.at.service.BookService;
import com.at.service.impl.BookServiceImpl;
import com.at.utils.WebBeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * 1、增删改使用重定向
 * 2、查询使用请求转发
 * @date 2020-07-20 21:18
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * Description:让数据的全部显示变成数据的分页
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 15:52 2020/7/22
     **/

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取分页的请求参数(当前页，和当前页显示数量,默认四条，和第一页)
        Integer pageNow = WebBeanUtils.parseInt(req.getParameter("pageNow"), 1);
        Integer pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.使用bookService调用方法page(pageNow,pageSize)
        Page<Book> page = bookService.page(pageNow, pageSize);
        //设置后台分页条的请求地址
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page", page);
        //4、请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //添加图书时，添加后默认跳转至最后一页，显示出最近添加的图书，设置添加后跳转的页,加1避免添加后总页码+1的错误显示
        //String pageNow = req.getParameter("pageNow");
        Integer pageNow = WebBeanUtils.parseInt(req.getParameter("pageNow"), 0);
        pageNow += 1;

        //1、获取请求的参数，并封装到对应的bean对象中
        Map<String, String[]> map = req.getParameterMap();
        Book book = WebBeanUtils.CopyParameter(map, new Book());
        //2、调用bookService.addBook()保存图书至数据库
        bookService.addBook(book);

        //3.跳到图书列表显示页面
        // 请求转发会发生重复提交的误操作，改用重定向
        // req.getRequestDispatcher("/manager/bookServlet?action=page");
        //    / 斜杠 如果被服务器解析，得到的地址是：http://ip:port/工程路径,然后映射到web
        //特殊情况： response.sendRediect(“/”); 把斜杠发送给浏览器解析。得到 http://ip:port/
        System.out.println("add pageNow"+pageNow);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNow=" + pageNow);
    }

    /**
     * Description:删除指定的图书
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 12:51 2020/7/21
     **/

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求的参数
        int id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        //2、使用bookService，删除指定id 的图书
        bookService.deleteBookById(id);
        //3、重定向返回图书列表界面
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNow="+req.getParameter("pageNow"));
    }

    /**
     * Description:修改指定的图书信息
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 15:08 2020/7/21
     **/

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        Book book = WebBeanUtils.CopyParameter(parameterMap, new Book());
        //2、使用bookservice.updateBookById()修改数据
        bookService.updateBook(book);
        //3、重定向到图书显页面,并再次查询数据库所有图书数据，并显示
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNow="+req.getParameter("pageNow"));

    }

    /**
     * Description:查询数据的图书的所有数据
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 21:22 2020/7/20
     **/

    protected void queryList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、查询所有图书信息
        List<Book> bookList = bookService.queryBooks();
        //2、保存图书信息在request域对象中
        req.setAttribute("books", bookList);
        //3、请求转发，跳到图书列表显示页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);

    }

    /**
     * Description:获取要修改的图书信息，回显到图书修改页面
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 14:06 2020/7/21
     **/

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取需要修改的请求图书编号
        int id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        //2、查询对应图书信息
        Book book = bookService.queryBookById(id);
        //3、封装到req域中
        req.setAttribute("book", book);
        //4、请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
}
