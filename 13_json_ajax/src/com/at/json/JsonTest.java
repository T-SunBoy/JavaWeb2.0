package com.at.json;

import com.at.bean.Person;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author tao
 * @version 1.0
 * 描述:测试对象类型与json的互转
 * @date 2020-07-30 13:15
 */
public class JsonTest {
    @Test
    public void testPerson() {
        Person person = new Person(1, "刘备");
        //将person对象转为json字符串
        Gson gson = new Gson();
        String personJson = gson.toJson(person);
        System.out.println(personJson);
        //将json字符串在转换为person对象
        Person p = gson.fromJson(personJson, Person.class);
        System.out.println(p);
    }

    @Test
    public void testPersonList() {
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(new Person(1, "刘备"));
        personArrayList.add(new Person(2, "张飞"));
        //将集合转为json字符串
        Gson gson = new Gson();
        String personArrayListJson = gson.toJson(personArrayList);
        System.out.println(personArrayListJson);

        //将json字符串转为集合
        List<Person> list = gson.fromJson(personArrayListJson, new TypeToken<ArrayList<Person>>(){}.getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);
    }
    @Test
    public void testPersonMap(){
        Map<Integer,Person> map = new ConcurrentHashMap<>();
        map.put(1,new Person(1,"刘备"));
        map.put(2,new Person(2,"关羽"));
        //将map集合转为json字符串
        Gson gson = new Gson();
        String personMapJsonString = gson.toJson(map);
        System.out.println(personMapJsonString);
        //将json字符串转为map集合
        Map map1 = gson.fromJson(personMapJsonString, new TypeToken<Map<Integer,Person>>(){}.getType());
        System.out.println(map1.get(1));
        System.out.println(map1);
    }
}
