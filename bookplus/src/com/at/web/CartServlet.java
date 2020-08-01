package com.at.web;

import com.at.bean.Book;
import com.at.bean.Cart;
import com.at.bean.CartItem;
import com.at.service.BookService;
import com.at.service.impl.BookServiceImpl;
import com.at.utils.WebBeanUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-26 14:33
 */
public class CartServlet extends BaseServlet {


    private BookService bookService = new BookServiceImpl();

    /**
     * Description:向购物车添加商品
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 14:33 2020/7/26
     **/
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("添加商品");
        //获取请求参数---需要添加到购物车的商品id
        Integer bookId = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        //查询对应id的图书信息
        Book book = bookService.queryBookById(bookId);
        //将图书信息转换为CartItem商品项,使用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        if (cart == null) {
            //我们将购物车的信息放在session中
            //为null表示，此时并没有购物车，编避免每次都new一个cart
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //将最后一个添加的商品的名字为lastName，用于首页购物车信息的显示
        req.getSession().setAttribute("lastName",cartItem.getName());

        //重定向到原来商品所在的地址页面
        //http协议中有一个请求头Referer,它可以把请求发起时的浏览器地址栏地址发送给服务器
        //resp.sendRedirect(req.getContextPath());
        resp.sendRedirect(req.getHeader("referer"));

    }

    /**
     * Description:删除车中指定的商品
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 17:44 2020/7/26
     **/

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取需要删除的商品id
        Integer id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        System.out.println("删除的商品id" + id);
        //通过session获取cart(购物车对象)
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //调用cart.deleteItem(id)方法,删除指定的cartItem
            cart.deleteItem(id);
            //重定向到购物车
            resp.sendRedirect(req.getHeader("referer"));
        }
    }

    /**
     * Description:清空购物车
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 18:53 2020/7/26
     **/

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取要清空的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //清空
            cart.clear();
            //重定向到当前页面
            resp.sendRedirect(req.getHeader("referer"));
        }

    }

    /**
     * Description:修改购物车中的单件商品数量
     *
     * @param req
     * @param resp
     * @return void
     * @Author tao
     * @Date 22:17 2020/7/26
     **/

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数---被修改商品的id
        Integer id = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        //被修改商品的修改数量
        Integer count = WebBeanUtils.parseInt(req.getParameter("count"), 1);
        //获取session中设置的cart属性
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //修改商品数量
            cart.updateCount(id, count);
            //重定向到购物车
            resp.sendRedirect(req.getHeader("referer"));
        }
    }
    /**
     * Description:使用ajax的请求的方式，向购物车添加商品
     * @Author tao
     * @Date 21:41 2020/7/30
     * @param req
     * @param resp
     * @return void
     **/


    protected void addItemAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("添加商品");
        //获取请求参数---需要添加到购物车的商品id
        Integer bookId = WebBeanUtils.parseInt(req.getParameter("id"), 0);
        //查询对应id的图书信息
        Book book = bookService.queryBookById(bookId);
        //将图书信息转换为CartItem商品项,使用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        if (cart == null) {
            //为null表示，此时并没有购物车，编避免每次都new一个cart
            cart = new Cart();
            //将购物车的信息放在session中
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        //将最后一个添加的商品的名字为lastName，用于首页购物车信息的显示
        req.getSession().setAttribute("lastName",cartItem.getName());


        //重定向到原来商品所在的地址页面
        //http协议中有一个请求头Referer,它可以把请求发起时的浏览器地址栏地址发送给服务器
        //resp.sendRedirect(req.getContextPath());
        //resp.sendRedirect(req.getHeader("referer"));

        //使用ajax请求后，局部改变，不在需要整个页面更新（重定向）
        //将需要返回的信息，以map形式封装
        Map<String,Object> mapJson = new HashMap<>();
        //最后添加的商品名称
        mapJson.put("lastName",cartItem.getName());
        //购物车所有商品的数量
        mapJson.put("totalCount",cart.getTotalCount());
        //将map封装为json字符串
        Gson gson = new Gson();
        String mapJsonString = gson.toJson(mapJson);
        //将json字符串发给客户端
        resp.getWriter().write(mapJsonString);

    }
}
