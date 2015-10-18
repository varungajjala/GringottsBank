package dao;
import org.hibernate.Session;

import persistence.HibernateUtil;
import pojo.Transactions;

public class DatabaseConnectors {
	 public static void main( String[] args )
	    {
	        System.out.println("Maven + Hibernate + MySQL");
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        
	        session.beginTransaction();
	        Transactions temp = new Transactions("credit","dhan1234","transfer",(float)123.4);
	        
	        /*temp.setAccountno(123456790);
	        temp.setUniqId("EM13");
	        temp.setBalance((float)23456.78);*/
	        
	        session.save(temp);
	        session.getTransaction().commit();
	    }
}