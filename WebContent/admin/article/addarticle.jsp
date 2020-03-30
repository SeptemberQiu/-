<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../base/header.jsp" %>
<title>文章添加</title>
</head>
<body>
    <div class="weadmin-body">
        <form class="layui-form" method="post" action="<%=request.getContextPath()%>/admin/article/addarticle">
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>文章标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="title" lay-verify="required"
                  autocomplete="off" value="${user.username}" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="we-red">*</span>文章作者
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="author" required="" lay-verify="required"
                  autocomplete="off" value="${user.name}" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label  class="layui-form-label">
                  <span class="we-red">*</span>文章内容
              </label>
             <!--  <div class="layui-input-inline">
                  <input type="text"  name="content" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
               -->
              <div class="">
              		<!-- 行块元素 -->
              		<div id="editor" style="display:inline-block;"></div>
              </div>
          </div>
          <textarea rows="10" cols="60" name="content" id="content"></textarea>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" >
              添加
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

          /* //监听提交
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
          }); */
          
        });
    </script>
    
    <script src="<%=request.getContextPath()%>/static/js/wangEditor.min.js"></script>
    <script>
	    var E = window.wangEditor
	    var editor = new E('#editor')
	    
	    editor.customConfig.onchange = function (html) {
            // 监控变化，同步更新到 textarea
            /* $text1.val(html) */
            /* console.log(html) */
            var textareaDom = document.querySelector("#content");
            textareaDom.value = html;
        }
	    //放置到创建编辑器之前
	    editor.create()
    </script>
  </body>
</html>