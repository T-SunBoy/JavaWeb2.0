<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/16
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>通过el的隐藏内置对象获取jsp的内置对象，获取信息</title>
</head>
<body>
el中的隐藏对象pagecontext对象可以获取jsp中的九大内置对象
<%=request.getScheme()%>
小技巧在jsp的代码脚本中声明一个request
<%
    pageContext.setAttribute("req", request);

%>
<br/>
1. 协议： ${pageContext.request.scheme} <br/>
1. 协议： ${req.scheme} <br/>
2. 服务器 ip： ${pageContext.request.serverName} <br/>
3. 服务器端口： ${pageContext.request.serverPort} <br/>
4. 获取工程路径： ${pageContext.request.contextPath} <br/>
5. 获取请求方法： ${pageContext.request.method} <br/>
6. 获取客户端 ip 地址：${pageContext.request.remoteHost}<br/>
7. 获取会话的 id 编号：${pageContext.session.id}<br/>
</body>
</html>
