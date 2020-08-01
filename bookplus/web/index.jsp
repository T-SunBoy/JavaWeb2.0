<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%--此页仅仅用来作转到首页的请求转发,并传入参数action=page给client/clientBookServlet程序，获取参数后调用page方法处理首页的分页--%>
<jsp:forward page="client/clientBookServlet?action=page"></jsp:forward>