<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta name="viewport" charset="UTF-8" content="initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=decice-width"/>
	<title>照片展示</title>
	<link rel="stylesheet" type="text/css" href="css/album.css" />
	<script src="js/style.js"></script>
    <script src="layer/jquery.js?v=1.83.min"></script>
    <script src="layer/layer.min.js"></script>
    <script type="text/javascript">  //个人基本资料的修改
        $(function () {
            $(".change").click(function () {
                //获取td值
                var td = $(this);
                var txt = td.text();
                var input = $("<input type='text'value='" + txt + "'/>");
                td.html(input);
                input.click(function () { return false; });
                //获取焦点
                input.trigger("focus");
                //文本框失去焦点后提交内容，重新变为文本
                input.blur(function () {
                    var newtxt = $(this).val();
                    //判断文本有没有修改
                    if (newtxt != txt) {
                        td.html(newtxt);
                    }
                    else {
                        td.html(newtxt);
                    }
                });
            });
        });

    </script>
</head>
</head>
<body>
    <div id="container">
    	<div class="banner">
    	   <div class="userpic"><img src="images/username.png"></div>
    	   <div class="banner-context">
    	       <div class="banner-head">
    	   	      <div class="banner-username"><b id="t" class="change">用户名甲</b></div>
    	   	       <div class="banner-button"><button type="button" id="click" class="button gray">编辑</button></div>
                  <script type="text/javascript">
                        /* document.getElementById("click").onclick=function(){
                            var text=document.getElementById("t");
                            var val=text.innerHTML;
                            text.innerHTML="<input type='text' id='n' value="+val+" />";
                            document.getElementById("n").addEventListener("blur",function(e){
                            text.innerHTML=document.getElementById("n").value;
                            });
                        }; */
                  </script>
    	   	    </div>
    	   	   <div class="banner-userinfor">
    	   	      <table>
                        <tr>
                            <td>学院：</td> 
                            <td class="change">管理学院</td>
                            <td>真实姓名：</td> 
                            <td class="change">张三</td>
                        </tr>
                        <tr>
                            <td>专业班级：</td> 
                            <td class="change">信息管理与信息系统1401</td>
                            <td>联系电话：</td> 
                            <td class="change">13412345678</td>
                        </tr>
                   </table>
    	   	   </div> 
    	   </div>
    	   <div class="tabs">
    	   	   <div class="tab">${sessionScope.countAlbum }作品集</div>
    	   	   <div class="tab"><b>11照片</b></div>
    	   	   <div class="tab">12赞</div>
    	   </div>
    	</div>

        <!-- 点击作品集封面上的“编辑按钮”，弹出修改窗口 -->
        
        <div id="fade" class="black_overlay"></div>
        <div id="editForm" class="white_content">
            <div style="text-align: right; cursor: default; height: 40px;margin-right: 30px">
            <span style="font-size: 16px;" onclick="hideDiv('editForm','fade')">关闭</span>
            </div>
             <form id="edit" action="#" method="post" target="frame">
                <div style="font-size: 2em;font-weight: bold;color:#202020;margin-bottom: 30px"><span>编辑照片/</span><span style="color:#587265;"><b>夜色</b></span></div><br/>
                <div><span class="spanWidth">标题</span><input type="text" id="albumTitle" style="width: 400px;height: 30px;"></div><br/>
                <div><span class="spanWidth">描述</span><input type="text" id="albumDes" style="width: 400px;height: 80px"></div><br/>
                <div><span class="spanWidth">删除</span><input type="button" class="button" value="删除照片" id="albumDelete" style="width: 200px;height: 50px;border: 1px solid #aeafae"></div><br/>
                <div><input type="button" class="button" value="保存设置" style="width: 250px;height: 60px;background: #587265;font-size: 1em;"></div>
            </form>
        </div>


    <!--照片的编写-->

        <div id="main">
           <div class="album">
                <div class="inner">
                    <a href="uploadPic.jsp" style="display: inline-block;" >
                    <img src="images/add.png">
                    <span style="display: inline-block;font-size: 0.8em;color: #666666;margin-left:32%;">新增照片</span>
                    </a>
                </div>
            </div>

           
                
                <div id="imgs" class="imgs">
                <c:forEach var="image" items="${images }">
                    <div class="album"><img src="<c:url value='${image.image_url }'/>">
                        <button type="button" class="button gray" onclick="popDiv('editForm','fade')">编辑</button>
                    </div> 
                </c:forEach>   
                    
                </div>

           
           <script>
                ;!function(){
                    layer.use('extend/layer.ext.js', function(){
                    //初始加载即调用，所以需放在ext回调里
                        layer.ext = function(){
                           layer.photosPage({
                           html:'<div style="padding:20px;"><div style="display:inline-block;margin-right:20px;"><img src="images/yonghu.png"></div>用户甲<p>古人九大雅事：床前明月光，疑是地上霜</p></p><p id="change"></p><p><img src="images/like.png" style="width:16px;height:16px;"></div>',
                           id: 100, //相册id，可选
                           parent:'#imgs'
                           });
                        };
                    });
                }();
           </script>

    	</div>
    </div>
	
</body>
</html>