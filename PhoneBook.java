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


		while (true) {
			try {
				System.out.print("Enter your choice: "); 
				int choice = Integer.parseInt(scanner.nextLine());
				System.out.println();
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
		Contact contact = new Contact(); //1

		System.out.print("Enter the contact's name: "); //1
		contact.setName(scanner.nextLine()); //1
		System.out.print("Enter the contact's phone number: "); //1
		contact.setPhoneNumber(scanner.nextLine(), scanner); //1
		System.out.print("Enter the contact's email address: "); //1
		contact.setEmailAddress(scanner.nextLine(), scanner); //1
		System.out.print("Enter the contact's address: "); //1
		contact.setAddress(scanner.nextLine()); //1 
		System.out.print("Enter the contact's birth date (dd/MM/yyyy): "); //1
		contact.setBirthDate(scanner.nextLine(), scanner); //1
		System.out.print("Enter any notes about the contact: "); //1
		contact.setNotes(scanner.nextLine()); //1


		// check if a contact with name and phone exists.
		ContactSearchCriteria searchCriteria = new ContactSearchCriteria(contact.getName(), 1);  // 1 corresponds to NAME
		ContactSearchCriteria phoneSearchCriteria = new ContactSearchCriteria(contact.getPhoneNumber(), 2);  // 2 corresponds to PHONE_NUMBER

		if (this.contacts.search(searchCriteria).isEmpty() && this.contacts.search(phoneSearchCriteria).isEmpty()) { //1
			this.contacts.add(contact); //1

			System.out.println("Contact added successfully!");//1
		} else {
			System.out.println("Contact with name or phone number already exists."); //1
		}
		System.out.println(); //1
	}

    private void searchForContact() {
        System.out.println("Enter search criteria."); //1
        System.out.println("1. Name"); //1
        System.out.println("2. Phone Number"); //1
        System.out.println("3. Email Address"); //1 
        System.out.println("4. Address"); //1
        System.out.println("5. Birthday"); //1
        int choice; 

        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 5) {
                    System.err.println("Please enter a number between 1 and 5");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a number between 1 and 5");
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
		System.out.println();
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
		System.out.println();
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
		System.out.println();
	}

	private void printContactsByFirstName() {
		System.out.print("Enter the first name: "); //1

		String firstName = scanner.nextLine(); //1

		ContactSearchCriteria criteria = new ContactSearchCriteria(firstName, 6); //1  // 6 corresponds to FIRST_NAME
		LinkedListADT<Contact> results = this.contacts.search(criteria); //1

		if (results.isEmpty()) { //1
			System.out.println("No contact found"); //1
		} else {
			System.out.println("Contacts found!"); //1
			System.out.println(results); //1
		}
		System.out.println(); //1
	}

	private void printAllEventsAlphabetically() {  // it is already sorted alphabetically in add method in linked list
		if(this.events.isEmpty()) //1
			System.out.println("There are no events scheduled"); //1
		else
			System.out.println(this.events); //1
	}

	public static void main(String[] args) {
		PhoneBook phoneBook = new PhoneBook(); //1
		System.out.println("Welcome to the Linked Tree Phonebook!"); //1
		int choice;
		do {
			choice = phoneBook.mainMenu(); //1

			switch (choice) {
			case 1: //n
				phoneBook.addContact(); //n
				break; //n
			case 2: //n
				phoneBook.searchForContact(); //n
				break; //n
			case 3: //n
				phoneBook.deleteContact(); //n
				break; //n
			case 4: //n
				phoneBook.scheduleEvent(); //n
				break; //n
			case 5: //n
				phoneBook.printEventDetails(); //n
				break; //n
			case 6: //n
				phoneBook.printContactsByFirstName(); //n
				break; //n
			case 7: //n
				phoneBook.printAllEventsAlphabetically(); //n
				break; //n
			case 8: //n
				System.out.println("Goodbye!"); //n
				break; //n
			}
		} while (choice != 8); //n

	}
}
