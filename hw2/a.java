public class SimpleLinkedListExample {
    public static void main(String[] args) {
        CustomLinkedList<String> customList = new CustomLinkedList<>();

        customList.add("One");
        customList.add("Two");
        customList.add("Three");
        customList.add("Four");
        customList.add("Five");
        System.out.println("CustomLinkedList elements: " + customList);
        System.err.println("1size of linkedlist"+ customList.sizeofLinkedlist());
        customList.remove("Two");
        System.out.println("CustomLinkedList elements: " + customList);
        System.err.println("2size of linkedlist"+ customList.sizeofLinkedlist());
        System.err.println("3. element is "+ customList.get_NthElement(2));

    }
}

class CustomLinkedList<E> {
    private Node<E> head;

    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }
    public void remove(E data) {
        // Check if the element to be removed is at the head
        if (head != null && head.data.equals(data)) {
            head = head.next;  // Move the head to the next node
            return;
        }
    
        // Traverse the linked list to find the node with the specified data
        Node<E> temp = head;
        while (temp != null && temp.next != null && !temp.next.data.equals(data)) {
            temp = temp.next;
        }
    
        // Check if the element was found
        if (temp != null && temp.next != null) {
            temp.next = temp.next.next;  // Remove the node
        }
    }
    public int sizeofLinkedlist(){
        Node<E> temp = head;
        int counter=1;
        while (temp != null && temp.next != null && !temp.data.equals(null)) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }
    public E get_NthElement(int index){
        Node<E> temp = head;
        int counter=1;
        while (temp != null && temp.next != null && !temp.data.equals(null) && counter<index) {
            temp = temp.next;
            counter++;
        }
        return temp.data;
    }
    //size/Length of the Linked List/ Get the Nth Element:  implement these
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            result.append(current.data).append(" ");
            current = current.next;
        }
        return result.toString();
    }

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
}
