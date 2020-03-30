<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="base/header.jsp" %>
<title>用户个人信息</title>
</head>
<body>
    <div class="weadmin-body">
        <form class="layui-form" method="post" action="<%=request.getContextPath()%>/co/addrecruit">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>职位名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
              
          </div>
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>月薪
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="pay" name="pay" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
          </div>
          
		<div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>人数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="persons" name="persons" required="" lay-verify="required"
                  autocomplete="off" value="" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                确定
              </button>
          </div>
      </form>
    </div>
		
<!--     <script type="text/javascript">
    	layui.extend({
				admin: '{/}../../static/js/admin'
			});
	    layui.use(['form','layer','admin'], function(){
	      var form = layui.form,
	      	admin = layui.admin,
	      	layer = layui.layer;
        
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
    </script> -->
  </body>
</html>