<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

              <c:if test="${user != null}">   
                 	欢迎：${user.uname}
                 	<a href="<%=basePath%>user/ShopCarSvl">购物车</a>
					<a href="<%=basePath%>LogoutSvl">退出</a>
				</c:if> 
				<c:if test="${user == null}">
					<a href="<%=basePath%>/LoginSvl">登录</a>
				</c:if>
				<c:if test="${user.role == 1}">
				   <a href="<%=basePath%>back/BookAddSvl">后台</a>
				</c:if> 
