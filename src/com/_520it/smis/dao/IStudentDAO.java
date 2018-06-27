package com._520it.smis.dao;

import java.util.List;

import com._520it.smis.domain.Student;

public interface IStudentDAO {
	/**
	 * �������
	 * @param stu
	 */
	int save(Student stu);
	
	/**
	 * ɾ������
	 * @param id
	 */
	int delete(Long id);
	
	/**
	 * ���²���
	 * @param id
	 * @param newStu
	 */
	int update(Long id,Student newStu);
	
	/**
	 * ��ѯָ��id��ѧ������
	 * @param i
	 * @return Student
	 */
	Student get(Long id);
	
	/**
	 * ��ѯ����ѧ��
	 * @return
	 */
	List<Student> listAll();

}
