package app.model;

public class TaskDoneOfDay {

	private int totalTaskDone;
	private String date;
	
	public TaskDoneOfDay( String date,int totalTaskDone) {
		this.totalTaskDone = totalTaskDone;
		this.date = date;
	}

	public int getTotalTaskDone() {
		return totalTaskDone;
	}

	public void setTotalTaskDone(int totalTaskDone) {
		this.totalTaskDone = totalTaskDone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
