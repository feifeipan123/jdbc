package com._520it._01_smis.test;


import java.util.List;

import org.junit.Test;

import com._520it._01_smis.dao.IStudentDAO;
import com._520it._01_smis.dao.impl.StudentDAOImpl;
import com._520it._01_smis.domain.Student;

public class StudentDAOTest {
	private IStudentDAO studentDao = new StudentDAOImpl();

	@Test
	public void testSave() {
		Student stu = new Student();
		stu.setAge(19);
		stu.setName("pppp");
		int count = studentDao.save(stu);
		System.out.println(count);
	}

	@Test
	public void testDelete() {
		int ret = studentDao.delete((long) 10);
		System.out.println(ret);
	}

	@Test
	public void testUpdate() {
		Student stu = new Student();
		stu.setAge(19);
		stu.setName("潘帅哥ppp");
		int count = studentDao.update((long) 3,stu);
		System.out.println(count);
	}

	@Test
	public void testGet() {
		 Student stu= studentDao.get((long) 3);
		 System.out.println(stu);
	}

	@Test
	public void testListAll() {
		List<Student> stus = studentDao.listAll();
		if(!stus.isEmpty()) {
			for (Student stu : stus) {
				System.out.println(stu);
		    }
		}else {
			System.out.println("学生列表为空");
		}
	}

}
