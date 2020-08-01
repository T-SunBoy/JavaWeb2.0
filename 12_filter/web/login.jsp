<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/24
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="http://localhost:8080/12_filter/">
</head>
<body>
<%--在表单标签设置参数loginServlet?action=login,并不是请求参数,a标签，可以使用，或使用隐藏域--%>
<form action="loginServlet" method="get">
    <input type="hidden" name="action" value="login">
    <table>
        <tr>
            <td>用户名:<input type="text" name="username" value="${cookie.username.value}"></td>
        </tr>

        <tr>
            <td>密码:<input type="password" name="password" value="${cookie.password.value}"></td>
        </tr>
        <tr>
            <td>验证码:<input type="text" style="width: 80px;" name="code">
                <img src="kaptcha.jpg" alt="" style="width: 100px; height: 25px;">
            </td>
        </tr>
    </table>
    <input type="submit" value="登录">
</form>


</body>
</html>
