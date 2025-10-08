
import java.util.NoSuchElementException;

public class LexyLinkedList<T> {
    private class Node {
        T element;
        Node next;
        Node prev;

        public Node(T e, Node n, Node p) {
            element = e;
            next = n;
            prev = p;
        }

        public Node() {
            this(null, null, null);
        }
    }


    // pointer to list start
    private Node head;

    // pointer to list end
    private Node tail;

    // current count of list elements
    private int size;

    public LexyLinkedList() {
        head = new Node();
        tail = head;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T e) {
        if (size == 0) {
            head.element = e;
            size++;
            return;
        }

        head.prev = new Node(e, head, null);
        head = head.prev;
        size++;
    }

    public void addLast(T e) {
        if (size == 0) {
            tail.element = e;
            size++;
            return;
        }
        tail.next = new Node(e, null, tail);
        tail = tail.next;
        size++;
    }

    private Node findNode(int ind) {
        Node curr = head;
        int diff = size - ind - 1;
        // optimize iterations
        if (diff < ind) {
            curr = tail;
            for (int i = 0; i < diff; i++) {
                curr = curr.prev;
            }
        } else {
            for (int i = 0; i < ind; i++) {
                curr = curr.next;
            }
        }
        return curr;
    }

    public T get(int ind) {
        if (ind < 0 || ind >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (ind == 0)
            return getFirst();
        if (ind == size - 1)
            return getLast();
        Node curr = findNode(ind);
        return curr.element;

    }

    public T getFirst() {
        if (size == 0)
            throw new NoSuchElementException();
        return head.element;
    }

    public T getLast() {
        if (size == 0)
            throw new NoSuchElementException();
        return tail.element;
    }

    public boolean add(int ind, T e) {
        if (ind < 0 || ind > size) {
            return false;
        }
        if (ind == size) {
            addLast(e);
            return true;
        }
        if (ind == 0) {
            addFirst(e);
            return true;
        }

        Node curr = findNode(ind);
        curr.prev.next = new Node(e, curr, curr.prev);
        curr.prev = curr.prev.next;
        size++;
        return true;

    }

    public T removeFirst() {
        T e = getFirst();
        head = head.next;
        head.prev = null;
        size--;
        return e;
    }

    public T removeLast() {
        T e = getLast();
        tail = tail.prev;
        tail.next = null;
        size--;
        return e;
    }

    public T remove(int ind) {
        if (ind == size - 1) {
            return removeLast();
        }
        if (ind == 0) {
            return removeFirst();
        }
        T e = get(ind);
        Node curr = findNode(ind);
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
        return e;

    }






}
