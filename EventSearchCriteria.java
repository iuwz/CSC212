public class EventSearchCriteria implements LinkedListADT.SearchCriteria<Event> {
	private String criteria;
    private int type;

    
    public EventSearchCriteria(String criteria, int type) {
    	this.criteria = criteria;
        this.type = type;
    }

    @Override
    public boolean matches(Event data) {
        switch (type) {
            case 1://by contact name
                return data.getContact().getName().equalsIgnoreCase(criteria);
            case 2://by event title
                return data.getTitle().equalsIgnoreCase(criteria);
        }
        return false;
    }
}
