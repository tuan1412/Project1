package app.services;

import java.time.LocalDate;
import java.util.List;

import app.dao.QueryJobNotDone;
import app.model.Job;

public class GetListJobNotDone {
	private int iduser;
	private LocalDate startDate;
	
	public GetListJobNotDone(int iduser, LocalDate startDate) {
		super();
		this.iduser = iduser;
		this.startDate = startDate;
	}
	
	public List<Job> getJobs(){
		QueryJobNotDone queryJob = new QueryJobNotDone(iduser, startDate);
		return queryJob.getJobs();
	}
}
