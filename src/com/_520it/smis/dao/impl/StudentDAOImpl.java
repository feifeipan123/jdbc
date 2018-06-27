package com._520it.smis.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com._520it.smis.dao.IStudentDAO;
import com._520it.smis.domain.Student;
import com._520it.smis.handler.BeanHandler;
import com._520it.smis.handler.BeanListHandler;
import com._520it.smis.handler.IResultSetHandler;
import com._520it.smis.util.JdbcTemplate;
import com._520it.smis.util.JdbcUtil;

public class StudentDAOImpl implements IStudentDAO {

	@Override
	public int save(Student stu) {
		String sql = "insert into t_student(age,name) values(?,?)";
		return JdbcTemplate.update(sql, stu.getAge(),stu.getName());
	}

	@Override
	public int delete(Long id) {
		String sql = "delete from t_student where id=?";
		return JdbcTemplate.update(sql, id);
	}

	@Override
	public int update(Long id, Student newStu) {
		String sql = "update t_student set age=?,name=? where id=?";
		return JdbcTemplate.update(sql, newStu.getAge(),newStu.getName(),newStu.getId());
	}

	@Override
	public Student get(Long id) {
		String sql = "select * from t_student where id=?";
		return JdbcTemplate.query(sql,new BeanHandler<Student>(Student.class),id);
	}

	@Override
	public List<Student> listAll() {
		String sql = "select * from t_student";
		return JdbcTemplate.query(sql,new BeanListHandler<Student>(Student.class));
	}
}

//以下代码可以不要，留着复习
class StudentResultSetHandler implements IResultSetHandler<List<Student>>{

	@Override
	public List<Student> handle(ResultSet rs) {
		List<Student> list = new ArrayList<Student>();
		try {
			while(rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getLong("id"));
				stu.setName(rs.getString("name"));
				stu.setAge(rs.getInt("age"));
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
