package app.model;

public class TaskDoneOJ {

	private int idUser;
	private int idJob;
	private int taskDone;
	private String startDate;
	
	public TaskDoneOJ(int idUser,int idJob, int taskDone, String startDate) {
		this.idUser = idUser;
		this.idJob = idJob;
		this.taskDone = taskDone;
		this.startDate = startDate;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdJob() {
		return idJob;
	}

	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}

	public int getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(int taskDone) {
		this.taskDone = taskDone;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}
