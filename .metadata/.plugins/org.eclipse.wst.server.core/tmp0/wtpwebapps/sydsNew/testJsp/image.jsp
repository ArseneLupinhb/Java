<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'image.jsp' starting page</title>

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
	<!--查看单个图片  -->
	<img alt="图片" src="<c:url value='${image.image_url }'/>" width="100"
		height="100" />
	<br>
	<!--查看个人图片，按相册分类  -->

	<c:forEach var="item" items="${images }">
		<img alt="图片" src="<c:url value='${item.image_url }'/>" width="100"
			height="100" />
	</c:forEach>

	<form action="<c:url value = '/ImageServlet' />" method="post">
		<input type="hidden" name="method" value="praiseImage" /> <input
			type="submit" name="praise" value="点赞" />
	</form>

	<form action="<c:url value = '/ImageServlet' />" method="post">
		<input type="hidden" name="method" value="cancelPraiseImage" /> <input
			type="submit" name="cancelPraise" value="取消点赞" />
	</form>


	<form action="<c:url value = '/ImageServlet' />" method="post">
		<input type="hidden" name="method" value="searchImages" /> <input
			type="text" name="searchString" value="" /> <input type="submit"
			name="search" value="搜索" />
	</form>
</body>

</html>
