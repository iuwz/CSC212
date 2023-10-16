/**
 * This class is a generic linked list implementation that stores it's elements in a sorted order.
 * <p>
 * It has methods for adding, searching and removing elements.
 *
 * @param <T>
 */
public class LinkedListADT<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    public LinkedListADT() {
        this.head = null;
        this.size = 0;
    }

    /**
     * This method adds an element to the linked list
     *
     * @param data The element to be added
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null && current.getData().compareTo(data) < 0) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            node.setNext(head);//if there is only head in the list the new data will be added before the head
            head = node;
        } else {
            previous.setNext(node);// will add the data before the last
            node.setNext(current);
        }
        size++;
    }

    /**
     * This method removes an element from the linked list
     *
     * @param data The element to be removed
     */
    public void remove(T data) {
        if (head == null) {
            return;
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (current != null && current.getData().compareTo(data) != 0) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            head = head.getNext();
        } else {
            previous.setNext(current.getNext());
        }
        size--;
    }

    /**
     * This method returns the size of the linked list
     *
     * @return The size of the linked list
     */
    public int size() {
        return size;
    }

    /**
     * This method searches for an element in the linked list
     *
     * @param criteria The criteria to be used for searching
     */
    public LinkedListADT<T> search(SearchCriteria<T> criteria) {
        LinkedListADT<T> results = new LinkedListADT<>();

        Node<T> current = head;
        while (current != null) {
            if (criteria.matches(current.getData())) {
                results.add(current.getData());
            }
            current = current.getNext();
        }

        return results;
    }


    public Node<T> getHead() {
        return head;
    }

    public boolean isEmpty(){
        return head == null;
    }

    @Override
    public String toString() {
        // Create a StringBuilder, which is an efficient way to concatenate strings.
        StringBuilder builder = new StringBuilder();
        // Initialize a pointer to the head of the linked list.
        Node<T> current = head;
        // Traverse the linked list until we reach the end (i.e., current becomes null).
        while (current != null) {
            // Append the data of the current node to the StringBuilder.
            builder.append(current.getData());
            // Append a newline to separate each piece of data.
            builder.append("\n");
            // Move to the next node in the list.
            current = current.getNext();
        }
        // Convert the StringBuilder to a string and return.
        return builder.toString();
    }

    public interface SearchCriteria<T> {
        boolean matches(T data);
    }
}
