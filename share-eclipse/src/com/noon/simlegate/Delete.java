package com.noon.simlegate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.noon.simlegate.db.DBOper;


@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ServletContext ctx;
	String server;
	String dbname;
	String user;
	String pwd;
	RequestDispatcher rd;
    public void init(){
    	ctx = this.getServletContext();
    	server = ctx.getInitParameter("server");
    	dbname = ctx.getInitParameter("dbname");
    	user = ctx.getInitParameter("user");
    	pwd = ctx.getInitParameter("password");
    }
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		     doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();       
		 String file_id = null;
		 String type = null;
		      try{
		        	file_id = request.getParameter("id");
		        	type = request.getParameter("type");
		        	
		           }
		           catch(NullPointerException e){
		        	   out.println("参数错误");
		           }
		      DBOper db = new DBOper();
		      if(file_id!=""&&file_id!=null){
		    	  try{
		   		    	db.getConn(server, dbname, user, pwd);
		   		    	String preparedSql;
		   		    	String message;
		   		    	if(type!=null&&type.equals("complete")&&file_id!=null){
		   		    		preparedSql  ="DELETE FROM uploadfile WHERE id = ? AND isdel = 1";
		   		    		
		   		    	}
		   		    	else if(type!=null&&type.equals("cycle")&&file_id!=null){
		   		    		preparedSql = "UPDATE uploadfile SET isdel = '0' WHERE id = ?"; 
		   		    	
		   		    	}
		   		    	else{
		   		    		
		   		    		preparedSql = "UPDATE uploadfile SET isdel = '1' WHERE id = ?"; 
		   		    	}
         		    	int result = db.executeUpdate(preparedSql, new String[]{file_id});
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

}
