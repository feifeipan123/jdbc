package com._520it._01_smis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com._520it._01_smis.dao.IStudentDAO;
import com._520it._01_smis.domain.Student;
import com._520it._01_smis.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public int save(Student stu) {
		String sql = "insert into t_student(age,name) values(?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		int count = 0;
		try {
			conn = JdbcUtil.getConn();
			pst = conn.prepareStatement(sql);
			pst.setInt(1,stu.getAge());
			pst.setString(2,stu.getName());
			count = pst.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,pst,null);
		}
		
		return count;
	}

	@Override
	public int delete(Long id) {
		String sql = "delete from t_student where id=?";
		StringBuilder sb = new StringBuilder();
		sb.append("delete from t_student where id=");
		sb.append(id);
		System.out.println(sb);
		Connection conn = null;
		Statement st = null;
		int count = 0;
		try {
			conn = JdbcUtil.getConn();
			st = conn.createStatement();
			count = st.executeUpdate(sb.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,st,null);
		}
		
		return count;

	}

	@Override
	public int update(Long id, Student newStu) {
		String sql = "update t_student set age=?,name=? where id=?";
		StringBuilder sb = new StringBuilder();
		sb.append("update t_student set age=");
		sb.append(newStu.getAge());
		sb.append(",name='");
		sb.append(newStu.getName());
		sb.append("' where id=");
		sb.append(id);
		System.out.println(sb);
		Connection conn = null;
		Statement st = null;
		int count = 0;
		try {
			conn = JdbcUtil.getConn();
			st = conn.createStatement();
			count = st.executeUpdate(sb.toString());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,st,null);
		}
		
		return count;

	}

	@Override
	public Student get(Long id) {
		String sql = "select * from t_student where id = "+id;
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
			    Student stu = new Student();
			    stu.setId(rs.getLong("id"));
			    stu.setAge(rs.getInt("age"));
			    stu.setName(rs.getString("name"));
			    return stu;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,st,rs);
		}
		return null;
	}

	@Override
	public List<Student> listAll() {
		List<Student> list = new ArrayList<Student>();
		String sql = "select * from t_student";
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			    Student stu = new Student();
			    stu.setId(rs.getLong("id"));
			    stu.setAge(rs.getInt("age"));
			    stu.setName(rs.getString("name"));
			    list.add(stu);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn,st,rs);
		}
		return list;
	}

}
