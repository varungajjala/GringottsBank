package pojo;

public class OtpInput {
	private String password;
	
	public OtpInput(){
		
	}
	
	public OtpInput(String password){
		this.password = password;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public String getPassword()
	{
		return this.password;
	}

}
