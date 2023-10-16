import java.util.Scanner;

public class PhoneBook {
    private final Scanner scanner = new Scanner(System.in);
    private final LinkedListADT<Contact> contacts = new LinkedListADT<>();
    private final LinkedListADT<Event> events = new LinkedListADT<>();

    /**
     * This is the main menu, it will display the options and return the user's choice
     */
    private int mainMenu() {
        System.out.println("Welcome ot the Linked Tree Phonebook!");
        System.out.println("Please choose an option");
        System.out.println("1. Add a contact");
        System.out.println("2. Search for a contact");
        System.out.println("3. Delete a contact");
        System.out.println("4. Schedule an event");
        System.out.println("5. Print event details");
        System.out.println("6. Print contacts by first name");
        System.out.println("7. Print all events alphabetically");
        System.out.println("8. Exit");


        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 8) {
                    System.err.println("Please enter a number between 1 and 8");
                } else {
                    return choice;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number between 1 and 8");
            }
        }
    }

    private void addContact() {
        Contact contact = new Contact();

        System.out.print("Enter the contact's name: ");
        contact.setName(scanner.nextLine());
        System.out.print("Enter the contact's phone number: ");
        contact.setPhoneNumber(scanner.nextLine(), scanner);

        System.out.print("Enter the contact's email address: ");
        contact.setEmailAddress(scanner.nextLine());
        System.out.print("Enter the contact's address: ");
        contact.setAddress(scanner.nextLine());
                System.out.print("Enter the contact's birth date (dd/MM/yyyy): ");
        contact.setBirthDate(scanner.nextLine(), scanner);
        System.out.print("Enter any notes about the contact: ");
        contact.setNotes(scanner.nextLine());


        // check if a contact with name and phone exists.
        ContactSearchCriteria searchCriteria = new ContactSearchCriteria(contact.getName(), 1);  // 1 corresponds to NAME
        ContactSearchCriteria phoneSearchCriteria = new ContactSearchCriteria(contact.getPhoneNumber(), 2);  // 2 corresponds to PHONE_NUMBER

        if (this.contacts.search(searchCriteria).isEmpty() && this.contacts.search(phoneSearchCriteria).isEmpty()) {
            this.contacts.add(contact);

            System.out.println("Contact added successfully!");
        } else {
            System.out.println("Contact with name or phone number already exists.");
        }
    }

    private void searchForContact() {
        System.out.println("Enter search criteria.");
        System.out.println("1. Name");
        System.out.println("2. Phone Number");
        System.out.println("3. Email Address");
        System.out.println("4. Address");
        System.out.println("5. Birthday");
        System.out.println("6. First Name");
        int choice;

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 6) {
                    System.err.println("Please enter a number between 1 and 6");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number between 1 and 6");
            }
        }

        String prompt = "";

        switch (choice) {
            case 1:
                prompt = "Enter the contact's name: ";
                break;
            case 2:
                prompt = "Enter the contact's phone number: ";
                break;
            case 3:
                prompt = "Enter the contact's email: ";
                break;
            case 4:
                prompt = "Enter the contact's address: ";
                break;
            case 5:
                prompt = "Enter the contact's birthday (dd/MM/yyyy): ";
                break;
            case 6:
                prompt = "Enter the contact's first name: ";
                break;
        }


        System.out.print(prompt);
        String search = scanner.nextLine();


        // Create a new search criteria based on the contact information
        ContactSearchCriteria criteria = new ContactSearchCriteria(search, choice);

        LinkedListADT<Contact> results = this.contacts.search(criteria);

        if (results.isEmpty()) {
            System.out.println("No contact found");
        } else {
            System.out.println("Contacts found!");
            System.out.println(results);
        }
    }
    private void deleteEventsAssociatedWithContact(Contact contact) {
        Node<Event> currentNode = events.getHead();
        while (currentNode != null) {
            if (currentNode.getData().getContact().equals(contact)) {
                events.remove(currentNode.getData());
            }
            currentNode = currentNode.getNext();
        }
    }
    private void deleteContact() {
        System.out.print("Enter the contact's name: ");
        String name = scanner.nextLine();

        ContactSearchCriteria criteria = new ContactSearchCriteria(name, 1);

        LinkedListADT<Contact> results = this.contacts.search(criteria);

        if (results.isEmpty()) {
            System.out.println("No contact found");
        } else {
            System.out.println("Contacts found!");
       

            Node<Contact> node = results.getHead();

            while (node != null) {
                System.out.println("Name: " + node.getData().getName());
                System.out.print("Are you sure you want to delete this contact? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    this.contacts.remove(node.getData());
                    System.out.println("Contact deleted successfully!");
                    deleteEventsAssociatedWithContact(node.getData());
                }
                
                node = node.getNext();
            }
        }
    }

    private void scheduleEvent() {
        Event event = new Event();
        String contactName = "";

        System.out.print("Enter the event's title: ");
        event.setTitle(scanner.nextLine());
        System.out.print("Enter contact's name: ");
        contactName = scanner.nextLine();
        
        System.out.print("Enter the event's date and time (dd/MM/yyyy HH:mm): ");
        event.setDate(scanner.nextLine(), scanner);
        System.out.print("Enter the event's location: ");
        event.setLocation(scanner.nextLine());

        ContactSearchCriteria criteria = new ContactSearchCriteria(contactName, 1);
        LinkedListADT<Contact> results = this.contacts.search(criteria);


        if (results.isEmpty()) {
            System.out.println("No contact found");
        } else if (results.size() > 1) {
            System.out.println("Multiple contacts found");
        } else {
            Node<Contact> node = results.getHead();

            if (node.getData().notConflictingEvents(event)) {
                System.out.println("This event conflicts with another event for this contact");
                return;
            }

            event.setContact(node.getData());
            node.getData().addEvent(event);

            this.events.add(event);

            System.out.println("Event added successfully!");
        }

    }

    private void printEventDetails() {
        System.out.println("Enter search criteria:");
        System.out.println("1. Contact Name");
        System.out.println("2. Event Title");

        int choice;

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) {
                    System.err.println("Please enter a number between 1 and 2");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number between 1 and 2");
            }
        }

        String prompt = "";

        switch (choice) {
            case 1://contact name
                prompt = "Enter the contact's name: ";
                break;
            case 2:// event title
                prompt = "Enter the event's title: ";
                break;
        }

        System.out.print(prompt);

        String searchFor = scanner.nextLine();

        EventSearchCriteria criteria = new EventSearchCriteria(searchFor, choice);

        LinkedListADT<Event> results = this.events.search(criteria);

        if (results.isEmpty()) {
            System.out.println("No event found");
        } else {
            System.out.println("Events found!");
            System.out.println(results);
        }
    }

    private void printContactsByFirstName() {
        System.out.print("Enter the first name: ");

        String firstName = scanner.nextLine();

        ContactSearchCriteria criteria = new ContactSearchCriteria(firstName, 6);  // 6 corresponds to FIRST_NAME
        LinkedListADT<Contact> results = this.contacts.search(criteria);

        if (results.isEmpty()) {
            System.out.println("No contact found");
        } else {
            System.out.println("Contacts found!");
            System.out.println(results);
        }
    }

    private void printAllEventsAlphabetically() {  // it is already sorted alphabetically in add method in linked list
    	if(this.events.isEmpty())
			System.out.println("There are no events scheduled");
		else
			System.out.println(this.events);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        int choice;
        do {
            choice = phoneBook.mainMenu();

            switch (choice) {
                case 1:
                    phoneBook.addContact();
                    break;
                case 2:
                    phoneBook.searchForContact();
                    break;
                case 3:
                    phoneBook.deleteContact();
                    break;
                case 4:
                    phoneBook.scheduleEvent();
                    break;
                case 5:
                    phoneBook.printEventDetails();
                    break;
                case 6:
                    phoneBook.printContactsByFirstName();
                    break;
                case 7:
                    phoneBook.printAllEventsAlphabetically();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    break;
            }
        } while (choice != 8);

    }
}
