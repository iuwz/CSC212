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
        this.title = title;
        this.location = location;
    }

    public Event() {
    }

    public Contact getContact() {
        return contact;
    }

    public String getTitle() {
        return title;
    }

    public Calendar getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean conflict(Event event) {
        return this.date.equals(event.getDate());
    }

     public void setDate(String dateString, Scanner scanner, LinkedListADT<Event> allEvents) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        while (true) {
            try {
                Date date = sdf.parse(dateString);
                Calendar proposedDate = Calendar.getInstance();
                proposedDate.setTime(date);

                // Check for conflicts with other events
                boolean conflict = false;
                Node<Event> currentNode = allEvents.getHead();
                while (currentNode != null) {
                    if (currentNode.getData().getDate().equals(proposedDate)) {
                        conflict = true;
                        break;
                    }
                    currentNode = currentNode.getNext();
                }

                if (conflict) {
                    System.out.println("There is already an event scheduled at this time.");
                    System.out.print("Please choose another time or date (dd/MM/yyyy HH:mm): ");
                    dateString = scanner.nextLine();
                    continue; // Will go back to the beginning of the while loop
                }

                this.date = proposedDate;
                break;  // If successfully parsed and no conflict, break out of the loop

            } catch (ParseException e) {
                System.out.print("Incorrect date format. Please enter again (dd/MM/yyyy HH:mm): ");
                dateString = scanner.nextLine();
            }
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public int compareTo(Event event) {
        return this.getTitle().compareTo(event.getTitle());
    }

    @Override
    public String toString() {
        return "Event title: " + this.title + "\n" +
        		 "Contact name: " + this.contact.getName() + "\n"+
                "Date and time: " + this.date.getTime() + "\n" +
                "Event location: " + this.location + "\n" ;
    }
}
