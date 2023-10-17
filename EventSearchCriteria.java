public class EventSearchCriteria implements LinkedListADT.SearchCriteria<Event> {
	private String criteria;
    private int type;

    
    public EventSearchCriteria(String criteria, int type) {
    	this.criteria = criteria;//1
        this.type = type;//1
    }

    @Override
    public boolean matches(Event data) {
        switch (type) {
            case 1://1 //by contact name
                return data.getContact().getName().equalsIgnoreCase(criteria);//1
            case 2://1 //by event title
                return data.getTitle().equalsIgnoreCase(criteria);//1
        }
        return false;//1
    }
}
