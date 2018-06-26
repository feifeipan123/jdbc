package com._520it._07_ds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class DBCPTest {
	public DataSource getDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql:///test");
		ds.setUsername("root");
		ds.setPassword("921012");
		return ds;
	}
	@Test
	public void test1() {
		DataSource ds = this.getDataSource();
		Connection conn = null;
		PreparedStatement ps = null;
		 ResultSet rs = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select * from t_student");
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getLong("id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

}
