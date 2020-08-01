<%@ page import="java.util.ArrayList" %>
<%@ page import="com.at.el.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/17
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>使用jstl标签代替jsp 的代码脚本</title>
    <style type="text/css">
        table{
            width: 500px;
            border: 1px solid red;
            border-collapse: collapse;
        }
        th , td{
            border: 1px solid red;
            align-content: center;
            text-align: center;
        }
    </style>
</head>
<body>
<%
    request.setAttribute("key2", "key2Value");
%>
<%--c:set很少使用--%>
<c:set scope="request" var="key1" value="key1Value"></c:set><br/>
${requestScope.key2}

<c:if test="${12 == 12}">
   <h1>12等于12</h1>
</c:if>

<%--遍历1到10--%>
<c:forEach begin="1" end="100" var="i">
    ${i}
</c:forEach>

<%--遍历数组--%>
<%
    request.setAttribute("arrays", new String[]{"21121","6561651","6546546"});
%>
<hr/>

<c:forEach items="${ requestScope.arrays }" var="item">
    ${ item }<br/>
</c:forEach>


<%
    ArrayList<Object> list = new ArrayList<>();
    for (int i = 0; i <10 ; i++) {
        list.add(new Student("name1"+i,i,"100"+i));
    }
    request.setAttribute("student", list);

%>

<%--遍历关于对象的list--%>

<table>
    <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>电话</th>
        <th>varStatus</th>
    </tr>
    <c:forEach items="${requestScope.student}" var="stu" begin="3" end="7" varStatus="status" step="2">
        <tr>
            <td>${stu.name}</td>
            <td>${stu.age}</td>
            <td>${stu.phone}</td>
            <td>${status}</td>
        </tr>
    </c:forEach>

</table>


</body>
</html>
