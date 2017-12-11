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
    <form action="<%=request.getContextPath()%>/loginController/login" method="post">
    	<input type="text" name ="name" value="admin">
    	<input type="text" name ="password" value="password">
    	<input type ="submit">
    </form>
    <body>
    <form action="<%=request.getContextPath()%>/loginController/regist" method="post">
        <input type="text" name ="name" value="admin">
        <input type="text" name ="password" value="password">
        <input type ="submit" value="提交并返回json">
    </form>
  </body>
</html>
