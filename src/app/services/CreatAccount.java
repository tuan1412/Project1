package app.services;

import app.dao.InsertAccount;
import app.dao.QueryUsername;

public class CreatAccount {
	public void creatAccount(String username,String password,String confirmpw) {
		QueryUsername qUsername = new QueryUsername();
		InsertAccount insAccount = new InsertAccount();
		if(qUsername.isExistUsername(username)) {
			System.out.println("Username existed!!");
		}
		else insAccount.insertAccount(username, password);
	}
}