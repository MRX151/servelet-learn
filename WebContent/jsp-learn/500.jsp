<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isErrorPage="true"%>
<html>
	<head>
		<title>友好500页</title>
	</head>
	<body>
		对不起，出错了，请联系管理员解决！<br/>
		异常信息如下：<%=exception.getMessage() %>
	</body>
</html>