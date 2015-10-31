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
import pojo.OneTimePass;
import pojo.OtpTransactions;
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
		String hql = "delete from UserInfo where uniqId like :uniqId";
		 session.createQuery(hql).setString("uniqId", userInfo.getUniqId()).executeUpdate();
		session.save(userInfo);
		session.getTransaction().commit();
		session.close();
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
		//if (passFromDb != "" && passFromDb.equals(passwd)){
		if (passFromDb !=""&& BCrypt.checkpw(passwd, passFromDb)){
			return 1;
		}
		return 0;
	}
	
	
	public void saveOtpTransaction(OtpTransactions otpTransaction) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(otpTransaction);
		 session.getTransaction().commit();
	}
	public OtpTransactions getOtpTransactionById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 OtpTransactions tempTransactions = (OtpTransactions)session.createCriteria(OtpTransactions.class)
				 .add(Restrictions.eq("id", id)).uniqueResult();
		 return tempTransactions;
	}
	public OtpTransactions getOtpTransactionsByUniqId(String uniqId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		OtpTransactions results = (OtpTransactions) session.createCriteria(OtpTransactions.class)
				 .add( Restrictions.like("uniqId", uniqId)).uniqueResult();
		 if(results != null) {
			 return results;
		 }
		 return null;
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
	
	public List<TempTransactions> getTempTransactions() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		List<TempTransactions> results = (List<TempTransactions>) session.createCriteria(TempTransactions.class).list();
		 System.out.println("result"+results.size());
		 System.out.println("result obj:"+results.toString());
		 if(results != null) {
			System.out.println("Returning temp transactions");
			 return (List<TempTransactions>)results;
			 
		 }
		 return null;
	 }
	public void removeTempTransaction(TempTransactions remove){
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "delete from TempTransactions where Id= :Id";
		session.beginTransaction();
		 session.createQuery(hql).setString("Id", remove.getId()+"").executeUpdate();
		session.getTransaction().commit();
	}
	 public UserInfo getUserInfoByUniqId(String uniqId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 UserInfo userInfo = (UserInfo)session.createCriteria(UserInfo.class)
				 .add(Restrictions.like("uniqId", uniqId)).uniqueResult();
		 return userInfo;
	 }
	 /* Get user info */
	 public List<UserInfo> getUserInfo() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		 List<UserInfo> userInfo = (List<UserInfo>)session.createCriteria(UserInfo.class)
				 .list();
		 System.out.println("list size is "+userInfo.size());
		 return userInfo;
	 }
	 
	 public List<UserInfo> getApprovedUserInfo() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 @SuppressWarnings("unchecked")
		 List<UserInfo> userInfo = (List<UserInfo>)session.createCriteria(UserInfo.class)
				 .add(Restrictions.like("govapproval","approved" )).list();
		 System.out.println("list size is "+userInfo.size());
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
	 public long getAccountNoByUniqId(String uniqId) {
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
	 public long getEmployeeIdByUniqId(String uniqId) {
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
	 
	 public void saveOTP(OneTimePass otp) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		 session.save(otp);
		 session.getTransaction().commit();
	 }
	 
	 public OneTimePass getOneTimePassByUsername(String username) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 OneTimePass otp = (OneTimePass)session.createCriteria(OneTimePass.class)
				 .add(Restrictions.like("username", username)).uniqueResult();
		 return otp;
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
 public void deleteOtpByUsername(String username)
 {
	  Session session = HibernateUtil.getSessionFactory().openSession();
      session.beginTransaction();
      String hql="delete from OneTimePass where username=:username";
      session.createQuery(hql).setString("username", username).executeUpdate();
      session.getTransaction().commit();
	 
 }
 
 public ExternalUser getExternalUserByAccNum(long accountno) {
     Session session = HibernateUtil.getSessionFactory().openSession();
     ExternalUser externalUser = (ExternalUser)session.createCriteria(ExternalUser.class)
             .add(Restrictions.like("accountno",accountno )).uniqueResult();
     return externalUser;
 }
 
 public void deleteOtpTransactionById(String uniqueid) {
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
	 String hql = "delete from OtpTransactions where uniqid= :uniqueid";
	 session.createQuery(hql).setString("uniqueid", uniqueid).executeUpdate();
	 session.getTransaction().commit();
 }
 public void saveOtpTransactionToTransactionById(String uniqueid){
	 OtpTransactions oT = getOtpTransactionsByUniqId(uniqueid);
	 Transactions transaction = new Transactions(oT.getTransactionType(), oT.getUniqId(), oT.getDescription(), oT.getBalance(), oT.getTransactionAmount(),oT.getStatus());
	 transaction.setDate(oT.getDate());
	 deleteOtpTransactionById(uniqueid);
	 saveTransaction(transaction); 
 }
}