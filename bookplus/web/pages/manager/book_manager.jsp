<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%--<link type="text/css" rel="stylesheet" href="../../static/css/style.css" >--%>

    <%--	静态包含base标签、css样式、jquery文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        <%--给删除标签绑定点击确定事件，防止误删操作--%>
        $(function () {
            //在事件的function函数中，有一个this对象。这个this对象，是当前正在响应事件的document对象。
            $("a.deleteClass").click(function () {
                /**
                 * confirm是确认提示框函数
                 * 参数是提示内容
                 * 有俩个按钮，确认，取消
                 * 返回true表示点击饿了确认，返回false点击了取消
                 */
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text() + "】图书吗?");
                // return false;返回false表示点击取消，阻止元素的默认行为，不提交请求；
            });

        });
    </script>
</head>
<body>


<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%--<div>
        <a href="book_manager.jsp">图书管理</a>
        <a href="order_manager.jsp">订单管理</a>
        <a href="../../index.jsp">返回商城</a>
    </div>--%>
    <%--静态包含menu菜单--%>
    <%@include file="/pages/common/menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <%--获取request域中books的数据，遍历数据，显示在图书列表页面--%>
        <c:forEach items="${requestScope.page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>

                    <%-- <td><a href="pages/manager/book_edit.jsp">修改</a></td> --%>
                <td>
                    <a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNow=${requestScope.page.pageNow}">修改</a>
                </td>

                    <%-- <td><a href="#">删除</a></td>--%>
                <td><a href="manager/bookServlet?action=delete&id=${book.id}&pageNow=${requestScope.page.pageNow}"
                       class="deleteClass">删除</a></td>
            </tr>

        </c:forEach>

        <%--<tr>
            <td>时间简史</td>
            <td>20.00</td>
            <td>霍金</td>
            <td>200</td>
            <td>400</td>
            <td><a href="book_edit.jsp">修改</a></td>
            <td><a href="#">删除</a></td>
        </tr>--%>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?method=add&pageNow=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <%-- 分页条的开始 --%>
    <%-- 使用静态包含，去使用分页条 --%>
    <%@include file="/pages/common/page_nav.jsp" %>
    <%-- 分页条的结束 --%>
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