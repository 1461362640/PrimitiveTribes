package com.easyui.core.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.easyui.core.dao.BaseDao;
import com.easyui.core.exception.CustomException;
import com.easyui.core.util.Hibernate;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao,
		Serializable {
	private static final long serialVersionUID = 617513315011278554L;
	protected final transient Log log = LogFactory.getLog(BaseDaoImpl.class);

	@SuppressWarnings("hiding")
	public <T> boolean addEntity(T entity) {
		boolean boo = false;
		try {
			Serializable io = this.getHibernateTemplate().save(entity);
			/** 如果io不为null实体则保存成功 **/
			if (io != null) {
				boo = true;
				log.debug("--------------------添加成功[" + entity
						+ "]--------------------");
			}
		} catch (Exception e) {
			boo = false;
			throw new RuntimeException("save failure！");
		}
		return boo;
	}

	public List<?> findAll(Class<?> clazz) {
		List<?> objs = this.getHibernateTemplate().loadAll(clazz);
		log.debug("--------------------[实体:" + clazz.getSimpleName() + " 记录数:"
				+ objs.size() + "]--------------------");
		return objs;
	}

	@Override
	public List<?> queryByNP(final String userName, final String passWord,
			final Class<?> clazz) {
		return (List<?>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<T>() {

					@SuppressWarnings("unchecked")
					@Override
					public T doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(
								"select * from "
										+ Hibernate.getTableName(clazz)
										+ " where username='" + userName
										+ "' and password ='" + passWord + "'")
								.addEntity(clazz);
						return (T) query.list();
					}

				});
	}

	@Override
	public List<?> findAll(Class<?> clazz, int page, int rows,String sort,String order,BigInteger total) {
		//排序处理
		String orderBy="";
		if(order!=null&&!"".equalsIgnoreCase(order)&&sort!=null&&!"".equalsIgnoreCase(sort)){
			orderBy=" order by "+sort+" "+order;
		}
		//分页处理
		if(total!=null){
			if( page>total.intValue()/rows){
				rows=total.intValue()%rows;
			}
		} 
		page=page-1;
		Query query = getSession()
				.createSQLQuery(
						"select * from " + Hibernate.getTableName(clazz)+orderBy)
				.addEntity(clazz).setFirstResult(page)
				.setMaxResults(rows);
		return query.list();
	}

	@Override
	public BigInteger getCount(final Class<?> clazz) {
		List<?> list = (List<?>) getHibernateTemplate()
				.executeWithNativeSession(new HibernateCallback<T>() {
					@SuppressWarnings("unchecked")
					@Override
					public T doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createSQLQuery("select count(*) from "
										+ Hibernate.getTableName(clazz));
						return (T) query.list();
					}
				});
		return (BigInteger) list.get(0);
	}

	@Override
	public Object getEntity(Class<?> clazz, String id) {
		List<?> list = getSession()
				.createSQLQuery(
						"select * from " + Hibernate.getTableName(clazz)
								+ " where id='" + id + "'").addEntity(clazz)
				.list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> boolean saveOrUpdate(Object obj) {
		try {
			Session session = getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(obj);
			tx.commit();
			session.close();
			return true;
		} catch (Exception e) {
			new CustomException(e);
		}
		return false;
	}

	@Override
	public List<?> getEntitysHQL(Class<?> clazz, String hql) {
		List<?> list = getSession()
				.createSQLQuery(
						"select * from " + Hibernate.getTableName(clazz) + " "
								+ hql).addEntity(clazz).list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public boolean delEntity(Object object) {
		try {
			Session session = getSessionFactory().openSession();
			session.delete(object);
			Transaction tx = session.beginTransaction();
			tx.commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			new CustomException(e);
		}
		return false;
	}

	public boolean save(Object clazz) {
		try {
			getHibernateTemplate().save(clazz);
			return true;
		} catch (DataAccessException e) {
			new CustomException(e);
		}
		return false;
	}

	@Override
	public boolean delete(final Class<?> clazz, final String id) {

		try {
			getSession().doWork(new Work() {
				PreparedStatement pStatement = null;

				public void execute(Connection conn) throws SQLException {
					try {
						conn = getHibernateTemplate().getSessionFactory()
								.openStatelessSession().connection();
						log.debug("--------------执行删除的事务--------------");
						pStatement = conn.prepareStatement("delete from "
								+ Hibernate.getTableName(clazz)
								+ " where id in(" + id + ")");
						pStatement.execute();
					} catch (Exception e) {
						log.error("删除失败", e);
						new CustomException(e);
					} finally {
						try {
							if (pStatement != null)
								pStatement.close();
							if (conn != null)
								conn.close();
						} catch (SQLException e) {
							new CustomException(e);
						}
					}
				}
			});
			return true;
		} catch (Exception e) {
			new CustomException(e);
			;
		}
		return false;
	}

	@Override
	public List<?> getEntitysSQL(final Class<?> clazz, final String sql) {
		return (List<?>) getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<T>() {
					@SuppressWarnings("unchecked")
					public T doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addEntity(
								clazz);
						return (T) query.list();
					}

				});
	}

	@Override
	public List<?> getTreeNode(String hql, Map<String, Object> params) {
		Query q = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<?> list = q.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}
