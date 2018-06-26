package com._520it._02_tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com._520it._01_smis.util.JdbcUtil;

public class TxTest {

	@Test
	public void test1() throws Exception{
		String sql = "select * from account where name=? and balance >=?";
		Connection conn = JdbcUtil.getConn();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "���޼�");
		ps.setInt(2, 1000);
		ResultSet rs = ps.executeQuery();
		if(!rs.next()) {
			throw new RuntimeException("�˻�����");
		}
		Long id = rs.getLong("id");
		//------------------------------
		sql = "update account set balance=balance-? where id=?";
	    ps = conn.prepareStatement(sql);
	    ps.setInt(1, 1000);
	    ps.setLong(2, id);
	    int count = ps.executeUpdate();
	    if(count!=1) {
	    	throw new RuntimeException("�˻����ĳ���");
	    }
		//------------------------------
	    sql = "update account set balance=balance+? where name=?";
	    ps = conn.prepareStatement(sql);
	    ps.setInt(1, 1000);
	    ps.setString(2, "����");
	    count = ps.executeUpdate();
	    if(count!=1) {
	    	throw new RuntimeException("�˻����ĳ���");
	    }
	    JdbcUtil.close(conn, ps, rs);
	}
	
	//��������
	@SuppressWarnings({ "resource", "null" })
	@Test
	public void test2() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false);
			String sql = "select * from account where name=? and balance >=?";
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "���޼�");
			ps.setInt(2, 1000);
			rs = ps.executeQuery();
			if(!rs.next()) {
				throw new RuntimeException("�˻�����");
			}
			Long id = rs.getLong("id");
			//------------------------------
			sql = "update account set balance=balance-? where id=?";
		    ps = conn.prepareStatement(sql);
		    ps.setInt(1, 1000);
		    ps.setLong(2, id);
		    int count = ps.executeUpdate();
		    if(count!=1) {
		    	throw new RuntimeException("�˻����ĳ���");
		    }
		    int a = 1/0;
			//------------------------------
		    sql = "update account set balance=balance+? where name=?";
		    ps = conn.prepareStatement(sql);
		    ps.setInt(1, 1000);
		    ps.setString(2, "����");
		    count = ps.executeUpdate();
		    if(count!=1) {
		    	throw new RuntimeException("�˻����ĳ���");
		    }
		    
		    conn.commit();
			
		}catch(Exception e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}			
		}finally {
			 JdbcUtil.close(conn, ps, rs);
		}

	}

}
