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

		// ��� Mozilla��������Ĵ��룺
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		}else if(window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}		
	}
	
	function stateChange222()
	{
		// ��� xmlhttp ��ʾΪ "loaded"
		if (xmlhttp.readyState==4)
		  {
			  // ���Ϊ "OK"
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
		
		loadXmlObject();                                //����xmlHttpRequest����
		if (xmlhttp!=null)
  		{  		   
		   xmlhttp.onreadystatechange=stateChange222;   //ע��ص��������ѱ��صĺ������߷�������������������������������֪ͨ�����ߣ�
		   var uname = document.getElementById("uname").value;
		   var url = "<%=basePath%>RegSvl?uname=" + uname;   
		   
 		   xmlhttp.open("GET",url,true);
		   xmlhttp.send(null);
		}
		else
		{
		    alert("�����������֧��XMLHTTP");
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
							<td>�û���</td>
							<td><input type="text" id="uname" name="uname" onblur="checkUserName()">
								<span id="namealert" style="color:red;font-size:12px"></span></td>
						</tr>
						<tr>
							<td>����</td>
							<td><input type="password" name="pwd"></td>
						</tr>
						<tr>
							<td>����ȷ��</td>
							<td><input type="password" name="pwd2"></td>
						</tr>
						<tr>
						<tr>
							<td>�˻���ֵ</td>
							<td><input type="text" name="account"></td>
						</tr>
						<tr>
							<td colspan=2><input type="submit" value="�ύ"> <span
								style="color:red;font-size:12px">${msg}</span>
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr height=200></tr>
		<tr align="center">
			<td><a href="www.sina.com"> ���� </a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.51.cto">51cto</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="www.csdn.net">csdn</a>
			</td>
		</tr>

	</table>


</body>
</html>
