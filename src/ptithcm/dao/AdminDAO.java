package ptithcm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ptithcm.entity.Admin;

@Transactional
public class AdminDAO{
	@Autowired
	private SessionFactory factory;
	
	
	public SessionFactory getFactory() {
		return factory;
	}


	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}


	public Admin getAdmin(String username, String pass) {
		String hql = "FROM Admin where username = :username and pass = :pass";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("pass", pass);
		@SuppressWarnings("unchecked")
		List<Admin> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
}
