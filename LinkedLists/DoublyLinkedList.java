import java.io.InputStream;

public class DoublyLinkedList<T> implements Iterable <T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    // internal node class
    private class Node<T> {
        T data;
        Node<T> previous, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.previous = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.previous = current.next = null;
            current.data = null;
            current = next;
        }
        head = tail = current = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T element) {
        addLast(element);
    }

    public void addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        }
        else {
            head.previous = new Node<T>(element, null, head);
            head = head.previous;
        }
        size++;
    }

    public void addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        }
        else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
    }

    public T peekFirst() {
        if (isEmpty()) return null;
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) return null;
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) throw new RuntimeException();
        T data = head.data;
        head = head.next;
        size--;

        if (isEmpty()) tail = null;
        else head.previous = null;

        return data;
    }

    public T removeLast() {
        if (isEmpty()) throw new RuntimeException();
        T data = tail.data;
        tail.previous = tail;
        size--;

        if (isEmpty()) head = null;
        else tail.next = null;

        return data;
    }

    private T remove(Node<T> node) {
        if (node.previous == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.next.previous = node.previous;
        node.previous.next = node.next;
        size--;

        T data = node.data;

        // is this memory cleanup actually neccessary?
        node.data = null;
        node = node.previous = node.next = null;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IllegalArgumentException();

        Node <T> current;

        // search from the front of the list
        if (index < size/2) {
            current = head;
            for (int i = 0; i != index; i++) {
                current = current.next;
            }
        }
        else {
            current = tail;
            for (int i = size - 1; i != index; i--) {
                current = current.previous;
            }
        }

        return remove(current);
    }

    public boolean remove(Object obj) {
        Node<T> current = head;
        while (current.next != null) {
            if (current.data.equals(obj)) {
                remove(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> current = head;

        while (current.next != null) {
            if (current.data.equals(obj)) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }
            @Override
            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data.toString() + ", ");
            current = current.next;
        }
        return sb.append("]").toString();
    }


}