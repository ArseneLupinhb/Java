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

<title>My JSP 'album.jsp' starting page</title>

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
	<form action="<c:url value = '/AlbumServlet' />" method="post">
		<input type="hidden" name="method" value="addAlbum" /> 相册名称：<input
			type="text" name="albumName" value="" /><br> 描 述：
		<textarea rows="5" cols="10" name="description" value=""></textarea>
		<br> <input type="submit" name="add" value="添加" /><br>
	</form>

	<form action="<c:url value = '/AlbumServlet' />" method="post">
		<input type="hidden" name="method" value="deleteAlbum" /> <input
			type="submit" name="deletes" value="删除" /><br>
	</form>


	<form action="<c:url value = '/AlbumServlet' />" method="post">
		<input type="hidden" name="method" value="alertAlbum" /> 相册名称：<input
			type="text" name="albumName" value="${album.albumName }" /><br>
		描 述：
		<textarea rows="5" cols="10" name="description">${album.description }</textarea>
		<br> <input type="submit" name="alter" value="编辑" /><br>
	</form>
	<select>
		<c:forEach var="item" items="${albumList }">
			<option><c:out value="${item.albumName }" /></option>
		</c:forEach>
	</select>

	<form action="<c:url value = '/AlbumServlet' />" method="post">
		<input type="hidden" name="method" value="alertAlbum" />
		<table>
			<tr>
				<c:forEach var="item" items="${albumList }">
					<td><c:out value="${item.albumName }" /></td>
				</c:forEach>
			</tr>
		</table>
	</form>


</body>
</html>
