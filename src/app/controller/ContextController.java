package app.controller;

/**
 * Class de truyen tham so giua cac controller
 * 
 * @author Chu lun Kute
 *
 */
public class ContextController {
	private final static ContextController instance = new ContextController();

	public static ContextController getInstance() {
		return instance;
	}
	
	//login user da dang nhap
	private int iduser;

	public int getLoggedUserId() {
		return iduser;
	}

	public void setLoggedUserId(int iduser) {
		this.iduser = iduser;
	}
}
