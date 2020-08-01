<%--
  Created by IntelliJ IDEA.
  User: 吉涛
  Date: 2020/7/23
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--抽取分页条的代码实现--%>
<%--抽取前提一后台的:manager/bookServlet?action=page使用page.url代替，在后台设置url--%>
<%--抽取前提二前台的:client/clientBookServlet?action=page使用page.url代替，在前台设置url--%>
<%--使用page.url后，此时前后台的分页条完全一致,当启动服务器后，首页会请求转发到client/clientBookServlet?action=page--%>

<%--分页条的开始--%>
<div id="page_nav">
    <%--当前页数大于1时才显示上一页和首页，注意用法:}和"之间不能有空格，否则大于1也不显示--%>
    <c:if test="${requestScope.page.pageNow > 1}">

        <%--将client/clientBookServlet?action=page抽取为page的属性，此时可用page.url来代替--%>
        <a href="${requestScope.page.url}&pageNow=1">首页</a>
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageNow-1}">上一页</a>
    </c:if>
    <c:choose>
        <%--显示五个页面，总页码小于五的情况--%>
        <c:when test="${requestScope.page.pageTotal <=5}">
            <%-- 此时在这设置开始和结束索引即可--%>
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
            <%-- <c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
                 <c:if test="${requestScope.page.pageNow ==i}">
                     【${i}】
                 </c:if>
                 <c:if test="${requestScope.page.pageNow !=i}">
                     <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
                 </c:if>
             </c:forEach>--%>
        </c:when>

        <%--总页码大于5的情况--%>
        <c:when test="${requestScope.page.pageTotal > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3个：1，2，3，页码范围1~5--%>
                <c:when test="${requestScope.page.pageNow <=3}">
                    <%-- 此时设置开始和结束索引即可--%>
                    <c:set var="begin" value="1"></c:set>
                    <c:set var="end" value="5"></c:set>
                    <%--<c:forEach begin="1" end="5" var="i">
                        <c:if test="${requestScope.page.pageNow ==i}">
                            【${i}】
                        </c:if>
                        <c:if test="${requestScope.page.pageNow !=i}">
                            <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>
                </c:when>

                <%-- 小情况二：当前页码为最后三个时--%>
                <c:when test="${requestScope.page.pageNow > requestScope.page.pageTotal-3}">
                    <%-- 此时在这设置开始和结束索引即可--%>
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                    <%--<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}"
                               var="i">
                        <c:if test="${requestScope.page.pageNow == i}">
                            【${i}】
                        </c:if>

                        <c:if test="${requestScope.page.pageNow !=i}">
                            <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>
                </c:when>

                <%--剩余情况--%>
                <c:otherwise>
                    <%-- 此时在这设置开始和结束索引即可--%>
                    <c:set var="begin" value="${requestScope.page.pageNow-2}"></c:set>
                    <c:set var="end" value="${requestScope.page.pageNow+2}"></c:set>

                    <%--<c:forEach begin="${requestScope.page.pageNow-2}" end="${requestScope.page.pageNow+2}"
                               var="i">
                        &lt;%&ndash;注意：${requestScope.page.pageNow == i}中值得比较，一定在el表达式里面比较，切记&ndash;%&gt;
                        <c:if test="${requestScope.page.pageNow == i}">
                            【${i}】
                        </c:if>

                        <c:if test="${requestScope.page.pageNow !=i}">
                            <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
                        </c:if>
                    </c:forEach>--%>

                </c:otherwise>
            </c:choose>
        </c:when>

    </c:choose>

    <%--将foreach方法抽取出来，因为上述情况发生都会执行一次，在上述判断中设置开始结束索引就ok--%>
    <c:forEach begin="${begin}" end="${end}"
               var="i">
        <%--注意：${requestScope.page.pageNow == i}中值得比较，一定在el表达式里面比较，切记--%>
        <c:if test="${requestScope.page.pageNow == i}">
            【${i}】
        </c:if>

        <c:if test="${requestScope.page.pageNow !=i}">
            <a href="${requestScope.page.url}&pageNow=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>

    <%--当前页小于总页数时才显示末页和下一页--%>
    <c:if test="${requestScope.page.pageNow <requestScope.page.pageTotal}">
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageNow+1}">下一页</a>
        <a href="${requestScope.page.url}&pageNow=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageNow}" name="pn" id="pn_input"/>页
    <input id="searchPageButton" type="button" value="确定">
    <%-- 给搜索某页的确定按钮添加点击事件 --%>
    <script type="text/javascript">
        $(function () {
            $("#searchPageButton").click(function () {
                //获取输入框的值
                var pageNow = $("#pn_input").val();
                //confirm(location.href);
                /*
                * js中提供了一个location地址栏对象，
                * 该对象有一个href属性，它可以获取浏览器地址栏中的地址
                * href属性可读可写（赋值）
                * */
                //此时点击确定按钮，跳转至下面这地址
                <%-- location.href = "http://localhost:8080/bookplus/${requestScope.page.url}&pageNow="+pageNow;--%>

                //优化：使用动态获取地址方法
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNow=" + pageNow;

            });
        })
    </script>
</div>
<%--分页条的结束--%>
