<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'CheckoutOK.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

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
               <table border="1" width=100%> 
			    <tr><td align=center colspan=4><font color="red;size=18">����ɹ������ǻᾡ��Ϊ���������� </font><br>��������Ϊ���Ĺ�����Ϣ </td></tr>
      			<tr><td>����</td><td>����</td><td>��Ʒ�۸�</td><td width="5%">����</td></tr>		       
       			     				
       				<tr><td>�����</td><td>˾����</td><td>38.5</td><td >5��</tr>    
					
					<tr><td>ս�����ƽ</td><td>�ж�˹̩</td><td>26.3</td><td>1��</td></tr>   
       			
      			    <tr><td colspan=4 align=center>�˻�����${usercount}  &nbsp;&nbsp;&nbsp;&nbsp; ��Ʒ�ܼۣ���${allprice}</td></tr>
      			    <tr><td colspan=4 align=center> <a href="<%=basePath%>MainSvl">������ҳ</a></td></tr>
    		</table>
           </td>
        </tr>
        <tr height=200></tr>
        <tr align="center">
          <td>
             <a href="www.sina.com"> ����  </a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;         
             <a href="www.51.cto">51cto</a>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             <a href="www.csdn.net">csdn</a>
          </td>
        </tr>
     
     </table>
  </body>
</html>
