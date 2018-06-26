package com._520it._05_blob;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com._520it._01_smis.util.JdbcUtil;

public class BlobTest {
   @Test
   public void test1() throws Exception{
	   String sql = "insert into t_image (img) values (?)";
	   Connection conn = JdbcUtil.getConn();
	   PreparedStatement ps = conn.prepareStatement(sql);
	   ps.setBlob(1, new FileInputStream("F:\\PRO\\conn-day2\\contain.png"));
	   ps.executeUpdate();
	   JdbcUtil.close(conn, ps, null);
	   
   }
}
