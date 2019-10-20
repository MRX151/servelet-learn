<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>登录页</title>
<style type="text/css">
	.form-card {
		margin:8px;
		font-size: large;
		
	}
	.form-card a{
		padding-left: 10px;
	}
	.form-card input {
		padding-left:5px;
		margin-left:10px;
	}
	.form-card img {
		padding-top:5px;
	}
</style>
<script type="text/javascript">

	window.onload = function(){
		document.getElementById("input_code").value="";
	}

	function generate(){
		//alert("重新生成图片！");
		document.getElementById("code").src="/servelet-learn/code_image?"+Math.random();
	}
</script>
</head>
<body>
	<h1>这是登录页</h1>
	<form action="/servelet-learn/validate_code" class="form-card">
		<span>验证码:</span>
		<input id="input_code" name="checkcode" type="text" value=""/>
		<input type="submit" value="提交"/>
		<a onclick="generate()" href="javascript:void(0)">看不清楚，换一张</a></br>
		<img id="code" alt="验证码" src="/servelet-learn/code_image">		
	</form>	
</body>

</html>