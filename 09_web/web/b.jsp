<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/14
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>这是b的jsp</h1>
pageContext 域是否有值：<%=pageContext.getAttribute("key")%><br/>
request 域是否有值：<%=request.getAttribute("key")%><br/>
session 域是否有值：<%=session.getAttribute("key")%><br/>
application 域是否有值：<%=application.getAttribute("key")%><br/>
<%--jsp的静态包含--%>
<%@include file="c.jsp" %>
<h1 align="center"> 九九乘法口诀表</h1>
<br/>
<table align="center" border="">
    <%
        for (int i = 1; i <= 9; i++) {
    %>
    <tr>
        <%
            for (int j = 1; j <= i; j++) {
        %>
        <td>
            <%=i + "*" + j + "=" + (i * j)%>
        </td>
        <% }
        %>
    </tr>


    <%
        }
    %>
</table>
</body>
</html>
