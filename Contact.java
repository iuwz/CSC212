import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Contact implements Comparable<Contact> {
    /**
     * The name of the contact
     */
    private String name;

    /**
     * The email address of the contact
     */
    private String emailAddress;


    /**
     * The phone number of the contact
     */
    private String phoneNumber;

    /**
     * The address of the contact
     */
    private String address;

    /**
     * The birth date of the contact. Format is dd/MM/yyyy
     */
    private String birthDate;

    /**
     * Any notes about the contact
     */
    private String notes;

    /**
     * The events belonging to this contact
     */
    private LinkedListADT<Event> events;

    public Contact() {
        this.events = new LinkedListADT<>();//every contact have his own events linked list
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LinkedListADT<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber, Scanner scanner) {
while(true) {
	try {
		int checkIfNum = Integer.parseInt(phoneNumber);// this variable is only used to throw a NumberFormatException
		if(phoneNumber.length()!=10)
			throw new RuntimeErrorException(null);
		else {
			this.phoneNumber= phoneNumber;
			break;
	}
}catch(NumberFormatException e) {
	System.out.print("Please enter a valid 10 digit phone number:");
	phoneNumber=scanner.nextLine();
	
}catch(RuntimeErrorException e){
	System.out.print("Please enter a valid 10 digit phone number:");
	phoneNumber=scanner.nextLine();
}
	

	}
}

     public void setEmailAddress(String emailAddress, Scanner scanner) {
       while(true) {
    	   try {
    		   if(emailAddress.split("@")[0].equals(emailAddress)||emailAddress.split(".")[0].equals(emailAddress))throw new RuntimeErrorException(null);
    		   else {
    			   this.emailAddress=emailAddress;
    			   break;
    		   }
    	   }catch(RuntimeErrorException e) {
    		   System.out.print("Please enter a valid email");
    		   emailAddress=scanner.nextLine();
    	   }
       }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate, Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        while (true) {
            try {
                Date date = sdf.parse(birthDate);// this variable is only used to throw a parseException
                this.birthDate = birthDate;
                break;  // If successfully parsed, break out of the loop
            } catch (ParseException e) {
                System.out.print("Incorrect date format. Please enter again (dd/MM/yyyy): ");
                birthDate = scanner.nextLine();
            }
        }
}

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getFirstName() {
        if (name != null && name.contains(" ")) {
            return name.split(" ")[0]; // Assuming the name is "First Last" format and [0] is added because we want it to return the first split of the array
        }
        return name;
    }
    public void deleteAllEvents() {
        this.events = new LinkedListADT<>();
    }

    public boolean notConflictingEvents(Event event) {
        return !this.events.search(data -> data.conflict(event)).isEmpty();//for each node in events linked list get it's data and check if there is conflict between the data and the given event
    }


    public String toString() {
        return "Name: " + name +"\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress +  "\nAddress: " + address + "\nBirth Date: " + birthDate + "\nNotes: " + notes + "\n";
    }

    @Override
    public int compareTo(Contact contact) {
        return this.name.compareTo(contact.getName());
    }
}
