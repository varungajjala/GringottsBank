package pojo;

public class OneTimePass {
	private String username;
	private String inititme;
	private String exptime;
	private int passwd;
	private long id;
	
	public OneTimePass(){
		
	}
	
	public OneTimePass(String username, String inittime, String exptime, int passwd){
		this.username = username;
		this.inititme = inittime;
		this.exptime = exptime;
		this.passwd = passwd;
	}
	
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setInittime(String inittime){
		this.inititme = inittime;
	}
	
	public String getInittime(){
		return this.inititme;
	}
	
	public void setExptime(String exptime){
		this.exptime = exptime;
	}
	
	public String getExptime(){
		return this.exptime;
	}
	
	public void setPasswd(int passwd){
		this.passwd = passwd;
	}
	
	public int getPasswd(){
		return this.passwd;
	}
}
