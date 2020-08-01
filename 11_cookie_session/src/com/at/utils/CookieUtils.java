package com.at.utils;

import javax.servlet.http.Cookie;

/**
 * @author tao
 * @version 1.0
 * 描述: 获取cookie的工具类
 * @date 2020-07-24 14:16
 */
public class CookieUtils {
    /**
     * Description:获取cookies中指定键的value
     *
     * @param name    需要查找的指定的键
     * @param cookies 被查找的cookie数组
     * @return javax.servlet.http.Cookie
     * @Author tao
     * @Date 14:18 2020/7/24
     **/

    public static Cookie findCookie(String name, Cookie[] cookies) {
        if (name == null || cookies == null || cookies.length == 0) {
            //未找到
            return null;
        }
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                //返回找到对应键的cookie
                return cookie;
            }
        }
        return null;
    }
}
