package app.model;

public class TimeWorkOfDay {

	private int minutesWork;
	private String date;
	
	public TimeWorkOfDay(String date,int minutesWork) {
		this.minutesWork = minutesWork;
		this.date = date;
	}

	public int getMinutesWork() {
		return minutesWork;
	}

	public void setMinutesWork(int minutesWork) {
		this.minutesWork = minutesWork;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	

	
	
}
