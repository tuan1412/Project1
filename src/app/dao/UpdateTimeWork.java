package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.utils.ConnectionUtils;

public class UpdateTimeWork {

	public void updateTimeWork (int idUser,String date,int minutes) {
		Connection con = ConnectionUtils.getConnection();
		PreparedStatement pst = null;
		try {
			String query = "Select * from Statistic where iduser = ? and date = ? ";
			pst = con.prepareStatement(query);
			pst.setInt(1, idUser);
			pst.setString(2, date);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				int tMinutes = rs.getInt(3);
				tMinutes += minutes;
				String query1 = "Update Statistic set minutesWork = ? where iduser = ? and date = ? ";
				pst = con.prepareStatement(query1);
				pst.setInt(1, tMinutes);
				pst.setInt(2, idUser);
				pst.setString(3, date);
				pst.execute();
			}else {
				String query2 = "Insert into Statistic (iduser,date,minutesWork) values (?,?,?) ";
				pst = con.prepareStatement(query2);
				pst.setInt(1, idUser);
				pst.setString(2, date);
				pst.setInt(3, minutes);
				pst.execute();
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		UpdateTimeWork test = new UpdateTimeWork();
//		test.updateTimeWork(2, "2017-11-08", 100);
//	}
}
