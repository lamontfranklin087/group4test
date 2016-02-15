package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

// Author Lamont Franklin 2/8/16

public class SerialStartup {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	
	
	public static void serialWriteUsers(Collection<User> myUsers)throws FileNotFoundException{
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("users.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(myUsers);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized user data is saved in users.txt\n");
	      } catch(IOException i) {
	          i.printStackTrace();
	      }
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<User> serialReadUsers()throws FileNotFoundException{	
		try {
			FileInputStream fileIn = new FileInputStream("users.txt");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        Collection<User> readColl = (Collection<User>) in.readObject();
	      			
	        in.close();
	        fileIn.close();
	        return readColl;
	    } catch(IOException i) {
	        i.printStackTrace();
	        return null;
	    } catch(ClassNotFoundException c) {
	        System.out.println("Employee class not found");
	        c.printStackTrace();
	        return null;
	    }
	}	
	
	public static void serialWriteJobs(Collection<Job> myJobs)throws FileNotFoundException{
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("jobs.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(myJobs);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized job data is saved in jobs.txt\n");
	      } catch(IOException i) {
	          i.printStackTrace();
	      }
	}
	
	@SuppressWarnings("unchecked")
	public static Collection<Job> serialReadJobs()throws FileNotFoundException{	
		try {
			FileInputStream fileIn = new FileInputStream("jobs.txt");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        Collection<Job> readColl = (Collection<Job>) in.readObject();
	      
	        in.close();
	        fileIn.close();
	        return readColl;
	    } catch(IOException i) {
	        i.printStackTrace();
	        return null;
	    } catch(ClassNotFoundException c) {
	        System.out.println("Employee class not found");
	        c.printStackTrace();
	        return null;
	    }
	}
	
	
	
	@SuppressWarnings("unchecked")
	public SerialStartup() throws FileNotFoundException {
		allJobs = new LinkedList<Job>();
		allUsers = new LinkedList<User>();
		
		Scanner scanner = new Scanner(new File ("userlist.txt"));
		/*setFirstName(String theFirstName);
		setLastName(String theLastName);
		setEmail(String theEmail);
		setPassword(String thePassword);
		*/
		User readUser;			
		while (scanner.hasNext()) {	
			String userData = scanner.nextLine();
			String[] parts = userData.split(", ");
		
			if (parts[0].equalsIgnoreCase("UrbanParksStaff")) {
				readUser = new UrbanParksStaff(parts[1],parts[2],parts[3],parts[4]);
			} else if (parts[0].equalsIgnoreCase("Manager")) {				
				ArrayList<String> parksList = new ArrayList<String>();
				for (int i = 5; i < parts.length; i++) {
					System.out.println(parts[i]);
					parksList.add(parts[i]);
				}				
				readUser = new Manager(parts[1],parts[2],parts[3],parts[4], parksList);
			} else if (parts[0].equalsIgnoreCase("Volunteer")) {
				readUser = new Volunteer(parts[1],parts[2],parts[3],parts[4]);
			} else {
				readUser = null;
			}
			
			if (readUser != null) {
				allUsers.add(readUser);
			} else {
				scanner.close();
			}			
		}
		allUsers.forEach(user->System.out.println(user.toString()));
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("users.txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(allUsers);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in users.txt\n");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
			Collection<User> loadedUsers;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("users.txt");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         loadedUsers = (Collection<User>) in.readObject();
	        
				
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return;
	      }
	      System.out.println("Deserialized Users...");
	      loadedUsers.forEach(user->System.out.println(user.toString()));
}
	
}
