<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=getServletContext().getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	
	function queryImage(){
		var row = $('#dg').datagrid('getSelected');
		window.open("<%=path%>/helpManager?status=queryImage&id="+row.id)
	}
	
	
	function deleteImage(){
		var row = $('#dg').datagrid('getSelected');
	    if (row){
	    	$.messager.confirm('删除确认','你确定要删除栏目‘'+row.title+'’吗？',function(r){
	            if (r){
	                $.post('<%=path%>/AdminServlet?method=deleteAlbum',{"id":row.id},function(result){
	                    if (result[0].success){
	                    	$('#dg').datagrid('options').url = "<%=path%>/AdminServlet?method=showAlbums" ;
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
			url="<%=path%>/AdminServlet?method=showAllAlbums"
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
				<th field="userName"  width="50" align="center">作者</th>
				<th field="phone"  width="50" align="center">联系方式</th>
				<th field="createTime" width="50" align="center">日期</th>
			</tr>
		</thead>
	</table>
	
	
	
<!-- 	操作对话框 -->
	<div id="dlg" class="easyui-dialog" style="width:400px;height:auto;padding:10px 20px"
        closed="true" buttons="#dlg-buttons">
    <div class="ftitle">相册审核</div>
    <form id="fm" method="post" novalidate>
        <div class="fitem">
            <label>相册名称</label>
            <input name="albumName" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>作者</label>
            <input name="userName" class="easyui-textbox" required="true">
        </div>
         <div class="fitem">
            <label>联系方式</label>
            <input name="phone" class="easyui-textbox" required="true">
        </div>
        <div class="fitem">
            <label>日期:</label>
            <input name="createTime" class="easyui-textbox">
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
		<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" plain="true"onclick="deleteImage()">删除</a>
	</div>
	
	<!-- 	右键快捷菜单 -->
	<div id="mm" class="easyui-menu" data-options="hideOnUnhover:false"style="width:120px;">
		<div data-options="iconCls:'icon-cancel'" onclick="deleteImage()">删除</div>
		<div class="menu-sep"></div>
		<div>关闭</div>
	</div>
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