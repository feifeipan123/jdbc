package com._520it.smis.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class JdbcUtil {
	private static DataSource ds = null;
	static {
		try {
			Properties p = new Properties();
			InputStream inStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties");
			p.load(inStream);
			ds = DruidDataSourceFactory.createDataSource(p);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
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
