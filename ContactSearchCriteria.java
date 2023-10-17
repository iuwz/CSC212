/**
 * This class implements the SearchCriteria interface and is used to search for a contact
 * This class allows us to search for a contact by name, phone number, email address, address or birth date
 */
public class ContactSearchCriteria implements LinkedListADT.SearchCriteria<Contact> {
    private String criteria;
    private int type;

    public ContactSearchCriteria(String criteria, int type) {
       
        this.criteria = criteria;
        this.type = type;
    }

   
    public boolean matches(Contact contact) {
        switch (type) {
            case 1:  // Name
                return criteria.equalsIgnoreCase(contact.getName());
            case 2:  // Phone Number
                return criteria.equalsIgnoreCase(contact.getPhoneNumber());
            case 3:  // Email Address
                return criteria.equalsIgnoreCase(contact.getEmailAddress());
            case 4:  // Address
                return criteria.equalsIgnoreCase(contact.getAddress());
            case 5:  // Birth Date
                return criteria.equalsIgnoreCase(contact.getBirthDate());
            default:
                return false;
        }
    }

    
}