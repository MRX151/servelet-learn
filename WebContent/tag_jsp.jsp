<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>使用字符过滤器解决解决get、post请求方式下的中文乱码问题</title>
  </head>
  
  <body>
       <%--使用post方式提交表单 --%>
       <form action="${pageContext.request.contextPath}/basic_do_servlet" method="post">
           	用户名：<input type="text" name="username" value="孤傲苍狼" />
           <input type="submit" value="post方式提交">
       </form>
       
  </body>
</html>