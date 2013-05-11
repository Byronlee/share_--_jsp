package com.noon.simlegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.noon.simlegate.db.DBOper;
@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	ServletContext ctx;
   	String server;
   	String dbname;
   	String user;
   	String pwd;
       public void init(){
       	ctx = this.getServletContext();
       	server = ctx.getInitParameter("server");
       	dbname = ctx.getInitParameter("dbname");
       	user = ctx.getInitParameter("user");
       	pwd = ctx.getInitParameter("password");
       }
       
       public CheckLogin() {
           super();
       }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    doGet(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    response.setContentType("text/html;charset=UTF-8");
		    ServletContext ctx = this.getServletContext();
		    PrintWriter out = response.getWriter();
		    String server = ctx.getInitParameter("server");
		    String dbname = ctx.getInitParameter("dbname");
		    String user   = ctx.getInitParameter("user");
		    String pwd    = ctx.getInitParameter("password");
		    String username = request.getParameter("username").trim();
		    String password = request.getParameter("password").trim();
		    DBOper db = new DBOper();
		    try{
		    	db.getConn(server, dbname, user, pwd);
		    	String preparedSql = "SELECT * FROM user WHERE username=? AND password = ?";
		    	ResultSet rs = db.executeQuery(preparedSql, new String[]{username,password});
		    	if(rs!=null && rs.next()){
		    		HttpSession session = request.getSession();
		    		session.setAttribute("user_id", rs.getString(1));
		    		session.setAttribute("username",username);
		    		//response.sendRedirect("ShowFiles?access=public");
		    		out.write("1");
		    	}
		    	else{
		    		out.write("2");
		    	}
		    	out.flush();
		    	out.close();
		    }
		    catch(ClassNotFoundException e){
		    	e.printStackTrace();
		    }
		    catch(Exception e){
		    	e.printStackTrace();
		    }
	}

}
