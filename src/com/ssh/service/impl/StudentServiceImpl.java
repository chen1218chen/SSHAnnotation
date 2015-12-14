package com.ssh.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssh.dao.IBaseDao;
import com.ssh.dao.IStudentDao;
import com.ssh.model.Student;
import com.ssh.service.IStudentService;
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements IStudentService{
	@Resource
	private IStudentDao studentDaoImpl;
	@Override
	public String getName() {
		return studentDaoImpl.queryById(1).getName();
	}

	@Override
	public Student queryByID(int id) {
		return studentDaoImpl.queryById(id);
	}
	
	@Override
	public List<Student> queryByName(String name) {
		String []fields={"name"};
		String []values={name};
		//Object []values={name};
		return studentDaoImpl.queryByFieldsStr(fields, values);
	}

	@Override
	public void deleteByID(int id) {
		// TODO Auto-generated method stub
		studentDaoImpl.deleteById(id);
	}
	@Override
	public IBaseDao<Student> getDao() {
		// TODO Auto-generated method stub
		return studentDaoImpl;
	}
}
