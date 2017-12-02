<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>这是个测试页面</title>
  </head>
  
  <body>
    <form action="<%=request.getContextPath()%>/user.do" method="post">
    	<input type="text" name ="name" value="admin">
    	<input type="text" name ="pass" value="pass">
    	<input type ="submit">
    </form>
  </body>
</html>
