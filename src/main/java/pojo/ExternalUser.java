package pojo;

public class ExternalUser {
	private long id;
	private String uniqId;
	private long accountno;
	private float balance;
	private String authtrans;
	/**
	 * @param uniqId
	 * @param accountno
	 * @param balance
	 */
	public ExternalUser(String uniqId, long accountno, float balance, String authtrans) {
		super();
		this.uniqId = uniqId;
		this.accountno = accountno;
		this.balance = balance;
		this.authtrans = authtrans;
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
	/**
	 * @return the accountno
	 */
	public long getAccountno() {
		return accountno;
	}
	/**
	 * @param accountno the accountno to set
	 */
	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}
	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}
	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public void setAuthtrans(String authtrans){
		this.authtrans = authtrans;
	}
	
	public String getAuthtrans(){
		return this.authtrans;
	}
	
	/**
	 * 
	 */
	public ExternalUser() {
		
		// TODO Auto-generated constructor stub
	}
}