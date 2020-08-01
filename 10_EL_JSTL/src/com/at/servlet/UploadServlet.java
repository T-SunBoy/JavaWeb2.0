package com.at.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:用来处理上传的数据,ServletFileUpload 类，用于解析上传的数据。 FileItem 类，表示每一个表单项。
 * @date 2020-07-17 18:49
 */
public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("文件上传了");

        //1.先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传）
        if (ServletFileUpload.isMultipartContent(req)) {
            //创建 FileItemFactory 工厂实现类
            FileItemFactory fileFactory = new DiskFileItemFactory();
            // 创建用于解析上传数据的工具类 ServletFileUpload 类
            ServletFileUpload fileUpload = new ServletFileUpload(fileFactory);
            try {
                // 解析上传的数据，得到每一个表单项 FileItem
                List<FileItem> list = fileUpload.parseRequest(req);
                // 循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    //判断数据是普通的表单项还是上传的文件
                    if (fileItem.isFormField()) {
                        //是普通表单项

                        //获取name属性值
                        String fieldName = fileItem.getFieldName();
                        System.out.println("表单项name属性值：" + fieldName);
                        //防止乱码,获取表单项的值
                        String stringValue = fileItem.getString("utf-8");
                        System.out.println("表单项value属性值：" + stringValue);
                    } else {
                        //是上传的数据

                        String fieldName = fileItem.getFieldName();
                        System.out.println("表单项name属性值：" + fieldName);
                        //获取上传的文件名
                        String stringValue = fileItem.getName();
                        System.out.println("上传的文件名：" + stringValue);
                        //将上传的数据写入磁盘
                        fileItem.write(new File("e:\\", fileItem.getName()));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


       /* byte[] bytes = new byte[1024000];
        ServletInputStream inputStream = req.getInputStream();
        int read = inputStream.read(bytes);
        System.out.println(new String(bytes,0,read));*/
    }
}
