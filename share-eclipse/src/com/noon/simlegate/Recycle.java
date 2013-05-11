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

@WebServlet("/Recycle")
public class Recycle extends HttpServlet {
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
    public Recycle() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("text/html;charset=UTF-8");
          HttpSession session = request.getSession();
          PrintWriter out = response.getWriter();
          String username;
	           try{
	        	   username = session.getAttribute("username").toString();
	           }
	           catch(NullPointerException e){
	        	   out.println("<script>alert('你没有还没有登录,将为你跳转到登录界面'); window.location.href='login.jsp';</script>");
	        	   //out.println("<a href='login.jsp'>登录</a>");
	           }
		  DBOper db =  new DBOper();    
		 
		  try{
		            	db.getConn(server, dbname, user, pwd);
		            	String preparedSql = "SELECT * FROM uploadfile WHERE user_id = ? AND isdel = 1 ORDER BY id DESC";
		            	
		            	String user_id = session.getAttribute("user_id").toString();
		            	ResultSet rs = db.executeQuery(preparedSql, new String[]{user_id});
		            	//if(rs!=null && rs.next()){
		            		request.setAttribute("rs", rs);
		            		rd = request.getRequestDispatcher("recycle.jsp");
		            		rd.forward(request, response);
		            	//}
		            	
		             }
          catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		  catch (Exception e) {

			e.printStackTrace();
		}
		finally{
			db.closeAll();
		}
		             
	}

}
