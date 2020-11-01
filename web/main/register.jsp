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

<title>My JSP 'register.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
	var xmlhttp;

	function loadXmlObject() {
		xmlhttp = null;

		// 针对 Mozilla等浏览器的代码：
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		}else if(window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}		
	}
	
	function stateChange222()
	{
		// 如果 xmlhttp 显示为 "loaded"
		if (xmlhttp.readyState==4)
		  {
			  // 如果为 "OK"
			  if (xmlhttp.status==200)
			    {
			          var regresult = xmlhttp.responseText;
			          document.getElementById("namealert").innerHTML = regresult;
			    }
			  else
			    {
			    alert("Problem retrieving XML data")
			    }
		  }
	}
	

	function checkUserName() {
		
		loadXmlObject();                                //创建xmlHttpRequest对象
		if (xmlhttp!=null)
  		{  		   
		   xmlhttp.onreadystatechange=stateChange222;   //注册回调函数（把本地的函数告诉服务器，当服务器处理完后，用这个函数通知调用者）
		   var uname = document.getElementById("uname").value;
		   var url = "<%=basePath%>RegSvl?uname=" + uname;   
		   
 		   xmlhttp.open("GET",url,true);
		   xmlhttp.send(null);
		}
		else
		{
		    alert("您的浏览器不支持XMLHTTP");
		}
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
							<td><input type="text" id="uname" name="uname" onblur="checkUserName()">
								<span id="namealert" style="color:red;font-size:12px"></span></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="password" name="pwd"></td>
						</tr>
						<tr>
							<td>密码确认</td>
							<td><input type="password" name="pwd2"></td>
						</tr>
						<tr>
						<tr>
							<td>账户充值</td>
							<td><input type="text" name="account"></td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" value="提交"> <span
								style="color:red;font-size:12px">${msg}</span>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr height=200></tr>
		<tr align="center">
			<td><a href="www.sina.com"> 新浪 </a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.51.cto">51cto</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.csdn.net">csdn</a>
			</td>
		</tr>

	</table>


</body>
</html>
