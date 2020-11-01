<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
                <c:if test="${user != null}">   
                 	»¶Ó­£º${user.uname}                 	
					<a href="<%=basePath%>LogoutSvl">ÍË³ö</a>
				</c:if> 
				<c:if test="${user == null}">
					<a href="<%=basePath%>/LoginSvl">µÇÂ¼</a>
				</c:if>				
				<a href="<%=basePath%>MainSvl">Ç°Ì¨</a>
				 
