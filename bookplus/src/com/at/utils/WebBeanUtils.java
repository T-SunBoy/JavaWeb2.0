package com.at.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:使用工具类BeanUtils对对象注入属性
 * @date 2020-07-19 15:45
 */
public class WebBeanUtils {
    /**
     * Description:将map中对应的数据信息封装到对象中
     * @Author tao
     * @Date 16:27 2020/7/20
     * @param map 需要封装的信息
     * @param bean 需要被注入的对象
     * @return T
     **/

    public static <T> T CopyParameter(Map map,T bean){
        //获取参数，对指定对象注入相应属性
        try {
            System.out.println("注入属性前:"+bean);
            BeanUtils.populate(bean, map);
            System.out.println("注入属性后:"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    public static Integer parseInt(String s,Integer defaultValue){
        Integer anInt = defaultValue;
        try {
             anInt= Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return anInt;
    }
}
