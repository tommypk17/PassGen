package view.tpk18.com;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;

public class PassGenStoreView extends JFrame{
	protected MainPanel panelMain;
	protected LoginPanel panelLogin;
	protected NewAccountPanel panelNewAccount;

	
	public PassGenStoreView(){
		setLayout(new GridLayout(1, 1));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		panelMain = new MainPanel();
		panelNewAccount = new NewAccountPanel();
		panelLogin = new LoginPanel();
		
		getRootPane().setDefaultButton(this.getMainPanel().buttonSearch);
		
		add(panelLogin);

		resetSize();
	}
	public void resetSize(){
		pack();
		Dimension windowSize = new Dimension(350, 300);
		setSize(windowSize);
	}
	public MainPanel getMainPanel(){
		return this.panelMain;
	}
	public LoginPanel getLoginPanel(){
		return this.panelLogin;
	}
	public NewAccountPanel getNewAccountPanel(){
		return this.panelNewAccount;
	}
	public void addPassGenStoreListener(ActionListener listener, ListSelectionListener listListener, MouseListener mouseListener, ActionListener menuListener){
		panelLogin.buttonLogin.addActionListener(listener);
		panelLogin.buttonCancel.addActionListener(listener);
		panelNewAccount.buttonGenerate.addActionListener(listener); 
		panelNewAccount.buttonAdd.addActionListener(listener); 
		panelNewAccount.buttonCancel.addActionListener(listener);
		panelMain.buttonSearch.addActionListener(listener); 
		panelMain.buttonCancel.addActionListener(listener);
		panelMain.buttonAddNew.addActionListener(listener); 
		panelMain.buttonDelete.addActionListener(listener);
		panelMain.listAccounts.addListSelectionListener(listListener);
		panelMain.listAccounts.addMouseListener(mouseListener);
		panelMain.menuItem.addActionListener(menuListener);
		
	}
	
}
