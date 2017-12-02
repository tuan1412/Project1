package app.services;

import app.dao.UpdateTimeWork;

public class UpdateStatistic {

	public void updateStatistic(int idUser,String date,int minutes) {
		UpdateTimeWork updateTime = new UpdateTimeWork();
		updateTime.updateTimeWork(idUser, date, minutes);
	}
}
