package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class DAO {
	@Autowired
	private static SessionFactory factory;
	private static Session session;
	private static Transaction transaction;

	public DAO() {}
	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		DAO.factory = factory;
	}
	
	public static Session getSession() {
		Session session = factory.getCurrentSession();
		return session;
	}
	
	public static void begin() {
		session = factory.openSession();
		try {
			transaction = session.beginTransaction();
        } catch (Exception e) {
        	System.out.println("BEGIN TRANSACTION ERROR: " + e);
        }
        
    }

	public static void commit() {
		try {
			transaction.commit();
        } catch (Exception e) {
        	System.out.println("COMMIT TRANSACTION ERROR: " + e);
        }
        
    }

	public static void rollback() {
        try {
        	transaction.rollback();
        } catch (Exception e) {
        	System.out.println("ROLLBACK ERROR: " + e);
        }
    }

    public static void close() {
    	try {
    		session.close();
        } catch (Exception e) {
        	System.out.println("CLOSE SESSION ERROR: " + e);
        }
    }
    
}
