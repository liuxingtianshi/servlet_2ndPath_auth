<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BookDetail.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script type="text/javascript">
       function aa(){
        alert("ok");
       }
    </script>
  </head>
  
  <body>
  <table width="98%">
		<tr align="right">
			<td>			
			    <jsp:include page="mhead.jsp"></jsp:include>				
			</td>
		</tr>
		<tr>
			<td>
				<table border="1" width=100%>        			
       			<tr><td rowspan=3><img width=100 height=100 src="<%=basePath%>ImgSvl?isbn=${book.isbn}"/></td><td colspan=2 align=center style="color:red">${book.bname}</td></tr>
       			<tr><td>��Ʒ�۸�</td><td>${book.price}</td></tr>
       			<tr><td>������</td><td>${book.press}</td></tr>
       			<tr><td height=300 colspan=3>${book.descb}</td></tr>
       			<tr><td colspan=3 align=center><a href="<%=basePath%>user/ShopcarAddSvl?isbn=${book.isbn}">���빺�ﳵ</a> &nbsp; <a href="<%=basePath%>MainSvl">����</a></td></tr>		
      			
    		</table>
				
			</td>
		</tr>
		<tr height=100></tr>
		<tr align="center">
			<td>
				<!-- ע����Ϣ���� --> <a href="www.sina.com"> &lt;&lt;����>> </a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.51.cto">51cto</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.csdn.net">csdn </a></td>
		</tr>

	</table>

  </body>
</html>
