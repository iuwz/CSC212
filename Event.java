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

   

    public void setDate(Scanner scanner) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        while (true) {
            try {
                System.out.print("Enter the event's start date and time (dd/MM/yyyy HH:mm): ");
                this.startTime = formatter.parse(scanner.nextLine());

                System.out.print("Enter the event's end date and time (dd/MM/yyyy HH:mm): ");
                this.endTime = formatter.parse(scanner.nextLine());

                if (endTime.before(startTime) || endTime.equals(startTime)) {
                    System.out.println("End time should be after start time. Please try again.");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid date or time. Please try again.");
            }
        }
    }

    public void setLocation(String location) {
        this.location = location;//1
    }

    @Override
    public int compareTo(Event event) {
        return this.getTitle().compareTo(event.getTitle());//1
    }
public boolean isConflicting(Event otherEvent) {
        return (this.startTime.before(otherEvent.getEndTime()) && otherEvent.getStartTime().before(this.endTime));
    }
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Title: " + this.title + "\n" +
               "Contact: " + this.contact.getName() + "\n" +
               "Starting time: " + sdf.format(this.startTime.getTime()) + "\n" +
               "Ending time: " + sdf.format(this.endTime.getTime()) + "\n" +
               "Location: " + this.location;
    }
}
