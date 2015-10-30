package pojo;
public class UserInfo {
	private long id;
	private String firstName;
	private String lastName;
	private String contactNo;
	private String emailId;
	private String username;
	private String uniqId;
	private String address;
	private String city;
	private int zipcode;
	private String state;
	private String country;
	private long identificationNo;
	private String utype;
	private String passwd;
	private String govapproval;
	/**
	 * @param firstName
	 * @param lastName
	 * @param contactNo
	 * @param emailId
	 * @param username
	 * @param uniqId
	 * @param address
	 * @param city
	 * @param zipcode
	 * @param state
	 * @param country
	 * @param identificationNo
	 */
	public UserInfo(String firstName, String lastName, String contactNo, String emailId, String username, String uniqId,
			String address, String city, int zipcode, String state, String country, long identificationNo) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.username = username;
		this.uniqId = uniqId;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.country = country;
		this.setIdentificationNo(identificationNo);
	}
	
	/**
	 * 
	 */
	public UserInfo() {
		super();
	}
	
	
	public String getutype() {
		return utype;
	}
	/**
	 * @param utype the utype to set
	 */
	public void setutype(String utype) {
		this.utype = utype;
	}
	
	public String getPasswd() {
		return this.passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}
	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the identificationNo
	 */
	public long getIdentificationNo() {
		return identificationNo;
	}
	/**
	 * @param identificationNo the identificationNo to set
	 */
	public void setIdentificationNo(long identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getGovapproval() {
		return govapproval;
	}

	public void setGovapproval(String govapproval) {
		this.govapproval = govapproval;
	}
	
}