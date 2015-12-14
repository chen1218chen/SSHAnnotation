package com.ssh.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.ssh.dao.IBaseDao;

@Transactional
public class BaseDaoImpl<T extends Serializable> implements IBaseDao<T> {

	protected Class<T> entityClass;

	public BaseDaoImpl(Class<T> classT) {
		this.entityClass = classT;
	}

	/*
	 * @Resource�������൱��@Autowired�� ֻ����@Autowired��byType�Զ�ע�룬��@ResourceĬ�ϰ�
	 * byName�Զ�ע����ˡ�
	 * 
	 * @Resource�����������ǱȽ���Ҫ�ģ�����name��type��Spring��@Resourceע���name���Խ���Ϊbean�����֣�
	 * ��type���������Ϊbean�����͡��������ʹ��name���ԣ���ʹ��byName���Զ�ע�����
	 * ����ʹ��type����ʱ��ʹ��byType�Զ�ע����ԡ�����Ȳ�ָ��nameҲ��ָ��type���ԣ� ��ʱ��ͨ���������ʹ��byName�Զ�ע����ԡ�
	 * �Ƽ�ʹ�ã�@Resourceע�����ֶ��ϣ������Ͳ���дsetter�����ˣ�
	 * �������ע��������J2EE�ģ���������spring����ϡ��������뿴��ͱȽ����š�
	 */
	@Resource
	private SessionFactory sessionFactory;

	public Session getSession()
	{
		return this.sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void insert(T t) {
		this.getSession().save(t);
		this.getSession().flush();
	}

	@Override
	@Transactional(readOnly = false)
	public void update(T t) {
		this.getSession().update(t);
		this.getSession().flush();
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(T t) {
		this.getSession().delete(t);
		this.getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false)
	public T merge(T t) {
		return (T) this.getSession().merge(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T queryById(int id) {
		return (T) this.getSession().get(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public T loadById(int id) {
		return (T) this.getSession().load(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<T> queryAll() {
		StringBuffer hql = new StringBuffer(" from ");
		hql.append(this.entityClass.getSimpleName());
		return this.getQuery(hql.toString()).list();
	}

	@Transactional(readOnly = false)
	@Override
	public void deleteById(int id) {
		T t = this.loadById(id);
		this.delete(t);
	}

	@Override
	public StringBuffer getBaseSQL() {
		return new StringBuffer(" from ").append(this.entityClass
				.getSimpleName());
	}

	public Query getQuery(String sql) {
		return this.getSession().createQuery(sql);
	}
	
	//�ֶβ�ѯ
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByFields(String[] fields, Object[] values) {
		StringBuffer hql = new StringBuffer(" from ");
		hql.append(this.entityClass.getSimpleName()).append(" student ").append(" where 1=1 ");
		for (int i = 0; i < fields.length; i++) {
			String field=fields[i];
			Object value=values[i];
			hql.append(" and ").append(" student.").append(field);
			if(value==null)
				hql.append(" is null");
			else
				hql.append(" =:").append(field);
		}
		System.out.println(hql.toString());
		Query query= this.getQuery(hql.toString());
		for (int i = 0; i < values.length; i++) {
			Object value=values[i];
			if(value!=null)
			{
				query.setParameter(fields[i], value);
			}
		}
		return query.list();
	}
	//�ֶ�ģ����ѯ
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryByFieldsStr(String[] fields, String[] values) {
		StringBuffer hql = new StringBuffer(" from ");
		hql.append(this.entityClass.getSimpleName()).append(" where 1=1 ");
		for (int i = 0; i < values.length; i++) {
			hql.append(" and (").append(fields[i]).append(" like '%").
				append(values[i]).append("%' or ").append(fields[i]).append(" is null)");
		}
		//System.out.println(hql.toString());
		Query query = this.getQuery(hql.toString());
		//System.out.println(query.toString());
		return query.list();
	}
}

