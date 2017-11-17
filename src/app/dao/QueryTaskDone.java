package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.TaskDoneOfDay;
import app.utils.ConnectionUtils;

public class QueryTaskDone {

	public List<TaskDoneOfDay> getListTaskDone(int idUser){
		List<TaskDoneOfDay> listTask = new ArrayList<>();
		listTask.clear();
		try {
			Connection con = ConnectionUtils.getConnection();
			String query = "Select startDate,sum(taskDone) from job where idUser = ? group by startDate";
			PreparedStatement pst =  con.prepareStatement(query);
			pst.setInt(1, idUser);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				listTask.add(new TaskDoneOfDay(rs.getString(1), rs.getInt(2)));
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listTask;
	}

//	public static void main(String[] args) {
//		QueryTaskDone qTask = new QueryTaskDone();
//		List<TaskDoneOfDay> list = new ArrayList<>();
//			list = 	qTask.getListTaskDone(2);
//			String startDate = list.get(0).getDate();
//			String endDate = list.get(list.size()-1).getDate();
//		for(int i = 0;i< list.size(); i++) {
//			System.out.println(list.get(i).getDate() + '\t' + list.get(i).getTotalTaskDone());
//		}
//		System.out.println(startDate +'\t' + endDate);
//	}
}
