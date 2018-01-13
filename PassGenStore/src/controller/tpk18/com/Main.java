package controller.tpk18.com;

import model.tpk18.com.PassGenStoreModel;
import view.tpk18.com.PassGenStoreView;

public class Main {

	public static void main(String[] args) {
		PassGenStoreView view = new PassGenStoreView();
		PassGenStoreModel model = new PassGenStoreModel("accounts.txt");
		new PassGenStoreController(view, model);
		
		view.setVisible(true);
	}

}
