package com.pla.query;

import com.pla.utils.SimplePropertyUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class QueryExcutorImpl<T> extends QueryGroupbyImpl<T> implements QueryExcutor<T> {
	protected List<String> ascList;
	protected List<String> descList;

	// -------------------------- Query Opration --------------------------
	protected abstract void close();

	public T load(Serializable id) {
		try {
			generateJoin();
			return (T) getCriteria().add(Restrictions.idEq(id)).uniqueResult();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public T first() {
		List<T> list = this.list(0, 1);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<T> list() {
		try {
			generateJoin();
			generateOrderBy();
			return getCriteria().list();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public List<T> list(int offset, int size) {
		try {
			generateJoin();
			generateOrderBy();
			getCriteria().setFirstResult(offset);
			getCriteria().setMaxResults(size);
			return getCriteria().list();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public int count() {
		try {
			generateJoin();
			getCriteria().setProjection(Projections.rowCount());
			Long count = (Long) getCriteria().uniqueResult();
			return count.intValue();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public T uniqueResult() {
		try {
			generateJoin();
			return (T) getCriteria().uniqueResult();
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public Pager<T> pager(int pageNo, int pageSize) {
		try {
			generateJoin();
			Pager<T> pager = new Pager<T>(pageNo, pageSize);

			getCriteria().setProjection(Projections.rowCount());
			Long count = (Long) getCriteria().uniqueResult();
			pager.setTotalCount(count.intValue());

			getCriteria().setProjection(null);
			getCriteria().setResultTransformer(Criteria.ROOT_ENTITY);

			generateOrderBy();
			getCriteria().setFirstResult(pager.getOffset());
			getCriteria().setMaxResults(pager.getPageSize());
			List<T> list = getCriteria().list();
			pager.setList(list);
			return pager;
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	// -------------------------- QueryOp4Parts --------------------------
	public T uniqueResult(String... propertyNames) {
		try {
			generateJoin();
			if (propertyNames == null || propertyNames.length == 0)
				return null;

			for (String propertyName : propertyNames) {
				getProjectionList().add(Projections.property(propertyName));
			}

			getCriteria().setProjection(getProjectionList());

			Object obj = getCriteria().uniqueResult();
			T t = getClazz().newInstance();
			if (obj instanceof Object[]) {
				Object[] objects = (Object[]) obj;
				for (int i = 0; i < objects.length; i++) {
					SimplePropertyUtil.setProperty(t, propertyNames[i], objects[i]);
				}
			} else {
				SimplePropertyUtil.setProperty(t, propertyNames[0], obj);
			}
			return t;
		} catch (HibernateException e) {
			throw e;
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public T first(String... propertyNames) {
		List<T> list = list(0, 1, propertyNames);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public List<T> list(String... propertyNames) {
		return list(null, null, propertyNames);
	}

	public List<T> list(Integer offset, Integer size, String... propertyNames) {
		try {
			generateJoin();
			if (propertyNames == null || propertyNames.length == 0)
				return null;
			for (String propertyName : propertyNames) {
				getProjectionList().add(Projections.property(propertyName));
			}

			getCriteria().setProjection(getProjectionList());

			generateOrderBy();
			if (offset != null)
				getCriteria().setFirstResult(offset);
			if (size != null)
				getCriteria().setMaxResults(size);
			List list = getCriteria().list();
			List<T> records = new ArrayList<T>();
			for (Object obj : list) {
				T t = getClazz().newInstance();
				if (obj instanceof Object[]) {
					Object[] objects = (Object[]) obj;
					for (int i = 0; i < objects.length; i++) {
						SimplePropertyUtil.setProperty(t, propertyNames[i], objects[i]);
					}
				} else {
					SimplePropertyUtil.setProperty(t, propertyNames[0], obj);
				}
				records.add(t);
			}
			return records;
		} catch (HibernateException e) {
			throw e;
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}

	public Pager<T> pager(int pageNo, int pageSize, String... propertyNames) {
		try {
			generateJoin();
			if (propertyNames == null || propertyNames.length == 0)
				return null;

			Pager<T> pager = new Pager<T>(pageNo, pageSize);

			getCriteria().setProjection(Projections.rowCount());
			Long count = (Long) getCriteria().uniqueResult();
			pager.setTotalCount(count.intValue());

			ProjectionList projectionList = Projections.projectionList();
			for (String propertyName : propertyNames) {
				projectionList.add(Projections.property(propertyName));
			}
			getCriteria().setProjection(projectionList);

			if (ascList != null) {
				for (String asc : ascList) {
					getCriteria().addOrder(Order.asc(asc));
				}
			}
			if (descList != null) {
				for (String desc : descList) {
					getCriteria().addOrder(Order.desc(desc));
				}
			}
			getCriteria().setFirstResult(pager.getOffset());
			getCriteria().setMaxResults(pager.getPageSize());
			List list = getCriteria().list();
			List<T> records = new ArrayList<T>();
			for (Object obj : list) {
				T t = getClazz().newInstance();
				if (obj instanceof Object[]) {
					Object[] objects = (Object[]) obj;
					for (int i = 0; i < objects.length; i++) {
						SimplePropertyUtil.setProperty(t, propertyNames[i], objects[i]);
					}
				} else {
					SimplePropertyUtil.setProperty(t, propertyNames[0], obj);
				}
				records.add(t);
			}
			pager.setList(records);
			return pager;
		} catch (Exception e) {
			throw new HibernateException(e);
		} finally {
			close();
		}
	}
}