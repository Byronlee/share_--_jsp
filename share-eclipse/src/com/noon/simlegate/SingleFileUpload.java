package com.noon.simlegate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/SingleFileUpload")
public class SingleFileUpload extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SingleFileUpload() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String path = "D:\\";

        
        String boundary = "";
        String fileOldName = "";
        FileOutputStream fo = null;
        BufferedOutputStream bo = null;
        byte[] buffer = new byte[1024];
        // 每行长度
        int p = 0;
        // 每行内容
        String line = "";
        // 获得请求的输入流
        ServletInputStream sis = request.getInputStream();

        // 读取第一行，获取分隔符
        if ((p = sis.readLine(buffer, 0, buffer.length)) != -1) {
            boundary = new String(buffer, 0, p, "utf-8");
            System.out.println(">>>>第1行：" + boundary);
        }

        // 读取第二行，获取原文件名
        if ((p = sis.readLine(buffer, 0, buffer.length)) != -1) {
            line = new String(buffer, 0, p, "utf-8");
            System.out.println(">>>>第2行：" + line);
            if (line.toLowerCase().startsWith("content-disposition: form-data; ")) {
                // 判断表单数据是否来自file控件，解析上传文件的原始路径
                int fileNameStartIndex = line.toLowerCase().indexOf("filename=\"");
                if (fileNameStartIndex != -1) {
                    // 解析上传文件的原始路径与文件名
                    int fileNameEndIndex = line.toLowerCase().indexOf("\"", fileNameStartIndex + 10);
                    // 获取上传文件的原始的完整路径
                    fileOldName = line.substring(fileNameStartIndex + 10, fileNameEndIndex);
                    System.out.println("输出fileOldName-1：" + fileOldName);
                    // 获取上传文件的原文件名
                    fileOldName = fileOldName.substring(fileOldName.lastIndexOf("\\") + 1);
                    System.out.println("输出fileOldName-2：" + fileOldName);
                }
                // 如果表单域中数据确实来自file控件，但却没有找到原文件名，则进入表单域中的下一部分进行解析
                if (fileOldName == null || fileOldName.trim().length() == 0) {
                    System.out.println("上传失败，没有找到上传文件");
                    return;
                }
            }
        }

        File f = new File(path + fileOldName);
        System.out.println("输出新文件的路径：" + f.getPath());
        fo = new FileOutputStream(f);
        bo = new BufferedOutputStream(fo);

        // 跳过第三行：content-type
        sis.readLine(buffer, 0, buffer.length);
        // 跳过第四行：空行
        sis.readLine(buffer, 0, buffer.length);

        // 获取文件内容
        while ((p = sis.readLine(buffer, 0, buffer.length)) != -1) {
            line = new String(buffer, 0, p, "utf-8");
            if (!line.toLowerCase().startsWith(boundary)) {
                bo.write(buffer, 0, p);
                bo.flush();

            }
        }
    }
}
