<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--<base href="http://localhost:8080/BookStore02/">
    <link type="text/css" rel="stylesheet" href="static/css/style.css">--%>

    <%--	静态包含base标签、css样式、jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            $("a.delete_cartItem").click(function () {
                /**
                 * 在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的document对象。
                 * confirm是确认提示框函数
                 * 参数是提示内容
                 * 有俩个按钮，确认，取消
                 * 返回true表示点击了确认，返回false点击了取消
                 */
                return confirm("你确定删除【" + $(this).parent().parent().find("td:first").text() + "】吗?");
                // return false;返回false表示点击取消，阻止元素的默认行为，不提交请求；
            });

            //给清空购物车绑定单击确定事件
            $("#clearCart").click(function () {
                return confirm("你确定要清空购物车吗?");
            });

            //给商品项数量绑上改变内容响应事件
            $("input.updateCount").change(function () {
                var name = $(this).parent().parent().find("td:first").text();
                var count = $(this).attr("value");
                var id = $(this).attr("bookId");

                if (confirm("你确定要修改商品【" + name + "】的数量为【" + count + "】吗?")) {
                    //点击了是，返回id给服务器修改
                    location.href = "http://localhost:8080/bookplus/cartServlet?action=updateCount&id=" + id + "&count=" + count;

                } else {
                    //点击了否，数量回复为原来的值
                    //defaultValue属性是表单项dom对象属性，表示默认的value值，既是未改变前的值被保存为默认
                    this.value = this.defaultValue;

                }

            });

            //给结账绑定单击确认时间，避免误操作
            $("#creatOrder").click(function () {
                return confirm("你确定要结算你的购物车所有商品吗?");
            });

        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--<div>
        <span>欢迎<span class="um_span">韩总</span>光临尚硅谷书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="index.jsp">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>--%>
    <%--使用包含--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td align="center" colspan="5"><a href="index.jsp">亲，您的购物车空空如也哦，请去选购你心仪的商品吧！！！！</a></td>
            </tr>

        </c:if>
        <%--遍历数据--%>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items}" var="cartItemEntry">
                <tr>
                    <td>${cartItemEntry.value.name}</td>
                    <td>
                        <input style="width: 75px;" bookId="${cartItemEntry.value.id}" class="updateCount" type="text"
                               value="${cartItemEntry.value.count}">
                    </td>
                    <td>${cartItemEntry.value.price}</td>
                    <td>${cartItemEntry.value.totalPrice}</td>
                    <td><a class="delete_cartItem"
                           href="cartServlet?action=deleteItem&id=${cartItemEntry.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <%--购物车非空才显示下面的内容--%>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a id="creatOrder" href="orderServlet?action=creatOrder">去结账</a></span>
        </div>
    </c:if>


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