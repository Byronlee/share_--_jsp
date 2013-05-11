<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<title>BYRONLEESHARE</title>
</head>
<body class="bg">
<%
      HttpSession sion = request.getSession();

      String username = null;
      
      try{
    	  username   =  sion.getAttribute("username").toString();
      }
      catch(NullPointerException e){
    	  e.printStackTrace();
    	  
      }
      if(username!=null&&username!=""){
    	     response.sendRedirect("ShowFiles?access=public");
    	  
      }
%>
 <div class="login_all">
   <div class="logo">
      <img src="images/logo.png"/>
      <div>
      <p>Share</p>   
      </div>
   </div>
   <form action="CheckLogin" method="post"  class="login_form_1" >
     <label id="uname_label">Username</label>
     <input type="text" name="username" id="log_text" class="username"/><br /><br />
    <label id="pass_label">Password</label>
     <input type="password" name="password" id="log_text" class="password"/><br /><br />
     <input type="submit" value="Login" class="login-btn" onclick="return checklogin()">
     <input type="button" value="Rigster" class="login-btn" onclick="register()" style="float: right;margin-right: 15px;">
   </form>
   </div>
</body>
</html>