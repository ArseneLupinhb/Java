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
<link rel="stylesheet" href="css/style-uploading.css">
</head>

<body>
	<!-- 照片上传 -->
	<div class="wrap">
		<div class="photo">
			<span class="point">上传到</span>
			<div class="detail">
				<div class="selected">
					<div class="selected-item">
					     <c:set var="albumImage_url" value="${sessionScope.album.albumImage_url }"/>
                                <c:if test="${ albumImage_url != ''}">
	                                 <p ><img src="<c:url value='${albumImage_url }'/>" alt="user" class="photo-image"></p>
                                </c:if>
                                <c:if test="${albumImage_url == ''}">
	                                 <p ><img src="images/yonghu1.png" alt="user" class="photo-image"></p>
                                </c:if>
                         <p id="p_albumName" >${sessionScope.album.albumName }</p>
					</div>
					<p class="arrow">
						<span></span>
					</p>
			  </div>

				<ul class="album-list" style = "margin:0;padding:0;list-style-type:none;float:left;">
					<c:forEach var="item" items="${albumList }">
						<li id="li_id">
							<p>
							    <c:set var="albumImage_url" value="${item.albumImage_url }"/>
                                <c:if test="${albumImage_url != ''  }">
                                          <img src="<c:url value='${item.albumImage_url }'/>" alt="user" class="photo-image">
                                </c:if>
                                <c:if test="${albumImage_url  == '' }">
	                                 <img src="images/yonghu1.png" alt="user" class="photo-image">
                                </c:if>
							    <p id="p_albumName">${item.albumName }</p>
							</p>
							<p id="p_album_id" style="display:none;" >${item.id }</p> 
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<form id="uploadForm" method="post" enctype="multipart/form-data" action="<c:url value = 'UploadServlet'/>">
			<div id="pictures" style="display: none">
				<ul>
					<li id="continue-up0" style="float: left">
					<label for="up-pic0">
					<img src="images/contium-upload.png" alt="upload">
					</label>
				    <input type="hidden" name="album_id" id="album_id" value=""> 
				    <input type="file" id="up-pic0" style="display:none;" multiple="multiple" onchange="javascript:setImagePreviews();"></li>
				</ul>
			</div>
			<div id="choose">
				<label id="label1"><img src="images/choose.png" alt=""></label>
				<input type="file" id="uploading" style="display:none;" multiple="multiple"
					onchange="javascript:setImagePreviews();">
				<p style="text-align: center">按住ctrl选择多张</p>
				<p style="text-align: center;">上传照片，每张不超过8M，总大小不超过50M</p>
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
