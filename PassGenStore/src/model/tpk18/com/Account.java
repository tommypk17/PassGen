package model.tpk18.com;

public class Account {
	protected String siteName;
	protected String userName;
	protected String passWord;
	
	public Account(String siteName, String userName, String passWord){
		this.siteName = siteName;
		this.userName = userName;
		this.passWord = passWord;

	}
	
	public String getSiteName() {
		return siteName;
	}
	public String getPassword(){
		return passWord;
	}

	public String getStorable(){
		return this.siteName+":"+this.userName+":"+this.passWord+"\n";
	}

	public String toString() {
		return "<html>Site Name: "+this.siteName+"<br/>Username: "+this.userName+"<br/>Password: "+this.passWord+"</html>";
	}
}
