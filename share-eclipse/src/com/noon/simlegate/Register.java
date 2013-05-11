package com.noon.simlegate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noon.simlegate.db.DBOper;


@WebServlet("/Register")
public class Register extends HttpServlet {
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
    public Register() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		                String username = request.getParameter("username");
		                String password = request.getParameter("password");
		                PrintWriter out = response.getWriter();
		                DBOper db = new DBOper();
		                try{
			   		    	db.getConn(server, dbname, user, pwd);
			   		    	Common common = new Common();
			   		    	String preparedSql = "INSERT INTO user(username,password,time) VALUES(?,?,?)";
               		    	int result = db.executeUpdate(preparedSql, new String[]{username,password,common.getCurrentTime()});
               		    	if(result==1){
               		    		out.println("1");
               		    	}
               		    	else{
               		    		out.println("2");
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
