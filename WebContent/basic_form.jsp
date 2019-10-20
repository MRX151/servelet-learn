<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Form表单</title>
    <script type="text/javascript">
    	var isCommitted = false;
    	function doCommit(){
    		if(false == isCommitted){
    			isCommitted = true;
    			return true;
    		}else {
				return false;
			}
    	}
    </script>
  </head>
  
  <body>
      <form action="${pageContext.request.contextPath}/sleep_form" onsubmit="return doCommit()" method="post">
        用户名：<input type="text" name="username">
        <input type="submit" value="提交" id="submit">
    </form>
  </body>
</html>