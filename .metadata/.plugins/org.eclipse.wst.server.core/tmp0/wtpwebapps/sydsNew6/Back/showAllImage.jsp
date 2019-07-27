<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%String path=getServletContext().getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=path%>/js/jquery-1.11.1.min.js"></script>
  <script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script> 
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/carousel.css">
  <script type="text/javascript" src="<%=path%>/js/carousel.js"></script> 
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/index.css">
  <link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css">
  <script src="<%=path%>/js/style.js" type="text/javascript" charset="utf-8"></script> 
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.js"></script>
<%@ include file="easyui.jsp" %>
<title>相册审核</title>

<!-- 	dialog的css -->
	<style type="text/css">
		#fm{
			margin:0;
			padding:10px 30px;
		}
		.ftitle{
			font-size:14px;
			font-weight:bold;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label{
			display:inline-block;
			width:80px;
		}
		.fitem input{
			width:160px;
		}
		.fitem select{
			width:164px;
		}
	</style>
	
<script type="text/javascript">
	$(function(){
		$('#dg').datagrid({
			
		});
	});
	
	function downloadImage(){
		var row = $('#dg').datagrid('getSelected');
		window.location = "<c:url value = '/AdminServlet' />?method=downloadImage&realName="+row.realName+"&phone="+row.phone+"&image_url="+row.image_url;
	}
	function queryImage(){
		$("#image_first").attr("src","");
		var row = $('#dg').datagrid('getSelected');
		var p = document.getElementById("div_imgs");
		$("#image_first").attr("src",<%=path%>/+""+row.image_url);
	    $(".photo-mask").show();
		$(".photo-panel").show();
		img_src = $(this).attr("src");
		img_index = $(this).index();
		photoView($(this));
	}
	function deleteImage(){
		var row = $('#dg').datagrid('getSelected');
	    if (row){
	    	$.messager.confirm('删除确认','你确定要删除栏目‘'+row.title+'’吗？',function(r){
	            if (r){
	                $.post('<%=path%>/AdminServlet?method=deleteImage',{"id":row.id,"album_id":row.album_id,"image_url":row.image_url},function(result){
	                    if (result[0].success){
	                    	$('#dg').datagrid('options').url = "<%=path%>/AdminServlet?method=showAllImages" ;
	                        $('#dg').datagrid('reload');    // reload the user data
	                    } else {
	                        $.messager.show({    // show error message
	                            title: '错误提示',
	                            msg: result[0].errorMsg,
	                            timeout: 2000
	                        });
	                    }
	                },'json');
	            }
	        });
	    }
	    else{
	    	 var options = {
	    	            title: "操作提示",
	    	            msg: "请选择您要删除的记录！",
	    	            showType: 'slide',
	    	            timeout: 1500
	    	        };
	    	 $.messager.show(options);
	    }
	}
</script>
</head>
<body>
<!-- 	datagrid -->
	<table id="dg" class="easyui-datagrid" style="" toolbar="#toolbar"
			url="<%=path%>/AdminServlet?method=showAllImages"
			data-options="
				lines: true,
				idField: 'id',
				rownumbers:true,
		 		fitColumns:true, 
		 		singleSelect:true,
			">
		<thead>
			<tr>
				<th field="albumName"  width="50" align="center">相册名称</th>
				<!-- <th field="image_url"  width="50" align="center">图片路径</th> -->
				<th field="realName"  width="50" align="center">作者</th>
				<th field="phone"  width="50" align="center">联系方式</th>
			</tr>
		</thead>
	</table>
	
	
	
<!-- 	操作对话框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:auto;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">所有图片</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>相册名称</label>
            <input name="albumName" class="easyui-textbox" required="true">
        </div>
       <!--  <div class="fitem">
            <label>图片路径</label>
            <input name="image_url" class="easyui-textbox" required="true">
        </div> -->
        <div class="fitem">
            <label>作者</label>
            <input name="realName" class="easyui-textbox" required="true">
        </div>
         <div class="fitem">
            <label>联系方式</label>
            <input name="phone" class="easyui-textbox" required="true">
        </div>
    </form>
	</div>
	
	<!-- 	操作对话框按钮 -->
	<div id="dlg-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveMenu()" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" style="width:90px">取消</a>
	</div>
	
	<!--     按钮栏 -->
	<div id="toolbar" class="datagrid-toolbar">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"plain="true" onclick="queryImage()" >查看详细</a> 		
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true"onclick="deleteImage()">删除</a>
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"plain="true" onclick="downloadImage()" >下载选中图片</a> 
	</div>
	
	<!-- 	右键快捷菜单 -->
	<div id="mm" class="easyui-menu" data-options="hideOnUnhover:false"style="width:120px;">
	    <div data-options="iconCls:'icon-add'"onclick="queryImage()">查看详细</div>
		<div data-options="iconCls:'icon-cancel'" onclick="deleteImage()">删除</div>
		<div data-options="iconCls:'icon-add'"onclick="downloadImage()">下载选中图片</div>
		<div class="menu-sep"></div>
		<div>关闭</div>
	</div>
	
	
	<!-- 展示图片 -->
	<div id="editForm" class="white_content">
		<div class="imgs" id="div_imgs"></div>
	</div>

    <div class="photo-mask"></div>
    <div class="photo-panel">
      <div class="photo-div">
        <div class="photo-left">
          <div class="arrow-prv"></div>
        </div>
        <div class="photo-img">
          <div class="photo-bar">
            <div class="photo-close"></div>
          </div>
            
          <div class="photo-view-h">
		     <img id="image_first" src="" />
          </div>
        </div>
              
        <div class="photo-right">
          <div class="arrow-next"></div>
        </div>
      </div>
    </div>
    </div>
  </div>
 <!-- 展示图片结束 -->
 
  <script src="js/jquery.min.js"></script>
  <script>
    var img_index = 0;
    var img_src = "";
    var num=0;
 //  for(var i=0;i<4;i++){


    $(function() {
		//计算居中位置
		var mg_top = ((parseInt($(window).height()) - parseInt($(
				".photo-div").height())) / 2);

		$(".photo-div").css({
			"margin-top" : "" + mg_top + "px"
		});
		//关闭
		$(".photo-close").click(function() {
			$(".photo-mask").hide();
			$(".photo-panel").hide();
		});
		//下一张
		$(".photo-panel .photo-div .arrow-next")
				.click(
						function() {
							if (img_index == -1) {
								img_index = 1;
							} else {
								img_index++;
							}
							if (img_index >= $("#editForm .imgs .album img").length) {
								img_index = 0;
							}
							img_src = $("#editForm .imgs .album img").eq(
									img_index).attr("src");
							photoView($("#editForm .imgs .album img"));
						});
		//上一张
		$(".photo-panel .photo-div .arrow-prv")
				.click(
						function() {
							img_index--;

							if (img_index < 0) {
								img_index = $("#editForm .imgs .album img").length - 1;
							}
							img_src = $("#editForm .imgs .album img").eq(
									img_index).attr("src");
							photoView($("#editForm .imgs .album img"));
						});
		
		
		//如何调用？展示相册的图片
		
	});

	//自适应预览
	function photoView(obj) {
		if ($(obj).width() >= $(obj).height()) {
			$(".photo-panel .photo-div .photo-img .photo-view-h").attr(
					"class", "photo-view-w");
			$(".photo-panel .photo-div .photo-img .photo-view-w img").attr(
					"src", img_src);
		} else {
			$(".photo-panel .photo-div .photo-img .photo-view-w").attr(
					"class", "photo-view-h");
			$(".photo-panel .photo-div .photo-img .photo-view-h img").attr(
					"src", img_src);
		}
		//此处写调试日志
		console.log(img_index);
	} 
</script>
	<script>
		$(function(){
			$(document).bind('contextmenu',function(e){
				e.preventDefault();
				$('#mm').menu('show', {
					left: e.pageX,
					top: e.pageY
				});
			
			});
		});
	</script>

</body>
</html>