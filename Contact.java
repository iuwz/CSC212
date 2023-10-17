import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Contact implements Comparable<Contact> {
    /**
     * The name of the contact
     */
    private String name;//1

    /**
     * The email address of the contact
     */
    private String emailAddress;//1


    /**
     * The phone number of the contact
     */
    private String phoneNumber;//1

    /**
     * The address of the contact
     */
    private String address;//1

    /**
     * The birth date of the contact. Format is dd/MM/yyyy
     */
    private String birthDate;//1

    /**
     * Any notes about the contact
     */
    private String notes;//1

    /**
     * The events belonging to this contact
     */
    private LinkedListADT<Event> events;

    public Contact() {
        this.events = new LinkedListADT<>();//1 //every contact have his own events linked list
    }

    public String getName() {
        return name;//1
    }

    public String getPhoneNumber() {
        return phoneNumber;//1
    }

    public LinkedListADT<Event> getEvents() {
        return events;//1
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public String getEmailAddress() {
        return emailAddress;//1
    }

    public String getAddress() {
        return address;//1
    }

    public String getBirthDate() {
        return birthDate;//1
    }

    public String getNotes() {
        return notes;//1
    }

    public void setName(String name) {
        this.name = name;//1
    }

    public void setPhoneNumber(String phoneNumber, Scanner scanner) {
        while (true) {//n
            try {
                long checkIfNum = Long.parseLong(phoneNumber); //n // this variable is only used to throw a NumberFormatException
                if (phoneNumber.length() != 10)//n
                    throw new RuntimeErrorException(null);//n
                else {
                    this.phoneNumber = phoneNumber;//1
                    break;//1
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid 10-digit phone number: ");//n
                phoneNumber = scanner.nextLine();//n
            } catch (RuntimeErrorException e) {
                System.out.print("Phone number must be exactly 10 digits. Try again: ");//n
                phoneNumber = scanner.nextLine();//n
            }
        }
    }

    public void setEmailAddress(String emailAddress, Scanner scanner) {
        while (true) {//n
            // Splitting the string by '@' and by '.' and checking the resulting arrays' lengths.
            String[] atSplit = emailAddress.split("@");//n
            String[] dotSplit = emailAddress.split("\\.");//n

            // Checking if email contains '@' and '.' but not at the beginning or end because if the @ or . is in the end the last item in the array will be empty or the first item will be empty
            if (atSplit.length == 2 && dotSplit.length >= 2 && !atSplit[atSplit.length-1].isEmpty() && !dotSplit[dotSplit.length-1].isEmpty() && !atSplit[0].isEmpty() && !dotSplit[0].isEmpty() ) {//n
                this.emailAddress = emailAddress;//1
                break;//1
            } else {
                System.out.print("Please enter a valid email: ");//n
                emailAddress = scanner.nextLine();//n
            }
        }
    }


    public void setAddress(String address) {
        this.address = address;//n
    }

    public void setBirthDate(String birthDate, Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");//1
        
        while (true) {
            try {
                Date date = sdf.parse(birthDate);//n // this variable is only used to throw a parseException
                this.birthDate = birthDate;//1
                break;//1  // If successfully parsed, break out of the loop
            } catch (ParseException e) {
                System.out.print("Incorrect date format. Please enter again (dd/MM/yyyy): ");//n
                birthDate = scanner.nextLine();//n
            }
        }
}

    public void setNotes(String notes) {
        this.notes = notes;//1
    }
    public String getFirstName() {
        if (name != null && name.contains(" ")) {//1
            return name.split(" ")[0]; //1 // Assuming the name is "First Last" format and [0] is added because we want it to return the first split of the array
        }
        return name;//1
    }
    public void deleteAllEvents() {
        this.events = new LinkedListADT<>();//1
    }

    public boolean notConflictingEvents(Event event) {
        return !this.events.search(data -> data.conflict(event)).isEmpty();//n //for each node in events linked list get it's data and check if there is conflict between the data and the given event
    }


    public String toString() {
        return "Name: " + name +"\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress +  "\nAddress: " + address + "\nBirth Date: " + birthDate + "\nNotes: " + notes + "\n";//1
    }

    @Override
    public int compareTo(Contact contact) {
        return this.name.compareTo(contact.getName());//1
    }
}
