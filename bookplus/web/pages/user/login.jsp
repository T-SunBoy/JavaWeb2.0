<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>尚硅谷会员登录页面</title>
    <!--写base标签，永远固定相对路径跳转的结果-->
    <%--<base href="http://localhost:8080/bookplus/">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>

    <%--	静态包含base标签、css样式、jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>尚硅谷会员</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                                    <%--<%=request.getAttribute("msg") == null ? "请输入用户名和密码" : request.getAttribute("msg")%>--%>
                                    <%--使用el表达式代替jsp的表达式脚本--%>
                        ${empty requestScope.msg ?"请输入用户名和密码" :requestScope.msg}
								</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <label>用户名称：</label>
                        <input type="hidden" name="action" value="login">
                        <%--<%=request.getAttribute("username")==null?"":request.getAttribute("username")
                        登录失败，获取username的值，如果为空串，提示用户输入(请输入用户名或密码)，如果不为空，回显username的值
                        --%>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username"
                            <%--value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
                            <%--使用el表达式代替jsp的表达式脚本--%>
                            <%-- EL 表达式在输出 null 值的时候，输出的是空串。jsp 表达式脚本输出 null 值的时候，输出的是 null 字符串。--%>
                            value="${requestScope.username}"/>

                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%--<div id="bottom">
    <span>
        尚硅谷书城.Copyright &copy;2015
    </span>
</div>--%>
<%--使用静态包含--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>