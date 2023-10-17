import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Event class
 * Stores information about an event
 */
public class Event implements Comparable<Event> {
    /**
     * The title of the event
     */
    private String title;
    /**
     * The date and time of the event
     */
    private Calendar date;

    /**
     * The location of the event
     */
    private String location;

    /**
     * The contact associated with the event
     */
    private Contact contact;

    public Event(String title, String location) {
        this.title = title;//1
        this.location = location;//1
    }

    public Event() {
    }

    public Contact getContact() {
        return contact;//1
    }

    public String getTitle() {
        return title;//1
    }

    public Calendar getDate() {
        return date;//1
    }

    public String getLocation() {
        return location;//1
    }

    public void setTitle(String title) {
        this.title = title;//1
    }

    public void setContact(Contact contact) {
        this.contact = contact;//1
    }

    public boolean conflict(Event event) {
        return this.date.equals(event.getDate());//1
    }

    public void setDate(String dateString, Scanner scanner, LinkedListADT<Event> allEvents) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");//1
        while (true) {//n
            try {
                Date date = sdf.parse(dateString);//n
                Calendar proposedDate = Calendar.getInstance();//n
                proposedDate.setTime(date);//n

                // Check for conflicts with other events
                boolean conflict = false;//n
                Node<Event> currentNode = allEvents.getHead();//n
                while (currentNode != null) {//n*n=n²
                    if (currentNode.getData().getDate().equals(proposedDate)) {//n
                        conflict = true;//1
                        break;//1
                    }
                    currentNode = currentNode.getNext();//n
                }

                if (conflict) {//n
                    System.out.println("There is already an event scheduled at this time.");//n
                    System.out.print("Please choose another time or date (dd/MM/yyyy HH:mm): ");//n
                    dateString = scanner.nextLine();//n
                    continue;//n // Will go back to the beginning of the while loop the nearest one
                }

                this.date = proposedDate;//1
                break;//1  // If successfully parsed and no conflict, break out of the loop

            } catch (ParseException e) {
                System.out.print("Incorrect date format. Please enter again (dd/MM/yyyy HH:mm): ");//n
                dateString = scanner.nextLine();//n
            }
        }
        //n²+15n+5
    }

    public void setLocation(String location) {
        this.location = location;//1
    }

    @Override
    public int compareTo(Event event) {
        return this.getTitle().compareTo(event.getTitle());//1
    }

    @Override
    public String toString() {
        return "Title: " + this.title + "\n" + //1
        		 "Contact: " + this.contact.getName() + "\n"+ //1
                "Date: " + this.date.getTime() + "\n" + //1
                "Location: " + this.location + "\n" ;//1
          //4
    }
}
