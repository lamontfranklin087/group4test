package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class SerialStartup {
	private Collection<Job> allJobs;
	private Collection<User> allUsers;	
	
	public SerialStartup() throws FileNotFoundException {
		//User testUser123 = new Volunteer();
		allJobs = new LinkedList<Job>();
		allUsers = new LinkedList<User>();
		//allUsers.add(testUser123);
		
		Scanner scanner = new Scanner(new File ("userlist.txt"));
		/*setFirstName(String theFirstName);
		setLastName(String theLastName);
		setEmail(String theEmail);
		setPassword(String thePassword);
		*/
		User readUser;
		while (scanner.hasNext()){
			
			int userType = Integer.parseInt(scanner.next());
			String fName = scanner.next();
			String lName = scanner.next();
			String email = scanner.next();
			String password = scanner.next();
			if (userType==1) readUser = new UrbanParksStaff(userType,fName,lName,email,password);
			else if (userType==2) readUser = new Manager(userType,fName,lName,email,password);
			else if (userType==3) readUser = new Volunteer(userType,fName,lName,email,password);
			else readUser=null;
			if (readUser!=null) allUsers.add(readUser);
			else scanner.close();
			
		}
		allUsers.forEach(user->System.out.println(user.toString()));
		//User e = null;
		try
	      {
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
