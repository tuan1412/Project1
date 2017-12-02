package app.model;

import java.time.LocalDate;

public class JobForm {
	private int iduser;
	private LocalDate startDate;
	private String title;
	private String des;
	private int taskNumber;
	private int workTime;
	private int shortBreakTime;
	private int longBreakTime;
	
	public JobForm() {
		
	}
	
	public JobForm(int iduser, LocalDate startDate, String title, String des, int taskNumber, int workTime,
			int shortBreakTime, int longBreakTime) {
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

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getWorkTime() {
		return workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public int getShortBreakTime() {
		return shortBreakTime;
	}

	public void setShortBreakTime(int shortBreakTime) {
		this.shortBreakTime = shortBreakTime;
	}

	public int getLongBreakTime() {
		return longBreakTime;
	}

	public void setLongBreakTime(int longBreakTime) {
		this.longBreakTime = longBreakTime;
	}

}
