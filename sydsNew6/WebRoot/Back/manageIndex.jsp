<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=getServletContext().getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="easyui.jsp" %>
<title>第四届“和谐*美丽”校园摄影大赛网站后台管理平台</title>
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
	function addTab(title, url){
		if ($('#tt').tabs('exists', title)){
			$('#tt').tabs('select', title);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add',{
				title:title,
				content:content,
				closable:true,
				fit:true
			});
		}
	}
	
	function changePwd(){
	    	$('#dlg2').dialog('open').dialog('setTitle','修改密码');
	        url = '<%=path%>/helpManager?status=changePwd&id='+${admin.id};    
	}
	
	
	function checkNewPwd(opass){
		if(pwd1.value!=opass){
			alert("原密码输入错误！")
		}
		else if(pwd2.value!=pwd3.value){
			alert("两次输入密码不一致！")
		}
		else{
			savePwd();
		}
	}
	
	 function savePwd(){
		$('#fm').form('submit',{
			url: url,
			onSubmit: function(){
				return $(this).form('validate');
			},
			success: function(result){
				var r = eval('('+result+')');
				if (r[0].errorMsg){
					$.messager.show({
						title: '错误提示',
						msg: r[0].errorMsg,
						timeout: 1500
					});
				} else {
					$('#dlg2').dialog('close');
				}
			}
		});
	} 
	
</script>
</head>
<body>	
	<div class="easyui-layout" style="width:100%;height:865px;">
	
<!-- 		头部标识 -->
		<div data-options="region:'north'" style="height:87px">
			<h1 align="center">第四届“和谐*美丽”校园摄影大赛网站后台管理平台</h1>
			<div align="right">
			<p>
				<span><a href="<%=path%>/Back/manageIndex.jsp">首页|</a></span>
				<span><a href="#">${admin.adminName}|</a></span>
				<!-- <span><a href="#" onclick="changePwd()">修改密码|</a></span> -->
				<span><a href="<%=path%>/AdminServlet?method=quit">退出用户</a></span>
			</p>
			</div>
		</div>
		
<!-- 		右布局 -->
<!-- 		<div data-options="region:'east',split:true" title="East" style="width:200px;"> -->
		
<!-- 		</div> -->
		
<!-- 		左边栏目导航 -->
		<div data-options="region:'west',split:true" title="栏目导航" style="width:200px;">
			<div class="easyui-layout" data-options="fit:true">
			
				<div data-options="region:'center',split:true" style="height:550px">
					<div class="easyui-accordion" data-options="" >
						<div title="作品管理" style="padding:10px;">
						    <p><a href="#" onclick="addTab('相册名称审核','<%=path%>/Back/showAlbum.jsp')">相册名称审核</a></p>
						    <p><a href="#" onclick="addTab('图片审核','<%=path%>/Back/showImage1.jsp')">图片审核</a></p>
						    <p><a href="#" onclick="addTab('所有相册','<%=path%>/Back/showAllAlbum.jsp')">所有相册</a></p>
						    <p><a href="#" onclick="addTab('所有图片','<%=path%>/Back/showAllImage.jsp')">所有图片</a></p>
						</div>
					</div>
				</div>
				
				<div data-options="region:'north',split:true" >
						<div id="pp" style="position:relative">
							<div title="Clock" style="text-align:center;background:#f3eeaf;height:110px;padding:5px;">
								<object codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="100" height="100" align="center">
							      <param name="movie" value="http://www.respectsoft.com/onlineclock/analog.swf">
							      <param name=quality value=high>
							      <param name="wmode" value="transparent">
							      <embed src="http://www.respectsoft.com/onlineclock/analog.swf" width="100" height="100" quality=high pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash" type="application/x-shockwave-flash" wmode="transparent"></embed>
							    </object>
						    </div>
						</div>
				</div>
				
			</div>
		</div>
		
<!-- 		中间主要内容 -->
		<div data-options="region:'center',title:'主要内容'">
			<div id="tt" class="easyui-tabs" data-options="fit:true" ">
				<div title="Welcome" style="padding:10px" data-options="closable:true">
					<h2>尊敬的：${admin.adminName} </h2>
					<p><h2>&nbsp;&nbsp;欢迎登陆！</h2></p>
				</div>				
			</div>
		</div>
		
<!-- 		尾部版权申明 -->
		<div data-options="region:'south'" style="height:40px;" align="center">
			<p>Copyright@2017	All Rights Reserved.	技术支持:<a href="http://adou.cnwust.com">武科大阿豆工作室</a> <a href="<%=path%>/index.jsp">返回前台</a></p>
		</div>
<!-- 		对话框-->		
		<div id="dlg2" class="easyui-dialog" style="width:350px;height:auto;padding:10px 20px"
		closed="true" buttons="#dlg2-buttons">
		<div class="ftitle">修改密码</div>
		<form id="fm" method="post" novalidate>
		<div class="fitem" id="pwd">
            <label>原密码:</label>
            <input id="pwd1" name="oldPwd"  class="easyui-textbox" type="password" required="true">
        </div>
		<div class="fitem" id="pwd">
            <label>新密码:</label>
            <input id="pwd2" name="adminPwd"  class="easyui-textbox" type="password" required="true">
        </div>
        <div class="fitem" id="pwdAgain">
            <label>确认密码:</label>
            <input id="pwd3" name="adminPwd" class="easyui-textbox" type="password" validType="equalTo['#pwd2']" required="true">
        </div>
        </form>
     </div>
<!-- 		对话框按钮-->	     
     <div id="dlg2-buttons">
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="checkNewPwd('${admin.adminPwd}')" style="width:90px">保存</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg2').dialog('close')" style="width:90px">取消</a>
	</div>

<script>
	
</script>
</body>
</html>