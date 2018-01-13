package model.tpk18.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login {
	private String userName;
	private String passWord;
	private Scanner si;
	private File file;
	public Login(String userName, String passWord){
		file = new File("login.txt");
		this.userName = userName;
		this.passWord = passWord;
		try{
		si = new Scanner(file);
		}catch(FileNotFoundException e){
			System.out.println("Could not find file");
		}
		
	}
	public boolean checkCredentials(){
		boolean login = false;
		String line = Encrypt.rot13(si.nextLine());
		String checkUserName = line.substring(0, line.lastIndexOf(":"));
		String checkPassWord = line.substring(line.lastIndexOf(":")+1,line.length());
		if(checkUserName.equals(this.userName)&&checkPassWord.equals(this.passWord)){
			login = true;
		}
		return login;
	}
}
