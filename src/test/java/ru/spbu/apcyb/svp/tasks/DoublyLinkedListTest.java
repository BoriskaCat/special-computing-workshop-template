package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for custom doubly linked list with interface implementation.
 */
class DoublyLinkedListTest {

  @Test
  void isEmptyTest() {
    DoublyLinkedList test1 = new DoublyLinkedList();
    test1.add("123");
    DoublyLinkedList test2 = new DoublyLinkedList();
    assertFalse(test1.isEmpty());
    assertTrue(test2.isEmpty());
  }

  @Test
  void addTest_single() {
    DoublyLinkedList test = new DoublyLinkedList();
    assertTrue(test.add(1));
    assertEquals(1, test.size());
  }

  @Test
  void addTest_single_inTheMiddle() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 10, 20));
    test.add(2, 3);
    DoublyLinkedList expected = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), test.get(i));
    }
  }

  @Test
  void addAllTest() {
    DoublyLinkedList test = new DoublyLinkedList();
    assertTrue(test.addAll(List.of(1, 2)));
    assertEquals(2, test.size());
  }

  @Test
  void addTest_insteadOfHead() {
    DoublyLinkedList test = new DoublyLinkedList(List.of("luonto", "sarjat"));
    test.add(0, "areena");
    List<String> expected = List.of("areena", "luonto", "sarjat");
    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), test.get(i));
    }
  }

  @Test
  void addTest_sizeOfList() {
    DoublyLinkedList test = new DoublyLinkedList(List.of("luonto", "sarjat"));
    assertThrows(IndexOutOfBoundsException.class, () -> test.add(2, "sarjat"));
  }

  @Test
  void removeTest_middleOfList() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    test.remove(2);

    DoublyLinkedList expected = new DoublyLinkedList(List.of(1, 2, 10, 20));
    for (int i = 0; i < test.size(); i++) {
      assertEquals(test.get(i), expected.get(i));
    }
  }

  @Test
  void removeTest_tailOfList() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    test.remove(4);

    DoublyLinkedList expected = new DoublyLinkedList(List.of(1, 2, 3, 10));
    for (int i = 0; i < test.size(); i++) {
      assertEquals(test.get(i), expected.get(i));
    }
  }

  @Test
  void removeTest_headOfList() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    test.remove(0);

    DoublyLinkedList expected = new DoublyLinkedList(List.of(2, 3, 10, 20));
    for (int i = 0; i < test.size(); i++) {
      assertEquals(test.get(i), expected.get(i));
    }
  }

  @Test
  void removeTest_singleList() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1));
    test.remove(0);
    assertEquals(0, test.size());
  }

  @Test
  void removeTest_emptyList() {
    DoublyLinkedList test = new DoublyLinkedList();
    Exception e = assertThrows(NullPointerException.class, () -> test.remove(0));
    assertEquals("The collection is empty", e.getMessage());
  }

  @Test
  void removeTest_object() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena"));
    assertThrows(UnsupportedOperationException.class, () -> list.remove("areena"));
  }

  @Test
  void removeAllTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena", "luonto", "sarjat"));
    assertThrows(UnsupportedOperationException.class, () -> list.removeAll(List.of("areena")));
  }

  @Test
  void containsTest() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    assertTrue(test.contains(3));
  }

  @Test
  void getTest_empty() {
    DoublyLinkedList test = new DoublyLinkedList();
    Object element = test.get(0);
    assertNull(element);
  }

  @Test
  void getTest_notEmpty() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2));
    Object result = test.get(1);
    assertEquals(2, result);
  }

  @Test
  void indexOfTest_correctIndex() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    int index = test.indexOf(3);
    assertEquals(2, index);
  }

  @Test
  void indexOfTest_outboundIndex() {
    DoublyLinkedList test = new DoublyLinkedList(List.of(1, 2, 3, 10, 20));
    int index = test.indexOf(30);
    assertEquals(-1, index);
  }

  @Test
  void lastIndexOfTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena"));
    assertThrows(UnsupportedOperationException.class, () -> list.lastIndexOf("areena"));
  }

  @Test
  void toArrayTest() {
    DoublyLinkedList test = new DoublyLinkedList(List.of("areena", "luonto", "sarjat"));
    Object[] expected = new Object[]{"areena", "luonto", "sarjat"};
    Object[] listAsArray = test.toArray();
    assertEquals(3, listAsArray.length);
    for (int i = 0; i < 3; i++) {
      assertEquals(expected[i], listAsArray[i]);
    }
  }

  @Test
  void iteratorTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena"));
    assertThrows(UnsupportedOperationException.class, list::iterator);
  }

  @Test
  void listIteratorTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena"));
    assertThrows(UnsupportedOperationException.class, list::listIterator);
  }

  @Test
  void listIteratorIndexTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena"));
    assertThrows(UnsupportedOperationException.class, () -> list.listIterator(0));
  }

  @Test
  void clearTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of(1));
    assertThrows(UnsupportedOperationException.class, list::clear);
  }

  @Test
  void setTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of(1));
    assertThrows(UnsupportedOperationException.class, () -> list.set(0, 5));
  }

  @Test
  void subListTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena", "luonto", "sarjat"));
    assertThrows(UnsupportedOperationException.class, () -> list.subList(0, 1));
  }

  @Test
  void retainAllTest() {
    DoublyLinkedList list = new DoublyLinkedList(List.of("areena", "luonto", "sarjat"));
    assertThrows(UnsupportedOperationException.class, () -> list.retainAll(List.of(0, 1)));
  }
}
