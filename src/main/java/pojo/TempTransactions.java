package pojo;
import java.sql.Timestamp;
import org.springframework.web.multipart.MultipartFile;
public class TempTransactions {
	private long id;
	private String transactionType;
	private String uniqId;
	private String description;
	private float balance;
	private Timestamp date;
	private float transactionAmount;
	private int accountno;
	private MultipartFile mpFile;
	private MultipartFile pkFile;
	/**
	 * @param transactionType
	 * @param uniqId
	 * @param description
	 * @param balance
	 */
	public TempTransactions(String transactionType, String uniqId, String description, float balance, float transactionAmount,int accountno, MultipartFile mpFile, MultipartFile pkFile) {
		this.transactionType = transactionType;
		this.uniqId = uniqId;
		this.description = description;
		this.setTransactionAmount(transactionAmount);
		this.balance = balance;
		this.accountno = accountno;
		this.mpFile = mpFile;
		this.pkFile = pkFile;
	}
	/**
	 * 
	 */
	public TempTransactions() {
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
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/**
	 * @return the transactionAmount
	 */
	public float getTransactionAmount() {
		return transactionAmount;
	}
	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public int getAccountno() {
		return accountno;
	}
	/**
	 * @param accountno the accountno to set
	 */
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public MultipartFile getMpFile() {
		return mpFile;
	}
	
	public void setMpFile(MultipartFile file) {
		this.mpFile = file;
	}
	
	public MultipartFile getPkFile() {
		return pkFile;
	}
	
	public void setPkFile(MultipartFile file) {
		this.pkFile = file;
	}
}