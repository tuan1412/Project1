package app.services;

import app.dao.UserDao;

public class UserService {
	UserDao userDao = new UserDao();
	
	public int validateLogin(String username, String password) {
		if ("".equals(username) || "".equals(password)) {
			return 0;
		}
		return userDao.findByUsernameAndPassword(username, password);
	}
	
	public boolean create(String username, String password) {
		return userDao.insert(username, password);
	}
	
	
}
