<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'PreCheckout.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
			function tijiao(){			
			   myForm.action = "<%=basePath%>user/CheckoutSvl";
			   myForm.submit();			
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
        <tr align="center">
           <td>
           <form id="myForm">
             <table border="1" width=100%> 
      			<tr><td>书名</td><td>作者</td><td>商品价格</td><td width="5%">数量</td></tr>		       
       			    
       			    <c:forEach var="bk" items="${books}"> 				
       					<tr><td>${bk.bname}</td><td>${bk.author}</td><td>${bk.price}</td><td >${bk.buycount}</tr>    
					</c:forEach>       			
      			    <tr><td colspan=4 align=center>账户余额：￥${user.account}  &nbsp;&nbsp;&nbsp;&nbsp; 商品总价：￥${allprice} <input type="hidden" name="allprice" value="${allprice}"> </td></tr>
      			    <tr><td colspan=4 align="center"><input type="button" onclick="tijiao()" value="付款确认"> &nbsp; <a href="<%=basePath%>MainSvl">返回</a></td></tr>
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
