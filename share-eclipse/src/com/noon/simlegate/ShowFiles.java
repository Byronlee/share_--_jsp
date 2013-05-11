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

@WebServlet("/ShowFiles")
public class ShowFiles extends HttpServlet {
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
    public ShowFiles() {
        super();
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           doPost(request,response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	           String username=null;
		           response.setContentType("text/html;charset=UTF-8");
		           PrintWriter out = response.getWriter();
		           String user_id  = request.getParameter("id");
		           String access   = request.getParameter("access");
		           HttpSession session = request.getSession();
	//	           username = session.getAttribute("username").toString();
		           try{
		        	   username = session.getAttribute("username").toString();
		           }
		           catch(NullPointerException e){
		        	   out.println("<script>alert('你没有还没有登录,将为你跳转到登录界面'); window.location.href='login.jsp';</script>");
		        	   //out.println("<a href='login.jsp'>登录</a>");
		           }
		          
		           DBOper db = new DBOper();
		           if(username!=""&&username!=null){
		        	   try{
		   		    	db.getConn(server, dbname, user, pwd);
		   		    	String preparedSql = "SELECT * FROM uploadfile WHERE 1=1 AND isdel = 0 ";
		   		    	if(user_id!=null){
		   		    		preparedSql +="AND user_id = '"+user_id+"'";
		   		    		request.setAttribute("change", "1");
		   		    	}
		   		    	if(access!=null&&access.equals("public")){
		   		    		preparedSql +="AND access = '2' OR access = '3'";
		   		    		request.setAttribute("change", "2");
		   		    	}
		   		    	preparedSql += " ORDER BY id DESC";
		   		    	System.out.println(preparedSql);
		   		    	ResultSet rs = db.executeQuery(preparedSql, new String[]{});
		   		    	request.setAttribute("rs", rs);
		   		    	rd = request.getRequestDispatcher("/showFiles.jsp");
		   		    	rd.forward(request, response);
		   		    }
		   		    catch(ClassNotFoundException e){
		   		    	e.printStackTrace();
		   		    }
		   		    catch(Exception e){
		   		    	e.printStackTrace();
		   		    }
		        	 finally{
		        		 db.closeAll();
		        	 }      
		        	   
		           }
//		           else{
//		        	   out.println("please login first");
//		        	   out.println("<a href='login.html'>login</a>");
//		        	   
//		           }
	}

}
