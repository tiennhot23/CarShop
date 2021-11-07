package ptithcm.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ptithcm.bean.FilterOrder;
import ptithcm.entity.Orders;

public class OrderDAO{

	private SessionFactory factory;
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public List<Orders> getOrders() {
		String hql = "FROM Orders";
		Query query = factory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public Orders getOrder(String oid) {
		String hql = "FROM Orders where oid = :oid";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("oid", oid);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public Orders getOrder(int id) {
		String hql = "FROM Orders where id = :id";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public List<Orders> getOrders(int carId) {
		String hql = "FROM Orders where car.id = :carId";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("carId", carId);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public List<Orders> getOrders(FilterOrder filterOrder) {
		String hql = "FROM Orders where stat <> -2 ";
		if(!filterOrder.getOidFilter().equals("")) hql += "and oid = :oidFilter ";
		if(!filterOrder.getCustomerFilter().equals("")) hql += "and customer LIKE :customerFilter ";
		if(!filterOrder.getEmailFilter().equals("")) hql += "and email = :emailFilter ";
		if(!filterOrder.getPhoneFilter().equals("")) hql += "and phone = :phoneFilter ";
		if(filterOrder.getStatusFilter() != 0) {
			hql += "and stat = :statusFilter ";
		}
		hql += "order by stat";
				
		Query query = factory.getCurrentSession().createQuery(hql);
		if(!filterOrder.getOidFilter().equals("")) query.setParameter("oidFilter", filterOrder.getOidFilter());
		if(!filterOrder.getCustomerFilter().equals("")) query.setParameter("customerFilter", "%" + filterOrder.getCustomerFilter() + "%");
		if(!filterOrder.getEmailFilter().equals("")) query.setParameter("emailFilter", filterOrder.getEmailFilter());
		if(!filterOrder.getPhoneFilter().equals("")) query.setParameter("phoneFilter", filterOrder.getPhoneFilter());
		if(filterOrder.getStatusFilter() != 0) {
			query.setParameter("statusFilter", filterOrder.getStatusFilter());
		}
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	
	public int create(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.save(order);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("CREATE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int update(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.update(order);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("UPDATE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public int delete(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		int res = 1;
		try {
			session.delete(order);
			transaction.commit();
		} catch (Exception e) {
			System.out.println("DELETE ORDER ERROR: " + e);
			transaction.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
}
