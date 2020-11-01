<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'BuyInfo.jsp' starting page</title>

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
		<tr align=right>
		   <td><jsp:include page="bhead.jsp"></jsp:include></td>			
		</tr>

		<tr>
			<td align=center><a href="<%=basePath%>back/BookAddSvl">新书上架</a> &nbsp; <a href="#">书增加库存</a>
				&nbsp; <a href="#">书下架</a> &nbsp; <a href="#">用户管理</a> &nbsp; <a
				href="#">修改售价</a> &nbsp; <a href="<%=basePath%>back/BuyinfoSvl">用户购买记录</a></td>
		</tr>
		<tr>
			<td align="left"><h2>购买记录</h2>
			</td>
		</tr>

		<!-- 查询条件 -->
		<tr>
			<td>
			  <form action="<%=basePath%>back/BuyinfoSvl" method="post">
					<table>
						<tr>
							<td align=left>用户名</td>
							<td><input type="text" name="uname" value="${uname}"/>
							</td>
						</tr>
						<tr>
							<td align=left>开始日期</td>
							<td><input type="text" class="easyui-datebox" name="beginDate" value="${beginDate}"/>
							</td>
							<td align=left>结束日期</td>
							<td><input type="text" class="easyui-datebox" name="endDate" value="${endDate}"/>
							</td>
							<td><input type=submit value="查询" />
							</td>
						</tr>
					</table>
				</form>
				</td>
		</tr>


		<tr>
			<td align=left>
				<table border="1" width=100%>
					<tr>
						<td>用户名</td>
						<td>书名--购买数量(本)</td>
						<td>书单价</td>
						<td>出版社</td>
						<td>作者</td>
						<td>购买日期</td>
						<td>总付款</td>
						<td>购书总数(本)</td>
					</tr>
                    
                    <c:forEach var="br" items="${buyinfos}">
						<tr>
							<td>${br.uname}</td>
							<td>${br.bname}--${br.count}</td>
							<td>${br.price}</td>
							<td>${br.press}</td>
							<td>${br.author}</td>
							<td>${br.buytime}</td>
							<td>${br.allmoney}</td>
							<td>${br.allcount}</td>
						</tr>
					</c:forEach>


					<tr>
						<td colspan=8>
							<table id="tblTurnPage" cellSpacing="0" cellPadding="1"
								width="100%" border="0"
								style="font-family:arial;color:red;font-size:12px;">
								<tr>
									<td>总记录数：${allRecordCount}</td>
									<td>总页数：${allPages}</td>
									<td>当前页：${currentPageNum}</td>
									<td><a href="<%=basePath%>back/BuyinfoSvl?page=1&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">首页|</a> <a
										href="<%=basePath%>back/BuyinfoSvl?page=${currentPageNum-1}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">《前页|</a> <a
										href="<%=basePath%>back/BuyinfoSvl?page=${currentPageNum+1}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">后页》|</a> <a
										href="<%=basePath%>back/BuyinfoSvl?page=${allPages}&uname=${uname}&beginDate=${beginDate}&endDate=${endDate}">末页|</a>
									</td>
									<td>跳转到:第<input type="text" size="3">页<input
										type="button" value="go">
									</td>
								</tr>
							</table></td>
					</tr>


				</table></td>
		</tr>
	</table>
</body>
</html>
