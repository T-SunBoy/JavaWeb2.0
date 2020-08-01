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

<h1>这是a的jsp</h1>
<% pageContext.setAttribute("key", "pageContext");%>
<% request.setAttribute("key", "request"); %>
<% session.setAttribute("key", "session");%>
<% application.setAttribute("key", "application");%>

pageContext 域是否有值：<%=pageContext.getAttribute("key")%>
<br/>
request 域是否有值：<%=request.getAttribute("key")%><br/>
session 域是否有值：<%=session.getAttribute("key")%><br/>
application 域是否有值：<%=application.getAttribute("key")%><br/>

<%
    //request.getRequestDispatcher("/b.jsp").forward(request, response);

%>
<%--jsp的的标签请求转发--%>
<jsp:forward page="/b.jsp"></jsp:forward>
<%
    for (int i = 1; i <=9; i++) {
        for (int j =1;j<=i;j++){
            System.out.print(i+"*"+j+"="+i*j);
        }
        System.out.println();
        
    }
%>
</body>
</html>
