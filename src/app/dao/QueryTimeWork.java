package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.TimeWorkOfDay;
import app.utils.ConnectionUtils;

public class QueryTimeWork {
	public List<TimeWorkOfDay> getListTimeWork(int idUser){
		List<TimeWorkOfDay> listTime = new ArrayList<>();
		listTime.clear();
		try {
			Connection con = ConnectionUtils.getConnection();
			String query = "Select * from Statistic where idUser = ?";
			PreparedStatement pst =  con.prepareStatement(query);
			pst.setInt(1, idUser);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				listTime.add(new TimeWorkOfDay(rs.getString(2), rs.getInt(3)));
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listTime;
	}

	public TimeWorkOfDay getTimeWorkOfDay(int idUser,String date) {
		TimeWorkOfDay timeWork = null;
		try {
			Connection con = ConnectionUtils.getConnection();
			String query = "Select * from Statistic where idUser = ? and date = ?";
			PreparedStatement pst =  con.prepareStatement(query);
			pst.setInt(1, idUser);
			pst.setString(2, date);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				timeWork = new TimeWorkOfDay(rs.getString(2), rs.getInt(3));
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return timeWork;
	}
//	public static void main(String[] args) {
//		QueryTimeWork test = new QueryTimeWork();
//		System.out.println(test.getTimeWorkOfDay(2, "2017-11-08").getDate() + '\t' +  test.getTimeWorkOfDay(2, "2017-11-08").getMinutesWork());
//	}
}
