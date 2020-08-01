<%@ page import="com.at.el.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/16
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用el表达式代替jsp中的表达式脚本</title>
</head>
<body>

<%

    String name = "刘德华";
    String[] phone = new String[]{"1564545","15461464+","564654164"};
    ArrayList<Object> list = new ArrayList<>();
    list.add("北京");
    list.add("上海");
    list.add("深圳");
    HashMap<Object, Object> map = new HashMap<>();
    map.put("key1", "value1");
    map.put("key2", "value2");
    map.put("key3", "value3");

    Person person = new Person(name,phone,list,map);
    pageContext.setAttribute("person", person);


%>
使用el表达式输出javabean中的person：${person};<br/>
使用el表达式输出javabean中的person：${person.name};<br/>
使用el表达式输出javabean中的person：${person.city};<br/>
使用el表达式输出javabean中的person：${person.city[1]};<br/>
使用el表达式输出javabean中的person：${person.map.key1};<br/>

${12 == 12}

</body>
</html>
