<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/18
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取--%>
<%
    String basePath = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";
    pageContext.setAttribute("basePath", basePath);
%>
<%--<%=basePath%>--%>
<!--写base标签，永远固定相对路径跳转的结果-->
<%--<base href="http://localhost:8080/bookplus/">--%>
<%--<base href="<%=basePath%>">--%>
<base href="${basePath}">

<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
