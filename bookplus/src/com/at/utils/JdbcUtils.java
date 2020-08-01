package com.at.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Description:
 * ThreadLocal 的作用，它可以解决多线程的数据安全问题。
 * ThreadLocal 它可以给当前线程关联一个数据（可以是普通变量，可以是对象，也可以是数组，集合）
 * ThreadLocal 的特点：
 * 1、ThreadLocal 可以为当前线程关联一个数据。（它可以像 Map 一样存取数据，key 为当前线程）
 * 2、每一个 ThreadLocal 对象，只能为当前线程关联一个数据，如果要为当前线程关联多个数据，就需要使用多个 ThreadLocal 对象实例。
 * 3、每个 ThreadLocal 对象实例定义的时候，一般都是 static 类型
 * 4、ThreadLocal 中保存数据，在线程销毁后。会由 JVM 虚拟自动释放。
 * <p>
 * 使用 ThreadLocal 来确保所有 dao 操作都在同一个 Connection 连接对象中完成
 *
 * @Author tao
 * @Date 16:55 2020/7/29
 **/


public class JdbcUtils {
    //创建数据库连接池
    private static DruidDataSource dataSource;
    //使用 ThreadLocal 来确保所有 dao 操作都在同一个 Connection 连接对象中完成,每一个 ThreadLocal 对象，只能为当前线程关联一个数据
    private static ThreadLocal<Connection> threadLocalConn = new ThreadLocal<>();
    //使用静态代码块创建数据库连接池：避免创建多个数据库连接池

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 获取数据库连接池中的连接
     *
     * @return 如果返回null, 说明获取连接失败<br />有值就是获取连接成功
     */
    public static Connection getConnection() {
        //从ThreadLocal中获取连接，因为其只能为当前线程关联一个数据，所以线程获取的都是同一个连接，用来实现事务性原理的操作
        Connection conn = threadLocalConn.get();
        if (conn == null) {
            try {
                //当threadLocalConn中没有连接时，就从数据库连接池中获取一个
                conn = dataSource.getConnection();
                //将连接放到threadLocalConn中
                threadLocalConn.set(conn);
                //关闭事务的自动提交，让我们手动提交事务
                conn.setAutoCommit(false);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * Description:提交事务并关闭资源，此时连接的关闭由commitAndClose()和rollbackAndClose()来进行关闭
     *
     * @return void
     * @Author tao
     * @Date 16:18 2020/7/29
     **/

    public static void commitAndClose() {
        //获取threadlocalConn中的连接，保证使用的是同一个连接
        Connection conn = threadLocalConn.get();
        if (conn != null) {
            try {
                //提交事务
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        // 一定要执行 remove 操作，否则就会出错。（因为 Tomcat 服务器底层使用了线程池技术）
        //实现事务的回滚或者提交时，删除当前ThreadLocal中当前线程的关联数据.
        threadLocalConn.remove();

    }

    /**
     * Description:回滚事务并关闭资源
     *
     * @return void
     * @Author tao
     * @Date 16:21 2020/7/29
     **/

    public static void rollbackAndClose() {
        Connection conn = threadLocalConn.get();
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadLocalConn.remove();
    }

    /**
     * 关闭连接，放回数据库连接池,由于数据安全问题的原因，启用事务性原理去关闭连接，此方法淘汰
     *
     * @param conn
     */
    /*public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/

}
