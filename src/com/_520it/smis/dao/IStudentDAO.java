package com._520it.smis.dao;

import java.util.List;

import com._520it.smis.domain.Student;

public interface IStudentDAO {
	/**
	 * 保存操作
	 * @param stu
	 */
	int save(Student stu);
	
	/**
	 * 删除操作
	 * @param id
	 */
	int delete(Long id);
	
	/**
	 * 更新操作
	 * @param id
	 * @param newStu
	 */
	int update(Long id,Student newStu);
	
	/**
	 * 查询指定id的学生对象
	 * @param i
	 * @return Student
	 */
	Student get(Long id);
	
	/**
	 * 查询所有学生
	 * @return
	 */
	List<Student> listAll();

}
