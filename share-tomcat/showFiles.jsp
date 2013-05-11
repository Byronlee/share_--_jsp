<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="com.noon.simlegate.Common" %>
<%@ page import="com.noon.simlegate.FileDownload" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/byronlee_share.css" type="text/css"  rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<link rel="shortcut icon" href="images/go.png" type="image/x-icon" />
<script type="text/javascript" src="js/byronlee_share.js"></script>
<link href="css/byronlee_apprise.css" type="text/css"  rel="stylesheet">
<script type="text/javascript" src="js/byronlee_apprise.js"></script>
<%   
    
     HttpSession sion  = request.getSession();
     String username   =  sion.getAttribute("username").toString();
     String user_id    =  sion.getAttribute("user_id").toString();
     String change =null;
     try{
    	 change =   request.getAttribute("change").toString();
    	 
     }
     catch(NullPointerException e){
    	 
    	 e.printStackTrace();
     }
     Common common  = new Common();
     
     String path = getServletContext().getRealPath("upload");
%>
<title>我的网盘</title> 
<script language=JavaScript>    
$(document).ready(function(){
	 setInterval("nowtime()",1000);
});
       </script> 
</head>
<body>

<div class="show_left">
<p style="font: 28px 楷体 bolder;color: white;text-align: center;margin-top: 51px;">Share 网盘</p>
<div class="info">
<p>欢迎：<%=username %><br /></p>
<p class="ntime"> <script>nowtime()</script>	<br /></p>

</div>



<nav class="menu f14 b" id="navbox">

<a href="ShowFiles?access=public" id="share-manage" title="热门共享" hidefocus="true" <% if(change.equals("2")){ %>class="active" <% } %> >
<span class="ico ico-share">热门共享</span>
</a>

<a href="ShowFiles?id=<%=user_id %>" id="file-manage" hidefocus="true" title="我的文件" <% if(change.equals("1")){ %>class="active" <% } %>  >
<span class="ico ico-file-manage">我的文件</span>
</a>


<a href="Recycle" id="recycle-manage" title="回收站" hidefocus="true" <% if(change.equals("")||change==null||change==""){ %> class="active" <%} 

%> >
<span class="ico ico-trash">回收站</span>
</a>
</nav>
</div>
<div class="show_right">

<div class="show_right_top">
<a class="upload" href="#" onclick="Box.start()">上传文件</a><a href="#" class="logout" onclick="out()">退出</a>
</div>



<div class="file_content">
<div class="every">
    <div class="name">文件名</div><div class="down">下载</div><div class="opreat">操作</div><div class="date">日期</div><div class="big">状态</div><div class="big">大小</div>
</div>
<%
    ResultSet rs =(ResultSet)request.getAttribute("rs");
	if(rs!=null){
%>

	<%
	while(rs.next()){
		%>
		<div class="every-data">
		
		<div  class="name"><%= rs.getString(3) %></div>
		
		<div  class="down">
		  <a href="FileDownload?filename=<%= rs.getString(3) %>">下载</a> 
		<!--<a href="<%= "upload/"+rs.getString(3) %>">下载</a>  -->
		
		
		</div>
		<div  class="opreat">
		<%if(rs.getString(2).equals(user_id)){ %>
		<a href="javascript:void(0);" onclick="torecycle(<%= rs.getString(1)  %>,this)">移除</a>
		<%} %>
		</div>
		<div  class="date" style="font-size: 12px;"><%= rs.getString(7) %></div>
		<div  class="status" style="font-size: 12px;"><%= common.getAccessById(rs.getString(6)) %></div>
		<div  class="big"  style="font-size: 13px;"><%= common.formatSize(rs.getString(4)) %></div>
		</div>
	<%
	}
	%>
  <% }else{ %>    

	nothing
<% }%>


</div>

</div>












</body>
</html>