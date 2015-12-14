package com.ssh.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface IBaseDao<T extends Serializable> {
	//����
	public void insert(T t);
	//����
	public void update(T t);
	//ɾ��
	public void delete(T t);
	//���ӻ����
	public T merge(T t);
	//����id����pojo
	public T queryById(int id);
	//����id������pojo
	public T loadById(int id);
	//��ȡȫ��
	public List<T> queryAll();
	//����ĳЩ�ֶ� ��ȡ����
	public List<T> queryByFields(String fields[],Object values[]);
	//ͨ���ֶβ�ѯ
	public List<T> queryByFieldsStr(String[] fields, String[] values);
	//����idɾ��
	public void deleteById(int id);
	//����pojo���ȡ�Ļ���sql
	public StringBuffer getBaseSQL();
	//����sql��ȡQuery
	public Query getQuery(String sql);
}
