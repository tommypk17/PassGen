package view.tpk18.com;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;

import model.tpk18.com.Account;

public class MainPanel extends JPanel{	
	protected JTextField textSearch;
	protected JTextArea textStorage;
	protected JScrollPane textScrollStorage;
	protected JList<Account> listAccounts;
	
	protected JButton buttonSearch;
	protected JButton buttonCancel;
	protected JButton buttonAddNew;
	protected JButton buttonDelete;
	
	protected JMenuItem menuItem;
	protected JPopupMenu popUpMenu;
	
	public MainPanel(){
		GridBagConstraints c = new GridBagConstraints();
		setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		textSearch = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		add(textSearch, c);
		
		listAccounts = new JList<Account>();
		listAccounts.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listAccounts.setLayoutOrientation(JList.VERTICAL_WRAP);
		textScrollStorage = new JScrollPane(listAccounts);
		textScrollStorage.setPreferredSize(new Dimension(250, 200));
		textScrollStorage.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		add(textScrollStorage, c);

		c.gridwidth = 1;
		buttonSearch = new JButton("Search");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 0;
		add(buttonSearch, c);
		
		c.gridwidth = 1;
		buttonCancel = new JButton("Cancel");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		c.gridx = 3;
		c.gridy = 2;
		add(buttonCancel, c);
		
		
		buttonAddNew = new JButton("Add New Account");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		add(buttonAddNew, c);
		
		buttonDelete = new JButton("Delete");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		buttonDelete.setEnabled(false);
		add(buttonDelete, c);	
		
		
		
		menuItem = new JMenuItem("Copy Password");
		
	}

	public JButton getButtonSearch() {
		return buttonSearch;
	}
	
	public JButton getButtonCancel(){
		return buttonCancel;
	}
	
	public JButton getButtonAddNew() {
		return buttonAddNew;
	}

	public JButton getButtonDelete() {
		return buttonDelete;
	}

	public JTextField getTextSearch() {
		return textSearch;
	}
	public JMenuItem getMenuItem(){
		return menuItem;
	}
	public void resetTextSearch(){
		textSearch.setText("");
	}
	public JList<Account> getList(){
		return listAccounts;
	}
	public void setListAccounts(ArrayList<Account> e) {
		Account[] list = new Account[e.size()];
		int i = 0;
		for(Account a: e){
			list[i] = a;
			i++;
		}
		this.listAccounts.setListData(list);
	}
}
