package com.ssh.dao.impl;

import org.springframework.stereotype.Repository;

import com.ssh.dao.IStudentDao;
import com.ssh.model.Student;
@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements IStudentDao{
	public StudentDaoImpl() {
		super(Student.class);
	}
}
