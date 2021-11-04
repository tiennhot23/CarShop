package ptithcm.dao;

import java.util.List;

import org.hibernate.Query;
import ptithcm.entity.Brands;

public class BrandDAO extends DAO{
	
	public static List<Brands> getBrands() {
		String hql = "FROM Brands";
		Query query = getSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Brands> list = query.list();
		return list;
	}
	
	public static int create(Brands brand) {
		begin();
		int res = 1;
		try {
			getSession().save(brand);
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
	
	public static int update(Brands brand) {
		begin();
		int res = 1;
		try {
			getSession().update(brand);
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
	
	public static int delete(Brands brand) {
		begin();
		int res = 1;
		try {
			getSession().delete(brand);
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
