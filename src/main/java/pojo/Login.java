package pojo;

import java.util.Date;

public class Login {

	private String userId;
	private String passwd;
	private String role;
	private String uniqId;
	private long id;
	private String status;
	private int attempt;
	private int loginstatus;
	private String logintime;

	public Login() {
	}

	public Login(String userId, String passwd, String role, String uniqId, String status, int attempt, int loginstatus, String logintime) {
		this.userId = userId;
		this.passwd = passwd;
		this.role = role;
		this.uniqId = uniqId;
		this.status = status;
		this.setAttempt(attempt);
		this.loginstatus = loginstatus;
		this.logintime = logintime;
	}
	


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the uniqId
	 */
	public String getUniqId() {
		return uniqId;
	}

	/**
	 * @param uniqId the uniqId to set
	 */
	public void setUniqId(String uniqId) {
		this.uniqId = uniqId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the attempts
	 */
	public int getAttempt() {
		return attempt;
	}

	/**
	 * @param attempts the attempts to set
	 */
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public int getLoginstatus(){
		return this.loginstatus;
	}
	
	public void setLoginstatus(int loginstatus){
		this.loginstatus = loginstatus;
	}
	
	public String getLogintime(){
		return this.logintime;
	}
	
	public void setLogintime(String logintime){
		this.logintime = logintime;
	}
}
