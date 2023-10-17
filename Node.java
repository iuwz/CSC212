public class Node<T> {
    private T data; 
    private Node<T> next; 

    public Node(T data) {
        this.data = data; //1
        this.next = null; //1
    }

    public T getData() {
        return data; //1
    }

    public Node<T> getNext() {
        return next; //1
    }

    public void setData(T data) {
        this.data = data; //1
    }

    public void setNext(Node<T> next) {
        this.next = next; //1
    } 
}
