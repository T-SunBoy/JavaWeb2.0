package com.at.filter;

import com.at.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author tao
 * @version 1.0
 * 描述:给所有的使用service调用方法的请求都使用事务性原理的方法去管理,一旦出现异常就回滚事务，并将异常抛给tomcat服务器器处理
 * @date 2020-07-29 17:49
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            //执行后续的访问，或下一个filter过滤器（如果不止一个过滤器）
            chain.doFilter(request, response);
            //使用数据库的dao操作未出现异常，提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            //一旦使用数据库的dao操作出现异常，此此时回滚事务，保证数据的安全性
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            //将异常抛给tomcat,让其展示友好的错误提示页面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
