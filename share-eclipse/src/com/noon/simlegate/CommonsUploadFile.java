package com.noon.simlegate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.noon.simlegate.db.DBOper;

@WebServlet("/CommonsUploadFile")
public class CommonsUploadFile extends HttpServlet {
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
	   
   
    public CommonsUploadFile() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	               doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	               request.setCharacterEncoding("UTF-8");
		           response.setContentType("text/html;charset=UTF-8");
		           DBOper db = new DBOper();
		           PrintWriter out = response.getWriter();
		           String fileName = null;
		           String access = null;
		           String message = "";
		           String size = null;
		           HttpSession session = request.getSession();
		           String user_id = session.getAttribute("user_id").toString();
		           boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		           if(isMultipart){
		        	   DiskFileItemFactory factory = new DiskFileItemFactory();
		        	   // 设置在内存中允许存储的字节数
		        	   factory.setSizeThreshold(20*1024);
		        	   // 设置存放临时文件的目录
		        	   factory.setRepository(factory.getRepository());
		        	   // 设置新的上传文件句柄
		        	   ServletFileUpload upload = new ServletFileUpload(factory);
		        	   List<FileItem> fileItems = null;
		        	   try{
		        		   // 从request 得到所有上传域的列表
		        		   fileItems = upload.parseRequest(request);
		        	   }
		        	   catch(FileUploadException e){
		        		   e.printStackTrace();
		        		   }
		        	   // 创建迭代器进行列表的遍历
		        	   Iterator iter = fileItems.iterator();
		        	  while(iter.hasNext()){
		        		  FileItem item = (FileItem)iter.next();
		        		  System.out.println(item.getFieldName());
		        		  //判断文本表单字段还是文件表单字段
		        		  if(!item.isFormField()){
		        			  // 获得文件路径
		        			  String filePath = item.getName();
		                      
		        			  if(item.getSize()>20*1024*1024){
		        				  message = "1";
		        			  }
		        			  else{
		        				  size = Long.toString(item.getSize());
		        				  fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
		        				  String saveDir = "upload";
		        				  String path = getServletContext().getRealPath(saveDir)+"\\"+ fileName;
		        				  System.out.println(path);
			        			  //String newPath = "upload\\" + fileName;
			        			  File file = new File(path);
			        			  // add
                                 try{
                                	item.write(file);
                                 }	
             		   		    catch(Exception e){
             		   		    	e.printStackTrace();
             		   		    }
		        			  }
		        			  //request.setAttribute("uploadMsg", message);
		        		  }
		        		  
		        		  else {
		        			  if(item.getFieldName().equals("access")){
		        				  access = item.getString();
		        			  }
		        		  }
		        	  }
		        	  try{
                          
                          	db.getConn(server, dbname, user, pwd);
                          	Common common = new Common();
               		    	String preparedSql = "INSERT INTO uploadfile(user_id,filename,size,access,time) VALUES(?,?,?,?,?)";
               		    	int result = db.executeUpdate(preparedSql, new String[]{user_id,fileName,size,access,common.getCurrentTime()});
               		    	if(result==1){
               		    		message = "2";
               		    		//out.println("successfully insert !");
               		    	}
               		    	else{
               		    		message = "3";
               		    		//out.println("failure to insert !");
               		    	}
                           }	
                           catch(ClassNotFoundException e){
       		   		    	e.printStackTrace();
       		   		    }
       		   		    catch(Exception e){
       		   		    	e.printStackTrace();
       		   		    }
		        	
		           }
		          
		           out.println(message);
		           out.flush();
		           out.close();
	}

}
