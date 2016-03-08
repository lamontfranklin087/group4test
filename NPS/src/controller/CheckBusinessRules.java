package controller;

import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import model.Job;
import model.Manager;
import model.MyOwnException;
/**
 * @author Ihar Lavor
 * @version 02/27/2016 
 */
public class CheckBusinessRules {

	/** The number of maximum allowable jobs to be pending for any 7 day period. */
	private int MAX_JOBS_IN_7_CONSECUTIVE_DAYS = 5;
			
	public boolean checkJobDuration(int aDuration) throws MyOwnException {
		if (aDuration < 1 || aDuration > 2) {
			throw new MyOwnException("Job's duration can't be less than 0 or greater than 2.");
		}
		return true;
	}
	
	/**
	 * Checks if a volunteer signed up for this job.
	 * @param currentUser currently logged in user.
	 * @param tempJobID Job ID to search for.
	 * @param allJobs list of all available jobs.
	 * @throws MyOwnException if volunteer signed up for this job.
	 */
	public boolean isThereVolunteer(Manager currentUser, int tempJobID, Collection<Job> allJobs) throws MyOwnException {
		if (currentUser.findJob(tempJobID, allJobs).getTotalVolunteers() > 0) {
			throw new MyOwnException("You can't edit this job because volunteer already signed up for this job.");			
		}	
		return true;
	}
	/**
	 * Check for past jobs and remove them from list before user see them.
	 * @param allJobs is a list of all available jobs.
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
	 * @throws MyOwnException if user are too busy for that week.
	 */
	public boolean jobsIn7Days(Collection<Job> allJobs, Calendar mydate) throws MyOwnException {
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
		throw new MyOwnException("A job can't be added because you are to busy for that week.");
	}
	/**
	 * Check if user entered date is in past or not.
	 * @param futureJobDate is a job's date.
	 * @return true if date is not past, otherwise throw new MyOwnException.
	 * @throws MyOwnException if entered date is in past.
	 */
	public boolean checkForPastDate(Calendar futureJobDate) throws MyOwnException {
		Calendar currentDate = new GregorianCalendar();
		
		int curYear = currentDate.get(Calendar.YEAR);		
		int jobDayOfYear = futureJobDate.get(Calendar.DAY_OF_YEAR);
		int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
		
		if (curYear < futureJobDate.get(Calendar.YEAR)) {
			jobDayOfYear = jobDayOfYear + 365;
		}		
	 	int resultDays = jobDayOfYear - curDayOfYear;	
	 	if (resultDays > 0) {	 		
			return true;
	 	}
	 	throw new MyOwnException("You can't enter past date. ");
	}
	/**
	 * Check if user entered date is more than 90 days in future or not.
	 * @param futureJobDate is a job's date.
	 * @return true if date is not past, otherwise throw new MyOwnException.
	 * @throws MyOwnException if entered date is more than 90 days in future.
	 */
	public boolean checkForFutureDate(Calendar futureJobDate) throws MyOwnException {
		Calendar currentDate = new GregorianCalendar();
		
		int curYear = currentDate.get(Calendar.YEAR);		
		int jobDayOfYear = futureJobDate.get(Calendar.DAY_OF_YEAR);
		int curDayOfYear = currentDate.get(Calendar.DAY_OF_YEAR);
		
		if (curYear < futureJobDate.get(Calendar.YEAR)) {
			jobDayOfYear = jobDayOfYear + 365;
		}		
	 	int resultDays = jobDayOfYear - curDayOfYear;	
	 	if (resultDays < 90) {
			return true;
	 	}
	 	throw new MyOwnException("You can't enter date more than 90 days ahead. ");
	}
}
