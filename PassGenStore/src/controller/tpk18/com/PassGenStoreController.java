package controller.tpk18.com;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.tpk18.com.Account;
import model.tpk18.com.PassGen;
import model.tpk18.com.PassGenStoreModel;
import view.tpk18.com.PassGenStoreView;


public class PassGenStoreController {
	protected PassGenStoreView view;
	protected PassGenStoreModel model;
	
	public PassGenStoreController(PassGenStoreView view, PassGenStoreModel model) {
		
		this.view = view;
		this.model = model;
		this.view.addPassGenStoreListener(new Listener(), new ListListener(), new MouseListen(), new MenuListener());
		
	}
	public class ListListener implements ListSelectionListener{
		@Override
		public void valueChanged(ListSelectionEvent e) {
				view.getMainPanel().getButtonDelete().setEnabled(true);
				model.setSelectedAccount(view.getMainPanel().getList().getSelectedValue());
		}
		
	}
	public class MouseListen extends MouseAdapter{
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	        JPopupMenu menu = new JPopupMenu();
	        menu.add(view.getMainPanel().getMenuItem());
	        if(view.getMainPanel().getList().getSelectedValue()!=null){
	        	menu.show(e.getComponent(), e.getX(), e.getY());
	        	
	        }
	    }
	}
	public class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			StringSelection strSel = new StringSelection(model.getSelectedAccount().getPassword());
			clipboard.setContents(strSel, null);
		}
		
	}

	public class Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			//
			//MainPanel Events
			//
			if(source == view.getMainPanel().getButtonSearch()){
				if(view.getMainPanel().getTextSearch().getText().equals("")){
					view.getMainPanel().setListAccounts(model.getAccounts());
					view.getMainPanel().getButtonDelete().setEnabled(false);
				}else{
					try{
						view.getMainPanel().setListAccounts(model.searchAccounts(view.getMainPanel().getTextSearch().getText()));
						view.getMainPanel().getButtonDelete().setEnabled(true);
					}catch(NullPointerException pointerException){
						JOptionPane.showMessageDialog(view, "Could not find specified site");
						view.getMainPanel().getButtonDelete().setEnabled(false);
					}
				}

			}
			else if(source == view.getMainPanel().getButtonCancel()){
				view.getMainPanel().setListAccounts(model.getAccounts());
				view.getMainPanel().getButtonDelete().setEnabled(false);
				view.getMainPanel().resetTextSearch();
			}
			else if(source == view.getMainPanel().getButtonAddNew()){
				view.remove(view.getMainPanel());
				view.add(view.getNewAccountPanel());
				view.resetSize();

			}else if(source == view.getMainPanel().getButtonDelete()){
				Account accountToDelete = model.getSelectedAccount();
				int opt = JOptionPane.showConfirmDialog(view, "Are you sure you would like to delete the following acount?\n"+ accountToDelete);
				if(opt == 0){
					model.deleteAccount(accountToDelete);
					view.getMainPanel().setListAccounts(model.getAccounts());
					JOptionPane.showMessageDialog(view, "Account Deleted!");
					view.getMainPanel().resetTextSearch();
					view.getMainPanel().getButtonDelete().setEnabled(false);
				}
			}
			//
			//NewAccountPanel Events
			//
			else if(source == view.getNewAccountPanel().getButtonGenerate()){
				view.getNewAccountPanel().getTextPassword().setText(PassGen.generatePassword());
				
			}else if(source == view.getNewAccountPanel().getButtonAdd()){

					String siteName = view.getNewAccountPanel().getTextSite().getText();
					String userName = view.getNewAccountPanel().getTextUserName().getText();
					String passWord = view.getNewAccountPanel().getTextPassword().getText();
					if(siteName.equals("")){
						JOptionPane.showMessageDialog(view, "Please enter a Site name");
					}if(userName.equals("")){
						JOptionPane.showMessageDialog(view, "Please enter a Username ");
					}if(passWord.equals("")){
						JOptionPane.showMessageDialog(view, "Please enter a Password");
					}if(!siteName.equals("")&&!userName.equals("")&&!passWord.equals("")){
					Account newAccount = new Account(siteName, userName, passWord);
					model.addAccount(newAccount);
					view.getNewAccountPanel().setTextSite("");
					view.getNewAccountPanel().setTextPassword("");
					view.getNewAccountPanel().setTextUserName("");
					JOptionPane.showMessageDialog(view, "Here is your account info:\n"+newAccount.toString());
					}

			
			}else if(source == view.getNewAccountPanel().getButtonCancel()){
				view.getNewAccountPanel().getTextSite().setText("");
				view.getNewAccountPanel().getTextUserName().setText("");
				view.getNewAccountPanel().getTextPassword().setText("");
				view.remove(view.getNewAccountPanel());
				view.add(view.getMainPanel());
				view.getMainPanel().setListAccounts(model.getAccounts());
				view.resetSize();
			
			}
			//
			//LoginPanel Events
			//
			else if(source == view.getLoginPanel().getButtonLogin()){
				view.remove(view.getLoginPanel());
				view.add(view.getMainPanel());
				view.getMainPanel().setListAccounts(model.getAccounts());
				view.resetSize();
				/*
				if(model.login(view.getLoginPanel().getTextUserName().getText(), view.getLoginPanel().getTextPassword().getText())){
					view.remove(view.getLoginPanel());
					view.add(view.getMainPanel());
					view.getMainPanel().setTextStorage(model.listAccounts());
					view.resetSize();
				}else{
					JOptionPane.showMessageDialog(view, "Incorrect Username or Password");
				}*/
				

			}else if(source == view.getLoginPanel().getButtonCancel()){
				System.exit(0);
			}
			
			
		}
		
	}
}
