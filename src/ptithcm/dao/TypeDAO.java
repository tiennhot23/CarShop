package ptithcm.dao;

import java.util.List;

import org.hibernate.Query;

import ptithcm.entity.Types;

public class TypeDAO extends DAO{
	
	public static List<Types> getTypes() {
		String hql = "FROM Types";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Types> list = query.list();
		return list;
	}
	
	public static int create(Types type) {
		begin();
		int res = 1;
		try {
			getSession().save(type);
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
	
	public static int update(Types type) {
		begin();
		int res = 1;
		try {
			getSession().update(type);
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
	
	public static int delete(Types type) {
		begin();
		int res = 1;
		try {
			getSession().delete(type);
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
