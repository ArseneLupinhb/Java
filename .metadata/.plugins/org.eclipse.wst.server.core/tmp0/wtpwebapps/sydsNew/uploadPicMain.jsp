<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" type="text/css" href="css/introduction.css">
<script type="text/javascript">
	$(function(){
		$("#li_id").click(function(){
			alert("你选择了我");
		});
	});
	</script>
</head>

<body>
	<!-- 照片上传 -->
	<div class="wrap">
		<div class="photo">
			<span class="point">上传到</span>
			<div class="detail">
				<div class="selected">
					<div class="selected-item">
						<%--  <p ><img src="images/yonghu1.png" alt="user" class="photo-image"></p>
         <p>${item.albumName[0] }</p> --%>
					</div>
					<p class="arrow">
						<span></span>
					</p>
				</div>

				<ul class="album-list">
					<c:forEach var="item" items="${albumList }">
						<li id="li_id">
							<p>
								<img src="images/yonghu1.png" alt="user" class="photo-image">
							</p>
							<p id="p_album_id">${item.id }</p> <!-- style="display:none;" -->
							<p id="p_albumName">${item.albumName }</p>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<form id="uploadForm" method="post" enctype="multipart/form-data">
			<div id="pictures" style="display: none">
				<ul>
					<li id="continue-up0" style="float: left"><label for="up-pic0"><img
							src="images/contium-upload.png" alt="upload"></label> <input
						type="hidden" name="album_id" id="album_id" value=""> <input
						type="file" id="up-pic0" hidden multiple="multiple"
						onchange="javascript:setImagePreviews();"></li>
				</ul>
			</div>
			<div id="choose">
				<label id="label1"><img src="images/choose.png" alt=""></label>
				<input type="file" id="uploading" hidden multiple="multiple"
					onchange="javascript:setImagePreviews();">
				<p style="text-align: center">按住ctrl选择多张</p>
			</div>
			<div>
				<button id="submit" onclick="upload(event)">开始上传</button>
			</div>
		</form>
	</div>
	<script src="js/jquery-1.11.3.min.js"></script>
	<script src="js/uploading.js"></script>

</body>
</html>
