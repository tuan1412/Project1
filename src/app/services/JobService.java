package app.services;

import java.time.LocalDate;
import java.util.List;

import app.dao.JobDao;
import app.model.Job;
import app.model.JobForm;

public class JobService {
	JobDao jobDao = new JobDao();
	
	public List<Job> queryJobDone(int iduser, LocalDate startDate){
		return jobDao.queryJobDone(iduser, startDate);
	}
	
	public List<Job> queryJobNotDone(int iduser, LocalDate startDate){
		return jobDao.queryJobNotDone(iduser, startDate);
	}
	
	public List<Job> queryJobOverdate(int iduser, LocalDate startDate){
		return jobDao.queryJobOverDate(iduser, startDate);
	}
	
	public boolean insertJob(JobForm jobForm) {
		return jobDao.insert(jobForm);
	}
	
	public boolean updateDone(int id) {
		return jobDao.updateDone(id);
	}
	
	public boolean updateJob(Job job) {
		if (job.getTaskDone() == job.getTaskNumber()) {
			return jobDao.updateDone(job.getIdjob());
		}
		return jobDao.updateTaskDoneAndPause(job);
	}
	public static void main(String[] args) {
		JobService jobService = new JobService();
		List<Job> jobs = jobService.queryJobOverdate(1, LocalDate.of(2017, 11, 8));
		jobs.stream().forEach(e->System.out.println(e.getIdjob()));
	}
}
