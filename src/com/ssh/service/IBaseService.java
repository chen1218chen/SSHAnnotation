package com.ssh.service;

import java.io.Serializable;
import java.util.List;
public interface IBaseService<T extends Serializable> {
	//增加
	public void insert(T t);
	//更新
	public void update(T t);
	//删除
	public void delete(T t);
	//获取全部
	public List<T> queryAll();
}
