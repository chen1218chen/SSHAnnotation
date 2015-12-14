package com.ssh.service.impl;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.IBaseDao;
import com.ssh.service.IBaseService;

@Transactional
public abstract class BaseServiceImpl<T extends Serializable> implements IBaseService<T>{
	public abstract IBaseDao<T> getDao();
	//增加
	public void insert(T t)
	{
		getDao().insert(t);
	}
	//更新
	public void update(T t)
	{
		getDao().update(t);
	}
	//删除
	public void delete(T t)
	{
		getDao().delete(t);
	}
	public List<T> queryAll() {
		// TODO Auto-generated method stub
		return getDao().queryAll();
	}
}
