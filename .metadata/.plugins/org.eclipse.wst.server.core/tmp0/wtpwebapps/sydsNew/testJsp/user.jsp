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

<title>My JSP 'user.jsp' starting page</title>

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
	${sessionScope.userShow.password } ${sessionScope.user.userName }---${user.password }

	<form action="<c:url value = '/UserServlet' />" method="post">
		<input type="hidden" name="method" value="alertUser" /> 用 户 名：<input
			type="text" name="userName" value="${user.userName } " /><br> 密
		码：<input type="password" name="password" value="${user.password }" /><br>
		真实姓名：<input type="text" name="realName" value="${user.realName }" /><br>
		联系方式：<input type="text" name="phone" value="${user.phone }" /><br>
		学 院：<input type="text" name="unit_id" value="${user.unit_id }" /><br>
		专 业：<input type="text" name="major" value="${user.major }" /><br>
		<input type="submit" name="alter" value="修改" /><br>
	</form>

	<a href="<c:url value = '/album.jsp' />">查看相册</a>
</body>
</html>
