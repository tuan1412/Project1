package app.controller;

import app.model.Job;

/**
 * Class de truyen tham so giua cac controller
 * 
 * @author Chu lun Kute
 *
 */
public class ContextController {
	private final static ContextController instance = new ContextController();

	private ContextController() {
		
	}
	
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
	//job de play
	private Job job;

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
}
