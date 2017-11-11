package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.model.Job;
import app.utils.ConnectionUtils;
/**
 * Chon danh sach job theo iduser va ngay
 * @author Chu lun Kute
 *
 */
public class QueryJobDone {
	private int iduser;
	private LocalDate startDate;
	
	public QueryJobDone(int iduser, LocalDate startDate) {
		super();
		this.iduser = iduser;
		this.startDate = startDate;
	}
	
	public List<Job> getJobs(){
		List<Job> jobs = new ArrayList<>();
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "select * from Job where iduser = ? and startDate = ? and state = 0";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, iduser);
			pst.setString(2, startDate.toString());

			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idjob = rs.getInt("idjob");
				int iduser = rs.getInt("iduser");
				int taskNumber = rs.getInt("taskNumber");
				int workTime = rs.getInt("workTime");
				int longBreakTime = rs.getInt("longBreakTime");
				int shortBreakTime = rs.getInt("shortBreakTime");
				int taskDone = rs.getInt("taskDone");
				int state = rs.getInt("state");
				String title = rs.getString("title");
				String pause = rs.getString("pause");
				String startDate = rs.getString("startDate");
				
				Job job = new Job(idjob, iduser, taskNumber, taskDone, longBreakTime, workTime, shortBreakTime, title, pause, startDate, state);
				jobs.add(job);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
}
