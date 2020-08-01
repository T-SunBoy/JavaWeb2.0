package com.at.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-18 11:04
 */
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取下载的文件名(暂时写死)
        String downloadName = "2.jpg";
        //使用servletContext对象读取需要下载的内容
        ServletContext servletContext = getServletContext();
        //获取要下载的类型
        String mimeType = servletContext.getMimeType("/file/" + downloadName);
        //在回传前，通过响应头告诉客户端返回的数据类型，利用响应头告诉客户端返回的类型
        resp.setContentType(mimeType);
        //还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
        // Content-Disposition 响应头，表示收到的数据怎么处理
        // attachment 表示附件，表示下载使用
        // filename= 表示指定下载的文件名(可以自定义)
        //判断用的是啥浏览器
        if (req.getHeader("user-agent").contains("Firefox")) {
            //如果使用的是火狐，则用此种编码方式解决中文名称问题
            // 使用下面的格式进行 BASE64 编码后
            String str = "attachment; fileName=" + "=?utf-8?B?" +
                    new BASE64Encoder().encode("中文.jpg".getBytes("utf-8")) + "?=";
            //设置到响应头中
            resp.setHeader("Content-Disposition", str);
        } else {
            //解决谷歌和ie浏览器
            // 把中文名进行 UTF-8 编码操作。
            String str = "attachment; fileName=" + URLEncoder.encode("中文.jpg", "UTF-8");
            // 然后把编码后的字符串设置到响应头中
            resp.setHeader("Content-Disposition", str);
        }


        //读取下载的内容，以输入流的形式返回
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/2.jpg");
        //获取响应输出流
        ServletOutputStream outputStream = resp.getOutputStream();
        //将输入流读取的数据copy到响应的输出流中
        IOUtils.copy(resourceAsStream, outputStream);
    }
}
