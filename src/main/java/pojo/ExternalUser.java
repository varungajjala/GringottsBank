package pojo;

public class ExternalUser {
	private long id;
	private String uniqId;
	private int accountno;
	private float balance;
	/**
	 * @param uniqId
	 * @param accountno
	 * @param balance
	 */
	public ExternalUser(String uniqId, int accountno, float balance) {
		super();
		this.uniqId = uniqId;
		this.accountno = accountno;
		this.balance = balance;
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
	public int getAccountno() {
		return accountno;
	}
	/**
	 * @param accountno the accountno to set
	 */
	public void setAccountno(int accountno) {
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
	/**
	 * 
	 */
	public ExternalUser() {
		
		// TODO Auto-generated constructor stub
	}
	public void setBalance(double d) {
		// TODO Auto-generated method stub
		
	}
}

