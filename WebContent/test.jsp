<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Request对象实现请求转发</title>
  </head>
  
  <body>
      使用普通方式取出存储在request对象中的数据：
      <h3 style="color:red;"><%=(String)request.getAttribute("data")%></h3>
     使用EL表达式取出存储在request对象中的数据：
     <h3 style="color:red;">${data}</h3>
     姓名
     <h3>${student.name} </h3>
     年龄
     <h1>${student.age} </h1>

  </body>
</html>