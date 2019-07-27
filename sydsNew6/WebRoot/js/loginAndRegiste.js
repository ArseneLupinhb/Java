	$(document).ready(function() {
  		//不为空说明已登录，隐藏注册和登录
  		if($("#showUserName").html() != ""){
  			$("#login_id").hide();
			$("#register_id").hide();
			$("#quit").show();
  		}else{
  			$("#quit").hide();
  			$("#login_id").show();
			$("#register_id").show();
  		}
  		
  		//$("#showUserName").hide();
  	//登录
  	$('#login').bind('click', function() {
  		var name1 = document.getElementById('userName1');
		var paw1 = document.getElementById('password1');

		var userName1 = name1.value;
		var password1 = paw1.value;

		if (userName1.length <= 0) {
			alert("请输入用户名");
			name1.focus();
			return false;
		} else if (password1.length <= 0) {
			alert("密码未填写");
			paw1.focus();
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : 'UserServlet?method=login',
				data : {
					'userName1' : userName1,
					'password1' : password1
				},
				success : function(data) {

					if (data == 'false') {
						alert("用户名或密码错误，请重新输入！");
						$("#userName1").val("");
						$("#password1").val("");
						$("#userName1").focus();
					} else if (data == 'true') {
						$("#login_box").hide();
						$("#login_id").hide();
						$("#register_id").hide();
						$("#showUserName").html(userName1);
						$("#quit").show();
						
						alert("登录成功");
					} else if (data == 'logining') {
						$("#quit").show();
						$("#login_id").hide();
						$("#login_box").hide();
						alert("您已经登录");
					}
				}
			});
		}
  	});
  //退出
  	$('#quit').bind('click', function() {
  		$.ajax({
			type : "POST",
			url : 'UserServlet?method=quit',
			data : {},
			success : function(data) {
				if (data == 'true') {
					alert("退出成功");
					window.location = "index0.jsp"; 
				}else if(data == 'noLogin'){
					alert("请先登录");
				}else{
					alert("退出失败，请重新打开浏览器");
					window.location.reload() ;
				}
			}
		});
  		
  	});
  	//注册的用户名检验
	$('#userName').bind('blur', function() {
      var userName = document.getElementById("userName").value;
		if (userName == "") {
			return;
		}
		$.ajax({
			type : "POST",
			url : 'UserServlet?method=checkUserName',
			data : {
				'userName' : userName
			},
			success : function(data) {
				if (data == 'true') {
					alert("用户名已经存在，请重新输入！");
					$("#userName").val("");
					$("#userName").focus();

				}
			}
		});
    });
	
	
	//两次密码输入是否一致的校验
	$('#passwordAgain').bind('blur', function() {
		var password1 = document.getElementById('password').value;
		var password2 = document.getElementById('passwordAgain').value;
		if (password1 != password2) {
			alert("两次密码输入不一致！！");
		}
	    });
	
});
  	
  //对联系方式的校验
	function checkCellNumber() {
		var objApplicantCell = document.getElementById("cellnumber");
		if (objApplicantCell != null) {
			var cellNum = document.getElementById("cellnumber").value;
			var rex = /^1[3-8]+\d{9}$/;
			var rex2 = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
			//var rex2=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
			if (cellNum == "") {
				alert("联系方式不能为空！");
				//document.getElementById("flag").value="手机号码不能为空";  
				$("#cellnumber").val("");
				$("#cellnumber").focus();
				return ;
			} else if (!rex.test(cellNum)) {
				if (!rex2.test(cellNum)) {
					alert("请输入正确的联系方式！");
					//$("#message_phone").text("请输入正确的联系方式！").css({"color":"red"});
					$("#cellnumber").val("");
					$("#cellnumber").focus();
					return;
				}
				//else $("#message_phone").text("");
			}

		}
		
		var userName = document.getElementById("userName").value;
		var password = document.getElementById("password").value;
		var department = document.getElementById("department").value;
		var academy = document.getElementById("academy").value;
		var realName = document.getElementById("realName").value;
		var cellnumber = document.getElementById("cellnumber").value;
		if (realName.length <= 0 || realName=="真实姓名") {
			alert("真实姓名不能为空！");
			$("#realName").val("");
			$("#realName").focus();
		} else if (userName.length <= 0 || userName=="请输入用户名") {
			alert("用户名不能为空！");
			$("#userName").val("");
			$("#userName").focus();
		} else {
			$.ajax({
				type : "POST",
				url : 'UserServlet?method=register',
				data : {
					'userName' : userName,
					'password' : password,
					'department' : department,
					'academy' : academy,
					'realName' : realName,
					'cellnumber' : cellnumber

				},
				success : function(data) {

					if (data == 'true') {
						alert("注册成功！");
						deleteRegister();
						showBox();
					}
				}
			});
		}

	}
  	//注册时真实姓名校验
  	function realName() {
		var nei = document.getElementById("realName").value;
		if (nei.length <= 0) {
			alert("姓名不能为空");
			$("#realName").val("");
			$("#realName").focus();
		}

	}
  //登录的校验
	function test() {

		var name1 = document.getElementById('userName1');
		var paw1 = document.getElementById('password1');

		var userName1 = name1.value;
		var password1 = paw1.value;

		if (userName1.length <= 0) {
			alert("请输入用户名");
			name1.focus();
			return false;
		} else if (password1.length <= 0) {
			alert("密码未填写");
			paw1.focus();
			return false;
		} else {
			$.ajax({
				type : "POST",
				url : 'UserServlet?method=login',
				data : {
					'userName1' : userName1,
					'password1' : password1
				},
				success : function(data) {

					if (data == 'false') {
						alert("用户名或密码错误，请重新输入！");
						$("#userName1").val("");
						$("#password1").val("");
						$("#userName1").focus();
					} else if (data == 'true') {
						$("#login_id").hide();
						window.location.href = "index3.jsp";
					} else if (data == 'logining') {
						alert("您已经登录");
						window.location.href = "index3.jsp";
					}
				}
			});
		}
	}
