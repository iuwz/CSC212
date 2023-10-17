/**
 * This class implements the SearchCriteria interface and is used to search for a contact
 * This class allows us to search for a contact by name, phone number, email address, address or birth date
 */
public class ContactSearchCriteria implements LinkedListADT.SearchCriteria<Contact> {
    private String criteria;//1
    private int type;//1

    public ContactSearchCriteria(String criteria, int type) {
       
        this.criteria = criteria;//1
        this.type = type;//1
    }

    public boolean matches(Contact contact) {
        switch (type) {
            case 1: //1 // Name
                return criteria.equalsIgnoreCase(contact.getName());//1
            case 2://1  // Phone Number
                return criteria.equalsIgnoreCase(contact.getPhoneNumber());//1
            case 3://1  // Email Address
                return criteria.equalsIgnoreCase(contact.getEmailAddress());//1
            case 4://1  // Address
                return criteria.equalsIgnoreCase(contact.getAddress());//1
            case 5://1  // Birth Date
                return criteria.equalsIgnoreCase(contact.getBirthDate());//1
            default://1
                return false;//1
        }
    }

    
}
