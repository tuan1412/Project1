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

//	public static void main(String[] args) {
//		QueryTimeWork qTask = new QueryTimeWork();
//		List<TimeWorkOfDay> list = new ArrayList<>();
//			list = 	qTask.getListTimeWork(2);
//			String startDate = list.get(0).getDate();
//			String endDate = list.get(list.size()-1).getDate();
//		for(int i = 0;i< list.size(); i++) {
//			System.out.println(list.get(i).getDate() + '\t' + list.get(i).getMinutesWork());
//		}
//		System.out.println(startDate +'\t' + endDate);
//	}

}
