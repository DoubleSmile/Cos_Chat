<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script src="/Cos_Chat/js/jquery-2.1.3.js"></script>
  <script type="text/javascript">
    $(document).ready(
    		function(){
   			 var json={"chatterName":"德玛西亚","identifier":"DEMa"};
   	       $.ajax({
   			type: "post",
   			contentType:"application/json",
   			url: "/Cos_Chat/openChat",
   			data: JSON.stringify(json),
   			success: function(data){
   		    	$("response").html(JSON.stringify(json));
   			}
   		});
         
    });
    
  </script>
  
  <body>
    This is my JSP page. <br>
    <p class="response"></p>
  </body>
  
</html>
