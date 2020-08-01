package com.at.el;

import javax.servlet.http.HttpServlet;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author tao
 * @version 1.0
 * 描述:
 * @date 2020-07-16 17:15
 */
public class Person extends HttpServlet {
    private String name;
    private String[] phone;
    private List city;
    private Map map;

    public Person() {
    }

    public Person(String name, String[] phone, List city, Map map) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhone() {
        return phone;
    }

    public void setPhone(String[] phone) {
        this.phone = phone;
    }

    public List getCity() {
        return city;
    }

    public void setCity(List city) {
        this.city = city;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone=" + Arrays.toString(phone) +
                ", city=" + city +
                ", map=" + map +
                '}';
    }
}
