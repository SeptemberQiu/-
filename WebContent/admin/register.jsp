<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ include file="base/header.jsp" %>
<title>管理员注册-招聘网站后台管理系统</title>
<style >
    input[disabled=disabled]{
    	opacity:0.6;
    	cursor:not-allowed;
    }
    .error{
    	border-color:deeppink!important;
    }	
    .error:hover{
    	border-color:deeppink!important;
    }
</style>
</head>
<body class="login-bg">

    <div class="login">
        <div class="message">管理员注册-招聘网站后台</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" action="<%=request.getContextPath()%>/admin/register" class="layui-form" >
            <input name="username" id="username" placeholder="用户名"  type="text"  class="layui-input" >
            <hr class="hr15">
            <input name="password"  id="password" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input name="repassword" id="repassword" placeholder="确认密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input class="loginin" id="button" value="注册" disabled="disabled" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>
	
	<script>
		
		var usernameDom = document.querySelector("#username");
		var passwordDom = document.querySelector("#password");
		var repasswdDom = document.querySelector("#repassword");
		var button = document.querySelector("#button");
		
		//独立版的layer无需执行这一句
		layui.use('layer', function(){ 
			  var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
			
			    //用户名输入的内容必须是A-z、0-9并且是6大于等于5个字符
				usernameDom.oninput = function(){
					var value = usernameDom.value;
					var regex = /^[A-z0-9_]{5,20}$/i;
					if(regex.test(value)){
						//usernameDom.style.border = "1px solid #DCDEE0!important";
						$("#username").removeClass("error");
					}else{
						$("#username").addClass("error");
						
						//配置一个透明的询问框
					    layer.msg('用户名必须是英文字母和数字以及下划线组成。', {
					    	time: 4000, //2s后自动关闭
					      })
					}
				}
			  
			  	 passwordDom.oninput = function(){
			     	var value = passwordDom.value;
				 	var regex = /.{6,}/; 
					if(!regex.test(value)){
						$("#password").addClass("error");
						//配置一个透明的询问框
					    layer.msg('密码必须6个字符以上', {
					    	time: 4000, //4s后自动关闭
					    	offset:'t'
					      })
					}else{
						$("#password").removeClass("error");
					}
			  }
			  	 repasswdDom.oninput = function(){
						if(passwordDom.value == repasswdDom.value&&passwordDom.value.length>5&&usernameDom.value.length>=5){
							$("#repasswd").removeClass("error");
							button.disabled = false;
						}else{
							$("#repasswd").addClass("error");
							layer.msg("重复密码与密码不一致",{
								time:3000,
								offset:'t'
							})
						}
						
				  }
		});
	</script>
</body>
</html>