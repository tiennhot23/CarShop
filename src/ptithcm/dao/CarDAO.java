package ptithcm.dao;

import java.util.List;

import org.hibernate.Query;

import ptithcm.bean.FilterCar;
import ptithcm.entity.Cars;

public class CarDAO extends DAO{
	public static List<Cars> getCars() {
		String hql = "FROM Cars";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	public static Cars getCar(int id) {
		String hql = "FROM Cars where id = :id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public static List<Cars> getCars(FilterCar filterCar) {
		String hql = "FROM Cars WHERE name LIKE :search "
				+ "and price >= :min and price <= :max "
				+ "and type.name LIKE :type "
				+ "and brand.name LIKE :brand ";
		Query query = getSession().createQuery(hql);
		query.setParameter("search", "%" + filterCar.getNameFilter() + "%");
		query.setParameter("min", filterCar.getMinFilter());
		query.setParameter("max", filterCar.getMaxFilter());
		if(filterCar.getTypeFilter().equals("All")) {
			query.setParameter("type", "%");
		}else query.setParameter("type", "%" + filterCar.getTypeFilter());
		if(filterCar.getBrandFilter().equals("All")) {
			query.setParameter("brand", "%");
		}else query.setParameter("brand", "%" + filterCar.getBrandFilter());
		@SuppressWarnings("unchecked")
		List<Cars> list = query.list();
		return list;
	}
	
	public static int create(Cars car) {
		begin();
		int res = 1;
		try {
			getSession().save(car);
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
	
	public static int update(Cars car) {
		begin();
		int res = 1;
		try {
			getSession().update(car);
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
	
	public static int delete(Cars car) {
		begin();
		int res = 1;
		try {
			getSession().delete(car);
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
