<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

              <c:if test="${user != null}">   
                 	��ӭ��${user.uname}
                 	<a href="<%=basePath%>user/ShopCarSvl">���ﳵ</a>
					<a href="<%=basePath%>LogoutSvl">�˳�</a>
				</c:if> 
				<c:if test="${user == null}">
					<a href="<%=basePath%>/LoginSvl">��¼</a>
				</c:if>
				<c:if test="${user.role == 1}">
				   <a href="<%=basePath%>back/BookAddSvl">��̨</a>
				</c:if> 
