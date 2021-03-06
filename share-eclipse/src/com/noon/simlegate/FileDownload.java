package com.noon.simlegate;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDownload")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       public FileDownload() {
           super();
       }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		           doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
		 ServletOutputStream ou = response.getOutputStream();
		 String saveDir = "upload";
		 String filepath = getServletContext().getRealPath(saveDir);
		 String filename=new String(request.getParameter("filename").getBytes("ISO8859_1"),"UTF-8").toString();
		 System.out.println("DownloadFile filepath:" + filepath);
		 System.out.println("DownloadFile filename:" + filename);
		 File file = new File(filepath +"/"+ filename);
		 if (!file.exists()) {
		 System.out.println(file.getAbsolutePath() + " 文件不存在!");
		 return;
		 }
		 // 读取文件流
		 java.io.FileInputStream fileInputStream = new java.io.FileInputStream(file);
		 // 下载文件
		 // 设置响应头和下载保存的文件名
		 if (filename != null && filename.length() > 0) {
		 response.setContentType("application/x-msdownload");
		 response.setHeader("Content-Disposition", "attachment; filename=" + new String(filename.getBytes("gb2312"),"iso8859-1") + "");
		 if (fileInputStream != null) {
		 int filelen = fileInputStream.available();
		 //文件太大时内存不能一次读出,要循环
		 byte a[] = new byte[filelen];
		 fileInputStream.read(a);
		 ou.write(a);
		 }
		 fileInputStream.close();
		 ou.close();
	}

	}
}
