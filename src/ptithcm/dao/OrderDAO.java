package ptithcm.dao;

import java.util.List;

import org.hibernate.Query;

import ptithcm.bean.FilterOrder;
import ptithcm.entity.Orders;
public class OrderDAO extends DAO{
	
	public static List<Orders> getOrders() {
		String hql = "FROM Orders";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public static Orders getOrder(String oid) {
		String hql = "FROM Orders where oid = :oid";
		Query query = getSession().createQuery(hql);
		query.setParameter("oid", oid);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public static Orders getOrder(int id) {
		String hql = "FROM Orders where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public static List<Orders> getOrders(int carId) {
		String hql = "FROM Orders where car.id = :carId";
		Query query = getSession().createQuery(hql);
		query.setParameter("carId", carId);
		@SuppressWarnings("unchecked")
		List<Orders> list = query.list();
		return list;
	}
	
	public static List<Orders> getOrders(FilterOrder filterOrder) {
		String hql = "FROM Orders where stat <> -2 ";
		if(!filterOrder.getOidFilter().equals("")) hql += "and oid = :oidFilter ";
		if(!filterOrder.getCustomerFilter().equals("")) hql += "and customer LIKE :customerFilter ";
		if(!filterOrder.getEmailFilter().equals("")) hql += "and email = :emailFilter ";
		if(!filterOrder.getPhoneFilter().equals("")) hql += "and phone = :phoneFilter ";
		if(filterOrder.getStatusFilter() != 0) {
			hql += "and stat = :statusFilter ";
		}
		hql += "order by stat";
				
		Query query = getSession().createQuery(hql);
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
	
	
	public static int create(Orders order) {
		begin();
		int res = 1;
		try {
			getSession().save(order);
			commit();
		} catch (Exception e) {
			System.out.println("CREATE ORDER ERROR: " + e);
			rollback();
			res = 0;
		} finally {
			close();
		}
		return res;
	}
	
	public static int update(Orders order) {
		begin();
		int res = 1;
		try {
			getSession().update(order);
			commit();
		} catch (Exception e) {
			System.out.println("UPDATE ORDER ERROR: " + e);
			rollback();
			res = 0;
		} finally {
			close();
		}
		return res;
	}
	
	public static int delete(Orders order) {
		begin();
		int res = 1;
		try {
			getSession().delete(order);
			commit();
		} catch (Exception e) {
			System.out.println("DELETE ORDER ERROR: " + e);
			rollback();
			res = 0;
		} finally {
			close();
		}
		return res;
	}
}
