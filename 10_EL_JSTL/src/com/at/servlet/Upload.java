package com.at.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author tao
 * @version 1.0
 * 描述:简单地复习上传与下载代码编写
 * @date 2020-07-18 10:39
 */
public class Upload extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //判断上传的数据是否是以多段的形式
        if(ServletFileUpload.isMultipartContent(req)){
            FileItemFactory fileFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fileFactory);
            try {
                //解析数据，获取每一个表单项
                List<FileItem> list = upload.parseRequest(req);
                for(FileItem fileItem : list){
                    if(fileItem.isFormField()){
                        //是普通的表单项内容
                        System.out.println("表单向name的值:"+fileItem.getFieldName() );
                        System.out.println("表单向value的值:"+fileItem.getString("utf-8"));
                    }else {
                        //是上传的数据内容
                        System.out.println("表单项name的值"+fileItem.getFieldName());
                        System.out.println("上传的文件名:"+fileItem.getName());
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }

    }
}
