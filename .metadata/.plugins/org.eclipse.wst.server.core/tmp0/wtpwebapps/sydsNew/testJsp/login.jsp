<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="<c:url value = '/UserServlet' />" method="post">
		<input type="hidden" name="method" value="login" /> 用户名：<input
			type="text" name="userName" /><br> 密 码：<input type="password"
			name="password" /><br> <input type="submit" name="submit" />
	</form>
	<form action="<c:url value = '/UserServlet' />" method="post">
		<input type="hidden" name="method" value="quit" /> <input
			type="submit" name="submit" value="退出" /><br>
	</form>

</body>
</html>
