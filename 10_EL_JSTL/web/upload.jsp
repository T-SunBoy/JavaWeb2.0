  <%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/17
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://localhost:8080/09_EL_JSTL/uploadServlet" method="post" enctype="multipart/form-data">
    username:<input type="text" name="username">
    选择上传文件:<input type="file" name="photo">
    上传:<input type="submit" value="上传">
</form>

</body>
</html>
