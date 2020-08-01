package com.at.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author tao
 * @version 1.0
 * 描述: 下载
 * @date 2020-07-17 21:11
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取要下载的文件名
        String downloadName = "2.jpg";

        //使用servletContext对象读取需要下载的内容
        ServletContext servletContext = getServletContext();

        //获取要下载的类型
        String mimeType = servletContext.getMimeType("/file/" + downloadName);
        System.out.println("下载的类型:" + mimeType);

        //在回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);

        //还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
        // Content-Disposition 响应头，表示收到的数据怎么处理
        // attachment 表示附件，表示下载使用
        // filename= 表示指定下载的文件名
        resp.setHeader("Content-Disposition", "attachment; filename=" + downloadName);//下载内容的核心

        //读取下载的内容，以输入流的形式返回
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadName);

        //获取响应的输出流
        OutputStream outputStream = resp.getOutputStream();

        //将输入流读取的数据复制到输出流中
        IOUtils.copy(resourceAsStream, outputStream);
    }
}
