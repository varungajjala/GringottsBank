package dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Property;

import javassist.bytecode.Descriptor.Iterator;
import persistence.HibernateUtil;
import pojo.ExternalUser;
import pojo.InternalUser;
import pojo.Login;
import pojo.Transactions;
import pojo.UserInfo;

public class DatabaseConnectors {
	 public void saveLogin(Login login) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(login);
		 session.getTransaction().commit();
	 }
	 public void saveInternalUser(InternalUser internalUser) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(internalUser);
		 session.getTransaction().commit();
	 }
	 public void saveExternalUser(ExternalUser externalUser) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(externalUser);
		 session.getTransaction().commit();
	 }
	 public void saveUserInfo(UserInfo userInfo) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(userInfo);
		 session.getTransaction().commit();
	 }
	public void saveTransaction(Transactions transaction) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(transaction);
		 session.getTransaction().commit();
	 }
	 public String getUniqIdByUsername(String username) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Login login = (Login) session.createCriteria(Login.class)
				 .add( Restrictions.like("userId", username)).uniqueResult();
		 if(login != null) {
			 return login.getUniqId();
		 }
		 return "";
	 }
	public List<Transactions> getTransactionsByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		List<Transactions> results = (List<Transactions>) session.createCriteria(Transactions.class)
				 .add( Restrictions.like("uniqId", uniqId)).addOrder(Order.desc("date")).list();
		 if(results != null) {
			 return (List<Transactions>)results;
		 }
		 return null;
	 }
	 public UserInfo getUserInfoByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 UserInfo userInfo = (UserInfo)session.createCriteria(UserInfo.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 return userInfo;
	 }
	 public int getAccountNoByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 ExternalUser externalUser = (ExternalUser)session.createCriteria(ExternalUser.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 if( externalUser != null ) {
			 return externalUser.getAccountno();
		 }
		 return 0;
	}
	 public float getBalanceByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 ExternalUser externalUser = (ExternalUser)session.createCriteria(ExternalUser.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 if( externalUser != null ) {
			 return externalUser.getBalance();
		 }
		 return 0;
	 }
	 public int getEmployeeIdByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 InternalUser internalUser = (InternalUser)session.createCriteria(InternalUser.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 if( internalUser != null ) {
			 return internalUser.getEmpId();
		 }
		 return 0;
	 }
}