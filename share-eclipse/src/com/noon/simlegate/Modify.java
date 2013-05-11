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

import com.noon.simlegate.db.DBOper;


@WebServlet("/Modify")
public class Modify extends HttpServlet {
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
    public Modify() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           doPost(request,response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		           
	}

}
