public class Array <T> implements Iterable <T> {
    private T[] array;
    private int length = 0;
    private int capacity = 0;
    private static final int DEFAULT_CAPACITY = 16;

    // default constructor setting the capacity
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity must be positive.");
        this.capacity = capacity;
        array = (T[]) new Object[capacity];
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {        
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        return array[index];
    }

    public void set(int index, T element) {
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        array[index] = element;
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
        length = 0;
    }

    public void add(T element) {
        // resizing neccessary
        if (length + 1 >= capacity) {
            if (capacity == 0) capacity = 1;
            else capacity *= 2;

            T[] tempArray = (T[]) new Object[capacity];
            // copy all the old values
            for (int i = 0; i < length; i++) {
                tempArray[i] = array[i];
            }
            array = tempArray;
        }
        array[length++] = element;
    }

    // this does never shrink the array
    // TODO: maybe half the size if it's only 1/3 full
    public T removeAt(int index) {
        if (index >= length || index < 0) throw new IndexOutOfBoundsException();
        T data = array[index];
        T[] tempArray = (T[]) new Object[capacity];
        for (int i = 0, j = 0; i < length; i++, j++) {
            if (i == index) j--;
            else tempArray[j] = array[i]
        }

        array = tempArray;
        length--;
        return data;
    }

    public boolean remove(Object obj) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(obj)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(Object obj) {
        for (int i = 0; i < length; i++) {
            if (array[i].equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator <T> iterator() {
        return new java.util.Iterator <T> () {
            int index = 0; public boolean hasNext() { return index < length; }
            public T next() { return array[index++]; }
        };
    }

    @Override
    public String toString() {
        if (length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length - 1; i++) {
            sb.append(array[i] + ", ");
        }
        sb.append(array[length - 1] + "]");
        return sb.toString();
    }

}