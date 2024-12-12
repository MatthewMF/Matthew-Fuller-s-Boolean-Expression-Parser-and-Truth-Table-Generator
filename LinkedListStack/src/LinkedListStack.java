class Node {
    public char data; // data inside the object
    public Node next; // pointer will point to the next object in our list

    Node(char val) {
        this.data = val;
        this.next = null;
    }
}
public class LinkedListStack {
    private Node top;

    public LinkedListStack() {
        this.top = null;

    }
    public boolean isEmpty(){
        return (top == null);
    }
    public void push(char val){
        if(isEmpty()){
            Node temp = new Node(val);
            top = temp;
            // top = new Node(val);
        }
        else{
            Node temp = new Node(val);
            temp.next = top;
            top = temp;

        }
    }
    public char pop() {
        if (!isEmpty()) {
            char popped = top.data;
            top = top.next;
            return popped;
        } else {
            System.out.println("Sorry: There are no elements to delete in the Stack");
        }
        return '\0';
    }
    public char peek(){
        if(!isEmpty()){
            return top.data;
        }
        else{
            System.out.println("Sorry: There are no elements to print");
            return '\0' ;
        }
    }

}
