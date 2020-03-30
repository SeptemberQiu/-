<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${title}</title>
<style>
	.main{
		width:600px;
		height:600px;
		margin:0 auto;
		display:flex;
		justify-content:center;
		align-items:center;
		flex-direction:column;
	}
</style>
</head>
<body>
	<div class="main">
		<h3>${title}</h3>
		<p>${info}</p>
		<img src="<%=request.getContextPath()%>/staitc/images/loading.gif" />
		<p>
			<span>3秒</span>后自动跳转
		</p>
	</div>

	<!-- 3秒后自动跳转 -->
	<script>
		var num= 3;
		setInterval(function(){
			if(num == 0 ){
				location.href = "<%=request.getContextPath()%>${httpUrl}";
			}else{
				num--;
				var spanDom = document.querySelector("span");
				spanDom.innerHTML = num;
			}
		},1000)
	</script>
</body>
</html>