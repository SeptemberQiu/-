<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="../base/header.jsp" %>
<title>文章管理</title>
<style type="text/css">
			.layui-form-switch {
				width: 55px;
			}			
			.layui-form-switch em {
				width: 40px;
			}
			body{overflow-y: scroll;}
		</style>
</head>
<body>
		<div class="weadmin-nav">
			<span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">文章管理</a>
        <a>
          <cite>文章列表</cite></a>
      </span>
			<a class="layui-btn layui-btn-sm" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
				<i class="layui-icon" style="line-height:30px">&#x1002;</i></a>
		</div>
		<div class="weadmin-body">
			<!-- <div class="layui-row">
				<form class="layui-form layui-col-md12 we-search">
					文章搜索：
					<div class="layui-input-inline">
						<select name="cateid">
							<option>请选择分类</option>
							<option>文章</option>
							<option>会员</option>
							<option>权限</option>
						</select>
					</div>
					<div class="layui-inline">
						<input class="layui-input" placeholder="开始日" name="start" id="start">
					</div>
					<div class="layui-inline">
						<input class="layui-input" placeholder="截止日" name="end" id="end">
					</div>
					<div class="layui-inline">
						<input type="text" name="keyword" placeholder="请输入关键字" autocomplete="off" class="layui-input">
					</div>
					<button class="layui-btn" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
				</form>
			</div> -->
			<div class="weadmin-block demoTable">
				<button class="layui-btn layui-btn-danger" data-type="getCheckData"><i class="layui-icon">&#xe640;</i>批量删除</button>
				<!-- <button class="layui-btn" data-type="Recommend"><i class="layui-icon">&#xe6c6;</i>推荐</button>
				<button class="layui-btn" data-type="Top"><i class="layui-icon">&#xe619;</i>置顶</button>
				<button class="layui-btn" data-type="Review"><i class="layui-icon">&#xe6b2;</i>审核</button> -->
				<a class="layui-btn" href="<%=request.getContextPath()%>/admin/article/addarticle"><i class="layui-icon">&#xe61f;</i>添加</a>
				<span class="fr" style="line-height:40px">共有数据：88 条</span>
			</div>
			<table class="layui-hide" id="articleList"></table>
			
			<table class="layui-table">
				<thead>
					<tr>
						<th>
							<div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
						</th>
						<th>ID</th>
						<th>标题</th>
						<th>作者</th>
						<th>发布时间</th>
						<th>操作</th>
				</thead>
				<tbody>
				<!-- 循环 -->
				<c:forEach items="${articlelist}" var="item" varStatus="i">
					
					<tr>
						<td>
							<div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${item.id}'><i class="layui-icon">&#xe605;</i></div>
						</td>
						<td>${item.id}</td>
						<td>${item.title }</td>
						<td>${item.author}</td>
						<td>${item.time}</td>
						
						<td class="td-manage">
							<a title="编辑" target="_blank" onclick="WeAdminShow('编辑','./edit.html')" href="<%=request.getContextPath()%>/admin/edituser?username=${item.username}">
								<i class="layui-icon">&#xe642;</i>
							</a>
							<%-- <a title="删除"  onclick="member_del(this,'要删除的id')" href="<%=request.getContextPath()%>/admin/deleteuser?id=${item.id}"> --%>
							<span title="删除" class="delbtn" data-id="${item.id}" data-username="${item.username}">
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
					<a class="prev" href="<%=request.getContextPath() %>/admin/userlist?page=${page-1}">&lt;&lt;</a>
				</c:if>
					<span class="current">${page}</span>
					<c:if test="${(page+1)<=allpage}">
						<a class="num" href="<%=request.getContextPath() %>/admin/userlist?page=${page+1}">${page+1 }</a>
					</c:if>
					
					<c:if test="${(page+2)<allpage}">
						<a class="num" href="<%=request.getContextPath() %>/admin/userlist?page=${page+2}">${page+2 }</a>
					</c:if>
					
					<c:if test="${(page+1)<allpage}">
						<a class="num" href="<%=request.getContextPath() %>/admin/userlist?page=${allpage}">${allpage }</a>
					</c:if>
					<c:if test="${(page+1)<=allpage}">
						<a class="next" href="<%=request.getContextPath() %>/admin/userlist?page=${page+1}">&gt;&gt;</a>
					</c:if>
				</div>
			</div>
			

		</div>
	</body>

</html>