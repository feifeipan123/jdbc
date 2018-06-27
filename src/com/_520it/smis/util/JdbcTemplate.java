package com._520it.smis.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com._520it.smis.handler.IResultSetHandler;

//Jdbc����ģ����
public class JdbcTemplate {
   /**
    * DML��������ɾ�ģ���ģ��
    * @param sql
    * @param params
    * @return
    */
	public static int update(String sql,Object...params) {
		Connection conn = null;
		PreparedStatement pst = null;
		int count = 0;
		try {
			conn = JdbcUtil.getConn();
			pst = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				pst.setObject(i+1, params[i]);
			}
			count = pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,pst,null);
		}
		
		return count;
	}
	
	/**
	 * DQL ��ѯģ��
	 * @param sql
	 * @param IResultSetHandler ���������
	 * @param Object[] �����б�
	 * @return List
	 */
	public static List query(String sql,IResultSetHandler rsh,Object ...params){
		List list = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			list = rsh.handle(rs);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,ps,rs);
		}
		return list;
		
	}
}
