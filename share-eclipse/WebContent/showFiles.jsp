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
    	 change =   request.getAttribute("change").toString();s
     }
     catch(NullPointerException e){
    	 
    	 e.printStackTrace();
     }
     Common common  = new Common();
     
     String path = getServletContext().getRealPath("upload");
%>
<title>�ҵ�����</title> 
<script language=JavaScript>    
$(document).ready(function(){
	 setInterval("nowtime()",1000);
});
       </script> 
</head>
<body>

<div class="show_left">
<p style="font: 28px ���� bolder;color: white;text-align: center;margin-top: 51px;">Share ����</p>
<div class="info">
<p>��ӭ��<%=username %><br /></p>
<p class="ntime"> <script>nowtime()</script>	<br /></p>

</div>



<nav class="menu f14 b" id="navbox">

<a href="ShowFiles?access=public" id="share-manage" title="���Ź���" hidefocus="true" <% if(change.equals("2")){ %>class="active" <% } %> >
<span class="ico ico-share">���Ź���</span>
</a>

<a href="ShowFiles?id=<%=user_id %>" id="file-manage" hidefocus="true" title="�ҵ��ļ�" <% if(change.equals("1")){ %>class="active" <% } %>  >
<span class="ico ico-file-manage">�ҵ��ļ�</span>
</a>


<a href="Recycle" id="recycle-manage" title="����վ" hidefocus="true" <% if(change.equals("")||change==null||change==""){ %> class="active" <%} 

%> >
<span class="ico ico-trash">����վ</span>
</a>
</nav>
</div>
<div class="show_right">

<div class="show_right_top">
<a class="upload" href="#" onclick="Box.start()">�ϴ��ļ�</a><a href="#" class="logout" onclick="out()">�˳�</a>
</div>



<div class="file_content">
<div class="every">
    <div class="name">�ļ���</div><div class="down">����</div><div class="opreat">����</div><div class="date">����</div><div class="big">״̬</div><div class="big">��С</div>
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
		  <a href="FileDownload?filename=<%= rs.getString(3) %>">����</a> 
		<!--<a href="<%= "upload/"+rs.getString(3) %>">����</a>  -->
		
		
		</div>
		<div  class="opreat">
		<%if(rs.getString(2).equals(user_id)){ %>
		<a href="javascript:void(0);" onclick="torecycle(<%= rs.getString(1)  %>,this)">�Ƴ�</a>
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