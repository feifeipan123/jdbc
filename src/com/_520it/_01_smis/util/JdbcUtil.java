package com._520it._01_smis.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	private static Properties p = new Properties();
	static {
		try {
			InputStream inStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties");
			p.load(inStream);
			System.out.println(p);
			Class.forName(p.getProperty("driverClassName"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		try {
			return DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void close(Connection conn,Statement st,ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(st != null) {
					st.close();
				}
			}catch(Exception e) {	
			}finally {
				try {
					if(conn != null) {
					conn.close();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
