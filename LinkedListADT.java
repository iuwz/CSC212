/**
 * This class is a generic linked list implementation that stores it's elements in a sorted order.
 * <p>
 * It has methods for adding, searching and removing elements.
 *
 * @param <T>
 */
public class LinkedListADT<T extends Comparable<T>> {
    private Node<T> head;//1
    private int size;//1

    public LinkedListADT() {
        this.head = null;//1
        this.size = 0;//1
    }

    /**
     * This method adds an element to the linked list
     *
     * @param data The element to be added
     */
    public void add(T data) {
        Node<T> node = new Node<>(data);//1
        if (head == null) {//1
            head = node;//1
            size++;//1
            return;//1
        }
        Node<T> current = head;//1
        Node<T> previous = null;//1
        while (current != null && current.getData().compareTo(data) < 0) {//n
            previous = current;//n
            current = current.getNext();//n
        }
        if (previous == null) {//1
            node.setNext(head);//1 //if there is only head in the list the new data will be added before the head
            head = node;//1
        } else {
            previous.setNext(node);//1 // will add the data before the last
            node.setNext(current);//1
        }
        size++;//1
    }

    /**
     * This method removes an element from the linked list
     *
     * @param data The element to be removed
     */
    public void remove(T data) {
        if (head == null) {//1
            return;//1
        }
        Node<T> current = head;//1
        Node<T> previous = null;//1
        while (current != null && current.getData().compareTo(data) != 0) {//n
            previous = current;//n
            current = current.getNext();//n
        }
        if (previous == null) {//1
            head = head.getNext();//1
        } else {
            previous.setNext(current.getNext());//1
        }
        size--;//1
    }

    /**
     * This method returns the size of the linked list
     *
     * @return The size of the linked list
     */
    public int size() {
        return size;//1
    }

    /**
     * This method searches for an element in the linked list
     *
     * @param criteria The criteria to be used for searching
     */
    public LinkedListADT<T> search(SearchCriteria<T> criteria) {
        LinkedListADT<T> results = new LinkedListADT<>();//1

        Node<T> current = head;//1
        while (current != null) {//n
            if (criteria.matches(current.getData())) {//n
                results.add(current.getData());//n
            }
            current = current.getNext();//n
        }

        return results;//1
    }


    public Node<T> getHead() {
        return head;//1
    }

    public boolean isEmpty(){
        return head == null;//1
    }

    @Override
    public String toString() {
        // Create a StringBuilder, which is an efficient way to concatenate strings.
        StringBuilder builder = new StringBuilder();//n
        // Initialize a pointer to the head of the linked list.
        Node<T> current = head;//n
        // Traverse the linked list until we reach the end (i.e., current becomes null).
        while (current != null) {//n
            // Append the data of the current node to the StringBuilder.
            builder.append(current.getData());//n
            // Append a newline to separate each piece of data.
            builder.append("\n");//n
            // Move to the next node in the list.
            current = current.getNext();//n
        }
        // Convert the StringBuilder to a string and return.
        return builder.toString();//1
    }

    public interface SearchCriteria<T> {
        boolean matches(T data);//1
    }
}
