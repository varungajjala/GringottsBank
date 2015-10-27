package pojo;
public class InternalUser {
	private long id;
	private String uniqId;
	private long empId;
	/**
	 * @param uniqId
	 * @param empId
	 */
	public InternalUser(String uniqId, long empId) {
		super();
		this.uniqId = uniqId;
		this.empId = empId;
	}
	/**
	 * 
	 */
	public InternalUser() {
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
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	
	
}