package com.noon.simlegate.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBOper {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connection getConn(String server, String dbname, String user,
			String pwd) throws ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException {
		String DRIVER = "com.mysql.jdbc.Driver";
		String URL = "jdbc:mysql://" + server + ":3306/" + dbname + "?user="
				+ user + "&password=" + pwd
				+ "&useUnicode=true&characterEncoding=utf8";
		Class.forName(DRIVER).newInstance();
		conn = DriverManager.getConnection(URL);

		return conn;
	}

	public void closeAll() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ResultSet executeQuery(String preparedSql, String[] param) {
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public int executeUpdate(String preparedSql, String[] param) {
		int num = 0;
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]);
				}
			}
			num = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}
	public void create(){
		try{
			
			pstmt = conn.prepareStatement("CREATE DATABASE  IF NOT EXISTS 'share';" +
					"CREATE TABLE 'user' ("+
  "'id' int(11) NOT NULL AUTO_INCREMENT,"+
  "'username' varchar(45) NOT NULL,"+
  "'password' varchar(45) NOT NULL,"+
  "'time' varchar(45) NOT NULL,"+
  "PRIMARY KEY ('id')"+
  ") ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;");
			pstmt.executeUpdate();
			System.out.println("success");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
