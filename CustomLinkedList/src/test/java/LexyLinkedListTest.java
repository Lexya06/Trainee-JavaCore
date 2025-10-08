import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LexyLinkedListTest {
    private LexyLinkedList<String> lexyLinkedList;
    @BeforeEach
    public void setUp() {
        lexyLinkedList = new LexyLinkedList<>();
    }

    @Test
    public void testAddFirst(){
        lexyLinkedList.addFirst("Hello");
        assertEquals(1, lexyLinkedList.size());
        assertEquals("Hello", lexyLinkedList.getFirst());
    }

    @Test
    public void testAddLast(){
        lexyLinkedList.addFirst("LexyLinkedList");
        lexyLinkedList.addLast("Complete");
        assertEquals(2, lexyLinkedList.size());
        assertEquals("Complete", lexyLinkedList.getLast());
    }

    @Test
    public void testAdd(){
        lexyLinkedList.addFirst("The sun");
        lexyLinkedList.addLast("gone");
        assertFalse(lexyLinkedList.add(-1, "cool"));
        assertFalse(lexyLinkedList.add(4, "hey"));
        assertTrue(lexyLinkedList.add(1, "has nearly"));
        assertTrue(lexyLinkedList.add(3, "the lights are turning on"));
        assertEquals(4, lexyLinkedList.size());
        assertEquals("has nearly", lexyLinkedList.get(1));
        assertEquals("the lights are turning on", lexyLinkedList.get(3));
    }

    @Test
    public void testRemoveFirst(){
        lexyLinkedList.addFirst("Computer");
        lexyLinkedList.addFirst("Science");

        assertEquals("Science", lexyLinkedList.removeFirst());
        assertEquals(1, lexyLinkedList.size());
        assertEquals("Computer", lexyLinkedList.getFirst());
    }

    @Test
    public void testRemoveLast(){
        lexyLinkedList.addLast("The cat");
        lexyLinkedList.addLast("eats");
        lexyLinkedList.addLast("fish");
        assertEquals("fish", lexyLinkedList.removeLast());
        assertEquals(2, lexyLinkedList.size());
        assertEquals("eats", lexyLinkedList.getLast());

    }

    @Test
    public void testRemove(){
        String[] arrString = {"На","веселых", "утят", "быть","похожими","хотят"};
        for (int i = 0; i < arrString.length; i++) {
            lexyLinkedList.add(i, arrString[i]);
        }
        int ind = 1;
        assertEquals("веселых",lexyLinkedList.remove(ind));
        assertEquals(arrString.length - 1, lexyLinkedList.size());
        assertEquals("утят", lexyLinkedList.get(ind));
    }

    @Test
    public void getFirstAndLastEmpty(){
        assertThrows(NoSuchElementException.class, lexyLinkedList::getFirst);
        assertThrows(NoSuchElementException.class, lexyLinkedList::getLast);
    }

    @Test
    public void getIncorrectElements(){
        lexyLinkedList.addFirst("Bye");
        assertThrows(IndexOutOfBoundsException.class,() -> lexyLinkedList.get(-1));
        assertThrows(IndexOutOfBoundsException.class,() -> lexyLinkedList.get(1));
    }



}
