package app.model;

public class Job {
	private int idjob;
	private int iduser;
	private int taskNumber;
	private int taskDone;
	private int longBreakTime;
	private int workTime;
	private int shortBreakTime;
	private String title;
	private String pause;
	private String startDate;
	private int state;
	
	
	public Job(String title) {
		super();
		this.title = title;
	}

	public Job(int idjob, int iduser, int taskNumber, int taskDone, int longBreakTime, int workTime, int shortBreakTime,
			String title, String pause, String startDate, int state) {
		super();
		this.idjob = idjob;
		this.iduser = iduser;
		this.taskNumber = taskNumber;
		this.taskDone = taskDone;
		this.longBreakTime = longBreakTime;
		this.workTime = workTime;
		this.shortBreakTime = shortBreakTime;
		this.title = title;
		this.pause = pause;
		this.startDate = startDate;
		this.state = state;
	}

	public int getIdjob() {
		return idjob;
	}

	public void setIdjob(int idjob) {
		this.idjob = idjob;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public int getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(int taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(int taskDone) {
		this.taskDone = taskDone;
	}

	public int getLongBreakTime() {
		return longBreakTime;
	}

	public void setLongBreakTime(int longBreakTime) {
		this.longBreakTime = longBreakTime;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPause() {
		return pause;
	}

	public void setPause(String pause) {
		this.pause = pause;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.title;
	}
	
	
}