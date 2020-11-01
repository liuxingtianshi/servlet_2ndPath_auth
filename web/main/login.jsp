<%@ page language="java" import="java.util.*,java.io.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
	  
	</script>

  </head>
  
  <body>
  
     <table width="98%">        
        <tr align="right">
          <td ></td>
        </tr>
        <tr height="200"></tr>
        <tr align="center">
           <td>
              <form action="<%=basePath%>LoginSvl" method="post">
			     <table>	        
			          <tr height=50>
			              <td>用户名 ：</td><td><input type="text" name="uname" >
			              <span id="unameAlert" style="color:rgb(255,0,0);font-size:10px">${msg}</span></td>
			          </tr>
			          <tr height=50>
			              <td> 密码：</td><td><input type="password"  name="pwd"> <span id="unameAlert"></span></td>
			          </tr>
			          <tr align="center">
			              <td colspan="2"><input type="submit" value="提交"></td>
			          </tr>
			     </table>
		      </form>           
           </td>
        </tr>
        <tr height=200></tr>
        <tr align="center">
          <td>
             <a href="www.sina.com"> 新浪  </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         
             <a href="www.51.cto">51cto</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <a href="www.csdn.net">csdn</a>
          </td>
        </tr>
     
     </table>
  </body>
</html>
