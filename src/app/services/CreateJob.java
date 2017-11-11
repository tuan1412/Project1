package app.services;

import java.time.LocalDate;

import app.dao.InsertJob;

/**
 * Class de tao job moi
 * @author Chu lun Kute
 *
 */
public class CreateJob {
	private int iduser;
	private LocalDate startDate;
	private String title;
	private String des;
	private int taskNumber;
	private int workTime;
	private int shortBreakTime;
	private int longBreakTime;
	
	public CreateJob(int iduser, LocalDate startDate, String title, String des, int taskNumber, int workTime, int shortBreakTime,
			int longBreakTime) {
		super();
		this.iduser = iduser;
		this.startDate = startDate;
		this.title = title;
		this.des = des;
		this.taskNumber = taskNumber;
		this.workTime = workTime;
		this.shortBreakTime = shortBreakTime;
		this.longBreakTime = longBreakTime;
	}
	
	public boolean create() {
		InsertJob insertJob = new InsertJob(iduser, startDate, title, des, taskNumber, workTime, shortBreakTime, longBreakTime);
		return insertJob.insert();
	}
}
