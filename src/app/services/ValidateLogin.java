package app.services;

import app.dao.QueryAccount;


/**
 * Class kiểm tra đăng nhập
 * @author HUYQUANGPTN
 *
 */
public class ValidateLogin {
	
	public int isValidate(String username, String password) {
		if ("".equals(username) || "".equals(password)) {
			return 0;
		}
		QueryAccount qAccount = new QueryAccount();
		return qAccount.isExistAccount(username, password);
	}
}
