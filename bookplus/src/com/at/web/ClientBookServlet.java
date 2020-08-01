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

/**
 * @author tao
 * @version 1.0
 * 描述:前台的Servlet程序,处理首页的分页问题
 * @date 2020-07-23 12:17
 */
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();


    /**
     * Description:前台显示数据的分页处理
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 17:48 2020/7/23
     **/

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取分页的请求参数(当前页，和当前页显示数量,默认四条，和第一页)
        Integer pageNow = WebBeanUtils.parseInt(req.getParameter("pageNow"), 1);
        Integer pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.使用bookService调用方法page(pageNow,pageSize)
        Page<Book> page = bookService.page(pageNow, pageSize);
        System.out.println("当前页码:" + page.getPageNow());
        //设置前台分页条的请求地址
        page.setUrl("client/clientBookServlet?action=page");
        //3、保存page对象到request域中
        req.setAttribute("page", page);
        //4、请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }

    /**
     * Description:处理价格区间的分页
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 17:55 2020/7/23
     **/

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        Integer pageNow = WebBeanUtils.parseInt(req.getParameter("pageNow"), 1);
        Integer pageSize = WebBeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Integer min = WebBeanUtils.parseInt(req.getParameter("min"), 0);
        Integer max = WebBeanUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //获取page页面模型对象
        Page<Book> page = bookService.pageQueryByPrice(pageNow, pageSize, min, max);

        //设置前台分页条的请求地址
        //解决搜索区间后，点击其他页时的数据问题
        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3、保存page对象到request域中
        req.setAttribute("page", page);
        System.out.println("pageByPrice。。。。。。。。");
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);

    }
}
