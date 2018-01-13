package model.tpk18.com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class PassGenStoreModel {
	private Scanner si;
	private File file;
	private Writer wr;
	private String fileName;
	private ArrayList<Account> accounts;
	private Account selectedAccount;
	public PassGenStoreModel(String fileName){
		this.file = new File(fileName);
		accounts = new ArrayList<Account>();
		try{
		si = new Scanner(this.file);
		}catch(FileNotFoundException e){
			System.out.println("Cant find file!");
		}
		while(si.hasNextLine()){
			String line = Encrypt.rot13(si.nextLine());
			Account account = null;
			String siteName = line.substring(0, line.indexOf(":"));
			String userName = line.substring(line.indexOf(":")+1, line.lastIndexOf(":"));
			String passWord = line.substring(line.lastIndexOf(":")+1, line.length());
			account = new Account(siteName, userName, passWord);
			accounts.add(account);
		}
		
		
	}
	public void deleteAccount(Account a){
		accounts.remove(a);
		save();
	}
	public void addAccount(Account account){
		accounts.add(account);
		save();
	}
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}
	public Account getSelectedAccount(){
		return selectedAccount;
	}
	public void setSelectedAccount(Account a){
		selectedAccount = a;
	}
	public ArrayList<Account> searchAccounts(String siteName){
		Account account = null;
		ArrayList<Account> found = new ArrayList<Account>();
		for(Account a: this.accounts){
			if(a.getSiteName().toUpperCase().contains(siteName.toUpperCase())){
				account = a;
				found.add(a);
			}
		}
		if(found.isEmpty()){
			return null;
		}
		return found;
	}

	public boolean login(String userName, String passWord){
		Login login = new Login(userName, passWord);
		return login.checkCredentials();
	}
	public void save(){
		
		try{
		wr = new FileWriter(this.file);
		for(Account a: accounts){
			wr.write(Encrypt.rot13(a.getStorable()));
		}
		wr.close();
		}catch(IOException e){
			System.out.println("Issue Writing!");
		}
	}
}
