package ptithcm.dao;

import java.util.List;
import org.hibernate.Query;
import ptithcm.entity.Securities;

public class SecurityDAO extends DAO{
	
	public static Securities getSecurity(String token) {
		String hql = "FROM Securities where token = :token";
		Query query = getSession().createQuery(hql);
		query.setParameter("token", token);
		@SuppressWarnings("unchecked")
		List <Securities> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	
	public static int delete(Securities security) {
		begin();
		int res = 1;
		try {
			getSession().delete(security);
			commit();
		} catch (Exception e) {
			System.out.println("DELETE KEY ERROR: " + e);
			rollback();
			res = 0;
		} finally {
			close();
		}
		return res;
	}
	
	public static int create(Securities security) {
		begin();
		int res = 1;
		try {
			getSession().save(security);
			commit();
		} catch (Exception e) {
			System.out.println("CREATE KEY ERROR: " + e);
			rollback();
			res = 0;
		} finally {
			close();
		}
		return res;
	}
}
