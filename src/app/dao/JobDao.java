package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import app.model.Job;
import app.model.JobForm;
import app.utils.ConnectionUtils;

public class JobDao {
	public List<Job> queryJobDone(int iduser, LocalDate startDate){
		List<Job> jobs = new ArrayList<>();
		try {
			String date = startDate.toString();
			Connection connection = ConnectionUtils.getConnection();
			String sql = "select * from Job where iduser = ? and startDate = ? and state = 1";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, iduser);
			pst.setString(2, date);

			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idjob = rs.getInt("idjob");
				int taskNumber = rs.getInt("taskNumber");
				int workTime = rs.getInt("workTime");
				int longBreakTime = rs.getInt("longBreakTime");
				int shortBreakTime = rs.getInt("shortBreakTime");
				int taskDone = rs.getInt("taskDone");
				int state = rs.getInt("state");
				String des = rs.getString("des");
				String title = rs.getString("title");
				String pause = rs.getString("pause");
			
				jobs.add(new Job(idjob, iduser, taskNumber, taskDone, longBreakTime, workTime, shortBreakTime, title, pause, startDate.toString(), state, des));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	public List<Job> queryJobNotDone(int iduser, LocalDate startDate){
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
				int taskNumber = rs.getInt("taskNumber");
				int workTime = rs.getInt("workTime");
				int longBreakTime = rs.getInt("longBreakTime");
				int shortBreakTime = rs.getInt("shortBreakTime");
				int taskDone = rs.getInt("taskDone");
				int state = rs.getInt("state");
				String des = rs.getString("des");
				String title = rs.getString("title");
				String pause = rs.getString("pause");
			
				jobs.add(new Job(idjob, iduser, taskNumber, taskDone, longBreakTime, workTime, shortBreakTime, title, pause, startDate.toString(), state, des));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public List<Job> queryJobOverDate(int iduser, LocalDate startDate){
		List<Job> jobs = new ArrayList<>();
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "select * from Job where iduser = ? and startDate < ? and state = 0";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, iduser);
			pst.setString(2, startDate.toString());

			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				int idjob = rs.getInt("idjob");
				int taskNumber = rs.getInt("taskNumber");
				int workTime = rs.getInt("workTime");
				int longBreakTime = rs.getInt("longBreakTime");
				int shortBreakTime = rs.getInt("shortBreakTime");
				int taskDone = rs.getInt("taskDone");
				int state = rs.getInt("state");
				String des = rs.getString("des");
				String title = rs.getString("title");
				String pause = rs.getString("pause");
			
				jobs.add(new Job(idjob, iduser, taskNumber, taskDone, longBreakTime, workTime, shortBreakTime, title, pause, startDate.toString(), state, des));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;
	}
	
	public boolean insert(JobForm jobForm) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "insert into Job (iduser, taskNumber, longBreakTime, shortBreakTime, workTime, title, des, startDate) "
					+ "values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, jobForm.getIduser());
			pst.setInt(2, jobForm.getTaskNumber());
			pst.setInt(3, jobForm.getLongBreakTime());
			pst.setInt(4, jobForm.getShortBreakTime());
			pst.setInt(5, jobForm.getWorkTime());
			pst.setString(6, jobForm.getTitle());
			pst.setString(7, jobForm.getDes());
			pst.setString(8, jobForm.getStartDate().toString());
			pst.execute();	
			flag = true;
			pst.close();
			ConnectionUtils.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateDone(int id) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "update job set state = 1 where idjob = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();
			flag = true;
			pst.close();
			ConnectionUtils.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean updateTaskDoneAndPause(Job job) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "update job set taskDone = ?, pause = ? where idjob = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, job.getTaskDone());
			pst.setString(2, job.getPause());
			pst.setInt(3, job.getIdjob());
			pst.executeUpdate();
			flag = true;
			pst.close();
			ConnectionUtils.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean removeByID(int id) {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "delete from job where idjob = ?";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, id);
			
			pst.executeUpdate();
			flag = true;
			pst.close();
			ConnectionUtils.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
}
