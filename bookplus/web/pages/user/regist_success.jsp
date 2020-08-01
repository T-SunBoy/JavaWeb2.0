<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%--<!--写base标签，永远固定相对路径跳转的结果-->
	<base href="http://localhost:8080/bookplus/">--%>
<%--	静态包含base标签、css样式、jquery文件--%>
	<%@include file="/pages/common/head.jsp"%>

<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/logo.gif" >
				<span class="wel_word"></span>
				<%--<div>
					<span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
					<a href="../order/order.jsp">我的订单</a>
					<a href="../../index.jsp">注销</a>&nbsp;&nbsp;
					<a href="../../index.jsp">返回</a>
				</div>--%>
			<%--使用包含--%>
			<%@include file="/pages/common/login_success_menu.jsp" %>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="../../index.jsp">转到主页</a></h1>
	
		</div>
		
		<%--<div id="bottom">
			<span>
				尚硅谷书城.Copyright &copy;2015
			</span>
		</div>--%>
<%--使用静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>