package controller;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;

import model.Job;
import model.User;

public class ReadWriteSerialFile {
	
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	/**
	 * Read all jobs and user from a file.
	 */
	public void readSerialFile() {
		try {
			allUsers = SerialStartup.serialReadUsers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allUsers = new LinkedList<User>();
		}
		try {
			allJobs = SerialStartup.serialReadJobs();						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			allJobs = new LinkedList<Job>();
		}
		
		if (allJobs == null || allJobs.size() == 0) {
			allJobs = new LinkedList<Job>();
		}
	}
	
	/**
	 * Writes all jobs and user to a file.
	 * @param anAllUsers 
	 * @param anAllJobs 
	 */
	public void writeSerialFile(Collection<Job> anAllJobs, Collection<User> anAllUsers) {
		if (anAllUsers != null) {
			try {
				SerialStartup.serialWriteUsers(anAllUsers);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}
		if (anAllJobs != null) {
			try {
				SerialStartup.serialWriteJobs(anAllJobs);
			} catch (FileNotFoundException e) {
				e.printStackTrace();			
			}
		}	
	}
	
	public Collection<User> getAllUsers() {
		return allUsers;
	}
	public Collection<Job> getAllJobs() {
		return allJobs;
	}
}
