package ptithcm.dao;

import java.util.List;

import org.hibernate.Query;

import ptithcm.entity.Admin;

public class AdminDAO extends DAO{
	
	public static Admin getAdmin(String username, String pass) {
		String hql = "FROM Admin where username = :username and pass = :pass";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("pass", pass);
		@SuppressWarnings("unchecked")
		List<Admin> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
}
