<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'register2.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="<%=basePath%>js/jquery-1.6.min.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	function checkUserName() {
       var uname = document.getElementById("uname").value;
       
		$.ajax({
			type : "get",
			url : "<%=basePath%>RegSvl",
			data : "uname=" + uname,
			success : function(msg3) {
				document.getElementById("namealert").innerHTML = msg3;
			}
		});

	}
</script>

</head>

<body>
	<table width="98%">
		<tr align="right">
			<td><jsp:include page="mhead.jsp"></jsp:include></td>
		</tr>
		<tr height="200"></tr>
		<tr align="center">
			<td>
				<form action="<%=basePath%>RegSvl" method="post">
					<table>
						<tr>
							<td>用户名</td>
							<td><input type="text" id="uname" name="uname"
								onblur="checkUserName()"> <span id="namealert"
								style="color:red;font-size:12px"></span>
							</td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="password" name="pwd">
							</td>
						</tr>
						<tr>
							<td>密码确认</td>
							<td><input type="password" name="pwd2">
							</td>
						</tr>
						<tr>
						<tr>
							<td>账户充值</td>
							<td><input type="text" name="account">
							</td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" value="提交"> <span
								style="color:red;font-size:12px">${msg}</span></td>
						</tr>
					</table>
				</form></td>
		</tr>
		<tr height=200></tr>
		<tr align="center">
			<td><a href="www.sina.com"> 新浪 </a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.51.cto">51cto</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.csdn.net">csdn</a></td>
		</tr>

	</table>

</body>
</html>
