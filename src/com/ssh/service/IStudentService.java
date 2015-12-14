package com.ssh.service;

import java.util.List;

import com.ssh.model.Student;

public interface IStudentService extends IBaseService<Student>{
	public String getName();
	public Student queryByID(int id);
	public List<Student> queryByName(String name);
	public void deleteByID(int id);
}
