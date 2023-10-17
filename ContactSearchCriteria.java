/*************Example***************
CLASS: ContactSearchCriteria..java
CSC212 Data structures - Project phase I
Fall 2023
EDIT DATE:
7-9 Oct
TEAM:
Code Crafters 
AUTHORS: Mazen Alkhodairi, Abdulaziz Alali, Abdulaziz Altekhaifi
authors-names Alkhodairi 443102007 , Alali (443100240), Altekhaifi 443100756
***********************************/
public class ContactSearchCriteria implements LinkedListADT.SearchCriteria<Contact> {
    private String criteria;
    private int type;

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
                  case 6:  // First Name 
                return criteria.equalsIgnoreCase(contact.getFirstName());
            default://1
                return false;//1
        }
        //12
    }

    
}
