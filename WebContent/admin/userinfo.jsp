<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base/header.jsp" %>
<title>用户个人信息</title>
</head>
<body>
    <div class="weadmin-body">
        <form class="layui-form" method="post" action="<%=request.getContextPath()%>/admin/userinfo">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>用户名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="username" disabled="disabled" lay-verify="required"
                  autocomplete="off" value="${user.username}" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>将会成为您唯一的登入名
              </div>
          </div>
          
                    <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>真实姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" value="${user.name}" class="layui-input">
              </div>
<!--               <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>将会成为您唯一的登入名
              </div> -->
          </div>
          
          <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="we-red">*</span>手机
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${user.mobile}" id="phone" name="mobile" required="" lay-verify="phone"
                  autocomplete="off" class="layui-input">
              </div>
<!--               <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>将会成为您唯一的登入名
              </div> -->
          </div>
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="we-red">*</span>邮箱
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${user.email}" id="L_email" name="email" required="" lay-verify="email"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">
                  <span class="we-red">*</span>年龄
              </label>
              <div class="layui-input-inline">
                  <input type="text" value="${user.age}" id="age" name="age" required="" 
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  <span class="we-red">*</span>
              </div>
          </div>
          
          
          <div class="layui-form-item">
              <label class="layui-form-label"><span class="we-red">*</span>角色</label>
              <div class="layui-input-block">
              	<c:if test="${user.userType=='admin'}">
              		<input type="radio" name="userType" lay-skin="primary" value="admin" title="超级管理员" checked="">
                	<input type="radio" name="userType" lay-skin="primary" value="co" title="企业人员">
              	</c:if>
                
                <c:if test="${user.userType=='co'}">
              		<input type="radio" name="userType" lay-skin="primary" value="admin" title="超级管理员">
                	<input type="radio" name="userType" lay-skin="primary" value="co" checked="">
              	</c:if>
              	
              	<c:if test="${user.userType!='co'&&user.userType!='admin'}">
              		<input type="radio" name="userType" lay-skin="primary" value="admin" title="超级管理员">
                	<input type="radio" name="userType" lay-skin="primary" value="co" title="企业人员">
              	</c:if>
<!--                 <input type="radio" name="userType" lay-skin="primary" title="宣传人员" > -->
              </div>
          </div>
          
          <div class="layui-form-item">
              <label class="layui-form-label"><span class="we-red">*</span>性别</label>
              <div class="layui-input-block">
              	<c:if test="${user.sex=='男'}">
              		<input type="radio" name="sex" lay-skin="primary" title="男" value="男" checked="">
                	<input type="radio" name="sex" lay-skin="primary" title="女" value="女">
              	</c:if>
                
                <c:if test="${user.sex=='女'}">
              		<input type="radio" name="sex" lay-skin="primary" title="男" value="男">
                	<input type="radio" name="sex" lay-skin="primary" title="女"  value="女" checked="">
              	</c:if>
              	
              	<c:if test="${user.sex!='女'&&user.sex!='男'}">
              		<input type="radio" name="sex" lay-skin="primary" title="男" value="男">
                	<input type="radio" name="sex" lay-skin="primary" title="女" value="女">
              	</c:if>
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_pass" class="layui-form-label">
                  <span class="we-red">*</span>密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required="" lay-verify="pass"
                  autocomplete="off" class="layui-input">
              </div>
              <div class="layui-form-mid layui-word-aux">
                  6到16个字符
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
                  <span class="we-red">*</span>确认密码
              </label>
              <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                 修改
              </button>
          </div>
      </form>
    </div>
		
    <script type="text/javascript">
    	layui.extend({
				admin: '{/}../../static/js/admin'
			});
	    layui.use(['form','layer','admin'], function(){
	      var form = layui.form,
	      	admin = layui.admin,
	      	layer = layui.layer;
        
          //自定义验证规则
          form.verify({
            nikename: function(value){
              if(value.length < 5){
                return '昵称至少得5个字符啊';
              }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
          });

          //监听提交
          form.on('submit(add)', function(data){
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
          });
          
        });
    </script>
  </body>
</html>