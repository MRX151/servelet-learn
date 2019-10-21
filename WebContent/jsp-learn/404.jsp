<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>404错误友好提示页面</title>
    <!-- 5秒钟后自动跳转回首页 -->
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/index.jsp">
    <script type="text/javascript">
    	
    </script>
  </head>
  <body>
    <img alt="对不起，你要访问的页面没有找到，请联系管理员处理!" 
    src="${pageContext.request.contextPath}/static/image/404.jpg"/><br/>
    <h4>5秒钟后自动跳转回首页，如果没有跳转，请点击<a href="${pageContext.request.contextPath}/index.jsp">这里</a></h4>
  </body>
</html>