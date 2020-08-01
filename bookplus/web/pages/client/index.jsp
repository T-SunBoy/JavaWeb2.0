<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp" %>
    <%-- 给购物车按钮添加点击事件 --%>
    <script type="text/javascript">
        $(function () {
            $("button.add_item").click(function () {
                var bookId = $(this).attr("bookId")
                //location.href = "http://localhost:8080/bookplus/cartServlet?action=addItem&id=" + bookId;
                //使用ajax请求局部改变页面对应内容
                $.getJSON("http://localhost:8080/bookplus/cartServlet", "action=addItemAjax&id=" + bookId, function (data) {
                    $("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品");
                    $("#cartLastName").text(data.lastName);
                });

            });

        })
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">网上书城</span>
    <div>
        <%--判断session中是否有登录成功的用户信息,为空显示登陆注册，不为空显示用户的信息--%>
        <c:if test="${empty sessionScope.userLogin}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.userLogin}">
            <span>欢迎<span class="um_span">${sessionScope.userLogin.username}</span>光临尚硅谷书城</span>
            <a href="pages/order/order.jsp">我的订单</a>
            <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
        </c:if>

        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/clientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询"/>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span id="cartTotalCount"> </span>
                <div>
                    <span style="color: red" id="cartLastName">亲，您的购物车暂时没有任何宝贝哦!</span>
                </div>
            </c:if>

            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red" id="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>

        </div>
        <%--分页的开始,遍历数据，显示--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="add_item">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>
        <%--分页的结束--%>

    </div>

    <%--分页条的开始--%>
    <%-- 使用静态包含方法，去使用分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>
    <%--分页条的结束--%>

</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>