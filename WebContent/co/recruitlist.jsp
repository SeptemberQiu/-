<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="base/header.jsp" %>
<title>企业招聘信息列表</title>
</head>
	<body>
		<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">企业招聘信息管理</a>
        <a>
          <cite>企业招聘信息列表</cite></a>
      </span>
			<a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
				<i class="layui-icon" style="line-height:30px">ဂ</i></a>
		</div>
		<div class="weadmin-body">
			<div class="layui-row">
			</div>
			<div class="weadmin-block">
				<button class="layui-btn layui-btn-danger" id="pdelBtn"><i class="layui-icon"></i>批量删除</button>
				<a class="layui-btn" href="<%=request.getContextPath()%>/co/addrecruit"><i class="layui-icon"></i>添加</a>
				<span class="fr" style="line-height:40px">共有数据${allnum}条</span>
			</div>
			<table class="layui-table">
				<thead>
					<tr>
						<th>
							<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
						</th>
						<th>ID</th>
						<th>职位名称</th>
						<th>月薪</th>
						<th>招聘人数</th>
<!-- 						<th>提交时间</th> -->
						<th>操作</th>
				</thead>
				<tbody>
				<!-- 循环 -->
				<c:forEach items="${recruitlist}" var="item" varStatus="i">
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.id}'><i class="layui-icon">&#xe605;</i></div>
						</td>
						<td>${item.id}</td>
						<td>${item.name}</td>
						<td>${item.pay}</td>
						<td>${item.persons}</td>
<%-- 						<td>${item.time}</td> --%>
						<td>
							<a title="编辑" target="_blank" onclick="WeAdminShow('编辑','./edit.html')" href="">
							<%-- <%=request.getContextPath()%>/admin/edituser?username=${item.username} --%>
								<i class="layui-icon">&#xe642;</i>
							</a>
							
							<span title="删除" class="delbtn" data-id="${item.id}" data-username="${item.name}">
								<i class="layui-icon">&#xe640;</i>
							</span>
						</td>
					</tr>
					
				</c:forEach>
					
				</tbody>
			</table>
			<div class="page">
				<div>
				<!-- 判断是否是第一页，如果是，就不让其显示 -->
				<c:if test="${page!='1'}">
					<a class="prev" href="<%=request.getContextPath() %>/co/recruitlist?page=${page-1}">&lt;&lt;</a>
				</c:if>
					<span class="current">${page}</span>
					<c:if test="${(page+1)<=allpage}">
						<a class="num" href="<%=request.getContextPath() %>/co/recruitlist?page=${page+1}">${page+1 }</a>
					</c:if>
					
					<c:if test="${(page+2)<allpage}">
						<a class="num" href="<%=request.getContextPath() %>/co/recruitlist?page=${page+2}">${page+2 }</a>
					</c:if>
					
					<c:if test="${(page+1)<allpage}">
						<a class="num" href="<%=request.getContextPath() %>/co/recruitlist?page=${allpage}">${allpage }</a>
					</c:if>
					<c:if test="${(page+1)<=allpage}">
						<a class="next" href="<%=request.getContextPath() %>/co/recruitlist?page=${page+1}">&gt;&gt;</a>
					</c:if>
				</div>
			</div>
		</div>
		
		<script>
		layui.use(['layer', 'form','jquery'], function(){
			  var layer = layui.layer
			  ,form = layui.form;
			  var $ = layui.jquery;
				var tbody = document.querySelector("tbody");
				tbody.addEventListener("click",function(e){
					//console.log(e);
					if(e.target.parentElement.className=="delbtn"){
						console.log("点击删除按钮");
						//获取删除按钮的ID和用户名
						var id = e.target.parentElement.dataset.id;
						var name = e.target.parentElement.dataset.username;
						
						var alertId = layer.confirm('是否i确认删除id为'+id+'的'+name+'?', {
							  btn: ['不了', '确定'] //可以无限个按钮
							}, function(index, layero){
								layer.close(alertId);
							}, function(index){
							 location.href="<%=request.getContextPath()%>/co/deleterecruit?id=${item.id}";
							});
					}
				})
							var allselectBtn = document.querySelector(".layui-unselect.header.layui-form-checkbox");
							var isAllSelect = false;
							allselectBtn.addEventListener("click",function(){
								//console.log(123)
								if(isAllSelect){
									$(".layui-form-checkbox i").css("background","#FFF")
									isAllSelect = false;
								}else{
									$(".layui-form-checkbox i").css("background","#1E9FFF")
									isAllSelect = true;
								}
							})
							
							$('.layui-unselect.layui-form-checkbox[data-id]').click(function(e){
								console.log(e);
								if(e.target.style.background=="rgb(30, 159, 255)"){
									//可以放置原生对象，进行封装成jquery对象
									$(e.target).css("background","#fff")
								}else{
									$(e.target).css("background","#1E9FFF")
								}
								isAllSelect = false;
								//第一个自动取消勾选
								$('.layui-unselect.layui-form-checkbox:eq(0) i').css("background","#fff")
							})
							
							$("#pdelBtn").click(function(){
							var allCheckBox = document.querySelectorAll(".layui-unselect.layui-form-checkbox[data-id]");
							var delIdList = [];
							allCheckBox.forEach(function(item,index){
								console.log([item]);
								if(item.children[0].style.background=="rgb(30, 159, 255)"){
									delIdList.push(item.dataset.id);
								}

							})
							console.log(delIdList);
							var alertId = layer.confirm('是否确认删除id为'+delIdList+'?', {
							  btn: ['不了', '确定'] 
							}, function(index, layero){
								layer.close(alertId);
							}, function(index){
							  $.ajax({
									url:"<%=request.getContextPath() %>/co/deleterecruit",
									method:"post",
									data:{
										ids:delIdList
									},
									complete:function(res){
										console.log(res)
										location.reload();
									}
								})
							});
						})
			});
		</script>
	</body>
</html>