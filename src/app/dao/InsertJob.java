package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import app.utils.ConnectionUtils;

/**
 * Insert job vao database
 * @author Chu lun Kute
 *
 */
public class InsertJob {
	private int iduser;
	private LocalDate startDate;
	private String title;
	private String des;
	private int taskNumber;
	private int workTime;
	private int shortBreakTime;
	private int longBreakTime;
	
	public InsertJob(int iduser, LocalDate startDate, String title, String des, int taskNumber, int workTime, int shortBreakTime,
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
	

	public boolean insert() {
		boolean flag = false;
		try {
			Connection connection = ConnectionUtils.getConnection();
			String sql = "insert into Job (iduser, taskNumber, longBreakTime, shortBreakTime, workTime, title, des, startDate) "
					+ "values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.setInt(1, iduser);
			pst.setInt(2, taskNumber);
			pst.setInt(3, longBreakTime);
			pst.setInt(4, shortBreakTime);
			pst.setInt(5, workTime);
			pst.setString(6, title);
			pst.setString(7, des);
			pst.setString(8, startDate.toString());
			pst.execute();	
			flag = true;
			pst.close();
			ConnectionUtils.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args) {
		InsertJob insertJob = new InsertJob(1, LocalDate.now(), "First Job", "Create Job", 3, 25, 5, 15);
		insertJob.insert();
	}
}
