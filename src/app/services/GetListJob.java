package app.services;

import java.time.LocalDate;
import java.util.List;

import app.dao.QueryJobDone;
import app.model.Job;

public class GetListJob {
	private int iduser;
	private LocalDate startDate;
	
	public GetListJob(int iduser, LocalDate startDate) {
		super();
		this.iduser = iduser;
		this.startDate = startDate;
	}
	
	public List<Job> getJobs(){
		QueryJobDone queryJob = new QueryJobDone(iduser, startDate);
		return queryJob.getJobs();
	}
}
