import java.util.Scanner;

public class PhoneBook {
	private final Scanner scanner = new Scanner(System.in);
	private final LinkedListADT<Contact> contacts = new LinkedListADT<>();
	private final LinkedListADT<Event> events = new LinkedListADT<>();

	/**
	 * This is the main menu, it will display the options and return the user's choice
	 */
	private int mainMenu() {
		System.out.println("Please choose an option: "); //1	
		System.out.println("1. Add a contact"); //1
		System.out.println("2. Search for a contact"); //1
		System.out.println("3. Delete a contact"); //1
		System.out.println("4. Schedule an event"); //1
		System.out.println("5. Print event details"); //1
		System.out.println("6. Print contacts by first name"); //1
		System.out.println("7. Print all events alphabetically"); //1
		System.out.println("8. Exit"); //1


		while (true) { //n
			try { 
				System.out.print("Enter your choice: "); //n
				int choice = Integer.parseInt(scanner.nextLine()); //n
				System.out.println(); //n
				if (choice < 1 || choice > 8) { //n
					System.err.println("Please enter a number between 1 and 8"); //n
				} else {
					return choice; //1
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number between 1 and 8"); //1
			}
		}
		//6n+11
	}

	private void addContact() {
		Contact contact = new Contact(); //1

		System.out.print("Enter the contact's name: "); //1
		contact.setName(scanner.nextLine()); //1
		System.out.print("Enter the contact's phone number: "); //1
		contact.setPhoneNumber(scanner.nextLine(), scanner); //1*(6n)=6n
		System.out.print("Enter the contact's email address: "); //1
		contact.setEmailAddress(scanner.nextLine(), scanner); //1*(6n)=6n
		System.out.print("Enter the contact's address: "); //1
		contact.setAddress(scanner.nextLine()); //1
		System.out.print("Enter the contact's birth date (dd/MM/yyyy): "); //1
		contact.setBirthDate(scanner.nextLine(), scanner); //1*(4n+1)=4n+1
		System.out.print("Enter any notes about the contact: "); //1
		contact.setNotes(scanner.nextLine()); //1


		// check if a contact with name and phone exists.
		ContactSearchCriteria searchCriteria = new ContactSearchCriteria(contact.getName(), 1); //1*(2n)=2n // 1 corresponds to NAME
		ContactSearchCriteria phoneSearchCriteria = new ContactSearchCriteria(contact.getPhoneNumber(), 2); //1*(2n)=2n  // 2 corresponds to PHONE_NUMBER

		if (this.contacts.search(searchCriteria).isEmpty() && this.contacts.search(phoneSearchCriteria).isEmpty()) { //2(3n²+28n+4)=6n²+56n+8
			this.contacts.add(contact); //1*(3n+14)=3n+14

			System.out.println("Contact added successfully!"); //1
		} else {
			System.out.println("Contact with name or phone number already exists."); //1
		}
		System.out.println();//1		//6n²+79n+35
	}

	
    private void searchForContact() {
        System.out.println("Enter search criteria."); //1
        System.out.println("1. Name"); //1
        System.out.println("2. Phone Number"); //1
        System.out.println("3. Email Address"); //1
        System.out.println("4. Address"); //1
        System.out.println("5. Birthday"); //1
        int choice;

        while (true) { //n
            try {
                System.out.print("Enter your choice: "); //n
                choice = Integer.parseInt(scanner.nextLine()); //n
                if (choice < 1 || choice > 5) { //n
                    System.err.println("Please enter a number between 1 and 5"); //n
                } else {
                    break; //1
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number between 1 and 5"); //n
            }
        }

        String prompt = ""; //1

        switch (choice) {
            case 1: //1
                prompt = "Enter the contact's name: "; //1
                break; //1
            case 2: //1
                prompt = "Enter the contact's phone number: "; //1
                break; //1
            case 3: //1
                prompt = "Enter the contact's email: "; //1
                break; //1
            case 4: //1
                prompt = "Enter the contact's address: "; //1
                break; //1
            case 5: //1
                prompt = "Enter the contact's birthday (dd/MM/yyyy): "; //1
                break; //1
           
        }


        System.out.print(prompt); //1
        String searchFor = scanner.nextLine(); //1


        // Create a new search criteria based on the contact information
        ContactSearchCriteria criteria = new ContactSearchCriteria(searchFor, choice); //1

        LinkedListADT<Contact> results = this.contacts.search(criteria); //3n^2+28n+4

        if (results.isEmpty()) { //1
            System.out.println("No contact found"); //1
        } else {
            System.out.println("Contacts found!"); //1
            System.out.println(results); //n
        }
    }
	
	private void deleteEventsAssociatedWithContact(Contact contact) {
		Node<Event> currentNode = events.getHead(); //1
		while (currentNode != null) { //n+1
			if (currentNode.getData().getContact().equals(contact)) { //1*n
				events.remove(currentNode.getData()); //n*(3n+9)= 4n²+9n
			}
			currentNode = currentNode.getNext(); //1*n
		}
		//4n²+12n+2
	}
	
	private void deleteContact() {
		System.out.print("Enter the contact's name: "); //1
		String name = scanner.nextLine(); //1

		ContactSearchCriteria criteria = new ContactSearchCriteria(name, 1); //1*(2n)=2n

		LinkedListADT<Contact> results = this.contacts.search(criteria); //1*(3n²+28n+4)=3n²+28n+4

		if (results.isEmpty()) { //1
			System.out.println("No contact found"); //1
		} else {
			System.out.println("Contacts found!"); //1

			Node<Contact> node = results.getHead(); //1

			while (node != null) { //n+1
				System.out.println("Name: " + node.getData().getName()); //1*n
				System.out.print("Are you sure you want to delete this contact? (y/n): "); //1*n
				String choice = scanner.nextLine(); //1*n
				if (choice.equalsIgnoreCase("y")) { //1*n
					this.contacts.remove(node.getData()); //n*(3n+9)=3n²+9n
					System.out.println("Contact deleted successfully!");//1*n
					deleteEventsAssociatedWithContact(node.getData()); //n*(4n²+12n+2)=4n³+12n²+2n
				}
				node = node.getNext(); //1*n
			}
		}
		System.out.println(); //1
		//4n³+18n²+48n+12
	}

	private void scheduleEvent() {
        Event event = new Event(); //1
        String contactName = ""; //1

        System.out.print("Enter the event's title: "); //1
        event.setTitle(scanner.nextLine()); //1
        System.out.print("Enter contact's name: "); //1
        contactName = scanner.nextLine(); //1
        
        event.setDate(scanner); //9n+1 // setting both start and end times

        System.out.print("Enter the event's location: "); //1
        event.setLocation(scanner.nextLine()); //1

        ContactSearchCriteria criteria = new ContactSearchCriteria(contactName, 1); //1
        LinkedListADT<Contact> results = this.contacts.search(criteria); //3n²+28n+5

        if (results.isEmpty()) { //1
            System.out.println("No contact found"); //1
        } else if (results.size() > 1) { //1
            System.out.println("Multiple contacts found"); //1
        } else {
            Node<Contact> node = results.getHead(); //1

            if (this.hasConflict(event)) { //2n+4
                System.out.println("This event conflicts with another event for this contact or other contacts"); //1
                return; //1
            }

            event.setContact(node.getData()); //1
            node.getData().addEvent(event); //1

            this.events.add(event); //3n+14

            System.out.println("Event added successfully!"); //1
        }
    }
    private boolean hasConflict(Event newEvent) {  //2n+3
        Node<Event> currentEventNode = events.getHead(); //1
        while (currentEventNode != null) { //n
            if (newEvent.isConflicting(currentEventNode.getData())) { //n
                return true; //1
            }
            currentEventNode = currentEventNode.getNext(); //1
        }
        return false; //1
    }

	private void printEventDetails() {
		System.out.println("Enter search criteria:"); //1
		System.out.println("1. Contact Name"); //1
		System.out.println("2. Event Title"); //1

		int choice;

		while (true) { //n
			try {
				System.out.print("Enter your choice: "); //n
				choice = Integer.parseInt(scanner.nextLine()); //n
				if (choice < 1 || choice > 2) { //n
					System.err.println("Please enter a number between 1 and 2"); //n
				} else {
					break; //1
				}
			} catch (NumberFormatException e) {
				System.err.println("Please enter a number between 1 and 2"); //n
			}
		}

		String prompt = ""; //1

		switch (choice) {
		case 1: //1 //contact name
			prompt = "Enter the contact's name: "; //1
			break; //1
		case 2: //1 // event title
			prompt = "Enter the event's title: "; //1
			break; //1
		}

		System.out.print(prompt); //1

		String searchFor = scanner.nextLine(); //1

		EventSearchCriteria criteria = new EventSearchCriteria(searchFor, choice); //1

		LinkedListADT<Event> results = this.events.search(criteria); //3n²+28n+5

		if (results.isEmpty()) { //1
			System.out.println("No event found"); //1
		} else {
			System.out.println("Events found!"); //1
			System.out.println(results); //n
		}
		System.out.println(); //1 
		// 3n²+35n+19
	}

	private void printContactsByFirstName() {
		System.out.print("Enter the first name: "); //1

		String firstName = scanner.nextLine(); //1

		ContactSearchCriteria criteria = new ContactSearchCriteria(firstName, 6); //1  // 6 corresponds to FIRST_NAME
		LinkedListADT<Contact> results = this.contacts.search(criteria); //3n²+28n+5

		if (results.isEmpty()) { //1
			System.out.println("No contact found"); //1
		} else {
			System.out.println("Contacts found!"); //1
			System.out.println(results); //n
		}
		System.out.println();//1
		//3n²+29n+9
	}

	private void printAllEventsAlphabetically() {  // it is already sorted alphabetically in add method in linked list
		if(this.events.isEmpty())  //1
			System.out.println("There are no events scheduled"); //1
		else
			System.out.println(this.events); //n
		//n+1
	}

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook(); 
		System.out.println("Welcome to the Linked Tree Phonebook!"); 
		int choice;
		do {
			choice = phoneBook.mainMenu(); //O(n)

			switch (choice) {
			case 1:
				phoneBook.addContact(); //O(n²)
				break;
			case 2:
				phoneBook.searchForContact(); //O(n2)
				break;
			case 3:
				phoneBook.deleteContact(); //O(n³)
				break;
			case 4:
				phoneBook.scheduleEvent(); //O(n²)
				break;
			case 5:
				phoneBook.printEventDetails(); //O(n²)
				break;
			case 6:
				phoneBook.printContactsByFirstName(); //O(n²)
				break;
			case 7:
				phoneBook.printAllEventsAlphabetically(); //O(n)
				break;
			case 8:
				System.out.println("Goodbye!"); //1
				break;
			}
		} while (choice != 8);

	}
}
