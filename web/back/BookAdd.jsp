<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
    <table align="center" width=90%>
    	 <tr>
      	<td align=right>
      	    <jsp:include page="bhead.jsp"></jsp:include>  	  	
      	</td>       	
      </tr>
      <tr>
      	<td align=center>
      	    <a href="#">�����ϼ�</a> &nbsp;  <a href="#">�����ӿ��</a>  &nbsp;  <a href="#">���¼�</a> &nbsp;  <a href="#">�û�����</a>
      	    &nbsp;  <a href="#">�޸��ۼ�</a> &nbsp; <a href="<%=basePath%>back/BuyinfoSvl">�û������¼</a>
      	</td>
      	</tr>    	
      <tr><td align="left"><h2>�����ϼ�</h2></td></tr>
      <tr>
      	<td>
      	<form action="<%=basePath%>back/BookAddSvl" method="post" enctype="multipart/form-data">
      		<table border="0" width=60% align="center">  		
      		
      			<tr><td>���ISBN</td><td><input type="text" name="isbn"/></td></tr>
       			<tr><td>����</td><td><input type="text" id="bname" name="bname"/><span id="NameNull"></span></td></tr>
       			<tr><td>����</td><td><input type="text" name="author"/></td></tr>
       			<tr><td>������</td><td><input type="text" name="press"/></td></tr>
       			<tr><td>��������</td><td><input type="text" name="pubdate" class="easyui-datebox"/></td></tr>
       			<tr><td>�۸�</td><td><input type="text" name="price"/></td></tr>
       			<tr><td>ͼƬ�ϴ�</td><td><input type="file" name="pic"/></td></tr>
       			<tr><td colspan=2 align=center><input type=submit value=�ύ /><span style="color:red;font-size:12px">${msg}</span></td></tr>
    		</table>
    	</form>
      	</td>
      </tr>
    </table>
  </body>

</html>
