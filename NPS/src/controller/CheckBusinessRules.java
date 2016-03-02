package controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import model.Job;

public class CheckBusinessRules {

	/* The number of maximum allowable jobs to be pending for any 7 day period. */
	private int MAX_JOBS_IN_7_CONSECUTIVE_DAYS = 5;
	
	/* The max number of jobs that can be pending for all dates. */
	private final int MAX_NUMBER_JOBS = 30;	
	
	/**
	 * Check for past jobs and remove them from list before user see them.
	 */
	public void checkForPastJobs(Collection<Job> allJobs) {
		Calendar currentDate = new GregorianCalendar();		
		int curYear = currentDate.get(Calendar.YEAR);
		
		Calendar jobDate;
		int jobYear;
		boolean ifDeleted = false;
		
		if (allJobs != null && allJobs.size() > 0) {
			Iterator<Job> itr = allJobs.iterator();
			Job temp = null;
			while (itr.hasNext()) {				
				if (!ifDeleted) {
					temp = itr.next();					
				}
				jobDate = temp.getDate();
				jobYear = jobDate.get(Calendar.YEAR);	
								
				int jobDayOfYear = jobDate.get(Calendar.DAY_OF_YEAR);
				int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
				
				if (curYear < jobYear) {
					jobDayOfYear = jobDayOfYear + 365;
				}		
								
				if ((jobDayOfYear - curDayOfYear) <= 0) {				
					allJobs.remove(temp);
					itr = allJobs.iterator();
					temp = itr.next();
					ifDeleted = true;
				} else {
					ifDeleted = false;
				}
			}
		}	
	}
	
	/**
	 * Checking: if there during any consecutive 7 day period 
	 * more than 5 jobs or not.	 
	 * @param allJobs is a list of all Jobs.
	 * @param mydate is user entered date.
	 * @return false if there 5 or more jobs in 7 days, otherwise true.
	 */
	protected boolean jobsIn7Days(Collection<Job> allJobs, Calendar mydate) {
		int jobsIn7Days = 0;	
		int jobDayOfYear = mydate.get(Calendar.DAY_OF_YEAR);		
		if (allJobs.size() == 0) {
			return true;
		}
		Iterator<Job> itr = allJobs.iterator();
		if (allJobs != null && allJobs.size() > 0) {		
			while (itr.hasNext()) {
				int temp = itr.next().getDate().get(Calendar.DAY_OF_YEAR);
				if ((temp <= (jobDayOfYear + 3)) && (temp >= (jobDayOfYear - 3))) {					
					jobsIn7Days = jobsIn7Days + 1;
				}				
			}
			if (jobsIn7Days < MAX_JOBS_IN_7_CONSECUTIVE_DAYS) {
				return true;
			}
		}
		return false;
	}
}
