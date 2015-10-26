package dao;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Property;
import org.hibernate.property.access.spi.GetterMethodImpl;

import javassist.bytecode.Descriptor.Iterator;
import persistence.HibernateUtil;
import pojo.ExternalUser;
import pojo.InternalUser;
import pojo.Login;
import pojo.TempTransactions;
import pojo.TempUserInfo;
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
	public void saveTempTransaction(TempTransactions transaction) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(transaction);
		 session.getTransaction().commit();
	 }
	public void saveTempUserInfo(TempUserInfo tempUserInfo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(tempUserInfo);
		session.getTransaction().commit();
	}
	public void updateUserInfo(UserInfo userInfo) {
		UserInfo tempUserInfo = getUserInfoByUniqId(userInfo.getUniqId());
		userInfo.setId(tempUserInfo.getId());
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(userInfo);
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
	public Login getLoginByUsername(String username) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Login login = (Login) session.createCriteria(Login.class)
				 .add( Restrictions.like("userId", username)).uniqueResult();
		 if(login != null) {
			 return login;
		 }
		 return null;
	}
	public String getPasswdByUsername(String username) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Login login = (Login) session.createCriteria(Login.class)
				 .add( Restrictions.like("userId", username)).uniqueResult();
		 if(login != null) {
			 return login.getPasswd();
		 }
		 return "";
	}
	
	
	
	public String getRoleByUsername(String username) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Login login = (Login) session.createCriteria(Login.class)
				 .add( Restrictions.like("userId", username)).uniqueResult();
		 if(login != null) {
			 return login.getRole();
		 }
		 return "";
	}
	
	public int checkLogin(String userid, String passwd){
		String passFromDb = getPasswdByUsername(userid);
		if (passFromDb !=""&& BCrypt.checkpw(passwd, passFromDb)){
			return 1;
		}
		return 0;
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
	 public TempTransactions getTempTransactionsById(long id) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 TempTransactions tempTransactions = (TempTransactions)session.createCriteria(TempTransactions.class)
				 .add(Restrictions.eq("id", id)).uniqueResult();
		 return tempTransactions;
	 }
	 public TempUserInfo getTempUserInfoByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 TempUserInfo tempUserInfo = (TempUserInfo)session.createCriteria(TempUserInfo.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 return tempUserInfo;
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
	 public void updateLogin(Login login) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(login);
			session.getTransaction().commit();
		 
	 }
	 public int getAttemptsByUsername(String username) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 Login login = (Login)session.createCriteria(Login.class)
				 .add(Restrictions.like("username", username)).uniqueResult();
		 if( login != null ) {
			 return login.getAttempt();
		 }
		 return 0;
	 }
	 public void deleteUserProfileByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 String internalUser = "InternalUser";
		 String externalUser = "ExternalUser";
		 String userInfo = "UserInfo";
		 String transactions = "Transactions";
		 String login = "Login";
		 session.beginTransaction();
		 String hql = "delete from "+internalUser+" where uniqId= :uniqId";
		 session.createQuery(hql).setString("uniqId", uniqId).executeUpdate();
		 hql = "delete from "+externalUser+" where uniqId like :uniqId";
		 session.createQuery(hql).setString("uniqId", uniqId).executeUpdate();
		 hql = "delete from "+userInfo+" where uniqId like :uniqId";
		 session.createQuery(hql).setString("uniqId", uniqId).executeUpdate();
		 hql = "delete from "+transactions+" where uniqId like :uniqId";
		 session.createQuery(hql).setString("uniqId", uniqId).executeUpdate();
		 hql = "delete from "+login+" where uniqId like :uniqId";
		 session.createQuery(hql).setString("uniqId", uniqId).executeUpdate();
		 session.getTransaction().commit();
		 
	 }
	 
	 public void updateExternalUser(ExternalUser extUser){
		 	Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(extUser);
			session.getTransaction().commit();
	 }
	 public ExternalUser getExternalUserByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 ExternalUser externalUser = (ExternalUser)session.createCriteria(ExternalUser.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 return externalUser;
	 }
}