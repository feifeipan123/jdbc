package com._520it._06_return_pk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import com._520it._01_smis.util.JdbcUtil;

public class GetPk {
  @Test
  public void testStatement() {
	  String sql = "insert into t_student (name,age) values ('aa',23)";
	  Connection conn = null;
	  Statement st = null;
	  ResultSet rs = null;
	  try {
		  conn = JdbcUtil.getConn();
		  st = conn.createStatement();
		  st.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
		  rs = st.getGeneratedKeys();
		  if(rs.next()) {
			  System.out.println(rs.getLong(1));
		  }
	  }catch(Exception e) {
		  
	  }finally {
		  JdbcUtil.close(conn, st, rs);
	  } 
  }
  
  @Test
  public void testPreparedStatement() {
	  String sql = "insert into t_student (name,age) values (?,?)";
	  Connection conn = null;
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  try {
		  conn = JdbcUtil.getConn();
		  ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		  ps.setString(1, "pppp");
		  ps.setInt(2, 27);
		  ps.executeUpdate();
		  rs = ps.getGeneratedKeys();
		  if(rs.next()) {
			  System.out.println(rs.getLong(1));
		  }
	  }catch(Exception e) {
		  e.printStackTrace();
		  
	  }finally {
		  JdbcUtil.close(conn, ps, rs);
	  } 
  }
}
