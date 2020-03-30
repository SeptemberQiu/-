<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="base/header.jsp" %>
<title>企业后台首页</title>

	</head>

	<body>
		<!-- 顶部开始 -->
		<div class="container">
			<div class="logo">
				<a href="./index">XXX招聘网站</a>
			</div>
			<div class="left_open">
				<!-- <i title="展开左侧栏" class="iconfont">&#xe699;</i> -->
				<i title="展开左侧栏" class="layui-icon layui-icon-shrink-right"></i>
				
			</div>
			<ul class="layui-nav left fast-add" lay-filter="">

			</ul>
			<ul class="layui-nav right" lay-filter="">
				<li class="layui-nav-item">
					<a href="javascript:;">${username}</a>
					<dl class="layui-nav-child">
						<!-- 二级菜单 -->
						<dd>
							<a onclick="WeAdminShow('个人信息','./userinfo')">个人信息</a>
						</dd>
						<!-- <dd>
							<a onclick="./login">切换帐号</a>
						</dd> -->
						<dd>
							<a class="loginout" href="./loginout">退出</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item to-index">
					<a href="">前台首页</a>
					<%-- <a href="<%=request.getContextPath() %>">前台首页</a> --%>
				</li>
			</ul>

		</div>

		<div class="left-nav">
			<div id="side-nav">
				<ul id="nav">
					<li>
						<a href="javascript:;">
							<i class="iconfont">&#xe6b8;</i>
							<cite>企业招聘信息管理</cite>
							<i class="iconfont nav_right">&#xe697;</i>
						</a>
						<ul class="sub-menu">
							<li>
								<a _href="<%=request.getContextPath() %>/co/recruitlist">
									<i class="iconfont">&#xe6a7;</i>
									<cite>企业招聘信息列表</cite>
								</a>
							</li>
							
						</ul>
					</li>

				</ul>
			</div>
		</div>

		<div class="page-content">
			<div class="layui-tab tab" lay-filter="wenav_tab" id="WeTabTip" lay-allowclose="true">
				<ul class="layui-tab-title" id="tabName">
					<li>欢迎页</li>
				</ul>
				<div class="layui-tab-content">
					<div class="layui-tab-item layui-show">
						<h1>欢迎您的使用。点击左侧相关栏目进行对应操作。</h1>
					</div>
				</div> 
			</div>
		</div>
		<div class="page-content-bg"></div>
		<!-- 右侧主体结束 -->
		<!-- 中部结束 -->
		<!-- 底部开始 -->
		<div class="footer">
			<div class="copyright">Copyright ©202018 XXX招聘网站    All Rights Reserved</div>
		</div>
		<!-- 底部结束 -->
		<script type="text/javascript">

			layui.config({
				<%-- base: '<%=request.getContextPath() %>/static/js/' --%>
			  base: '<%=request.getContextPath() %>/static/js/'
			  ,version: '101100'
			}).use('admin');


		</script>
	</body>
	<!--Tab菜单右键弹出菜单-->
	<ul class="rightMenu" id="rightMenu">
        <li data-type="fresh">刷新</li>
        <li data-type="current">关闭当前</li>
        <li data-type="other">关闭其它</li>
        <li data-type="all">关闭所有</li>
    </ul>

</html>