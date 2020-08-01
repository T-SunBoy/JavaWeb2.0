package com.at.test;

import java.lang.reflect.Method;

/**
 * @author tao
 * @version 1.0
 * 描述: 测试使用反射调用方法
 * @date 2020-07-19 13:52
 */
public class UserServletTest {
    public void login(){
        System.out.println("login方法被调用了");
    }
    public void regist(){
        System.out.println("regist方法被调用了");
    }
    public void updateUser(){
        System.out.println("updateUser方法被调用了");
    }
    public void updatePassword(){
        System.out.println("updatePassword方法被调用了");
    }
    public static void main(String[] args) {
        String action = "updateUser";
        try {
            //得到需要调用的方法
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);
            System.out.println(declaredMethod);
            //调用该方法
            declaredMethod.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
