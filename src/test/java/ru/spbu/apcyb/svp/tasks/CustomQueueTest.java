package ru.spbu.apcyb.svp.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for custom queue with interface implementation.
 */
class CustomQueueTest {

  @Test
  void sizeTest() {
    CustomQueue test = new CustomQueue(List.of(1, 2, 3));
    assertEquals(3, test.size());
  }

  @Test
  void isEmptyTest_empty() {
    CustomQueue test = new CustomQueue();
    assertTrue(test.isEmpty());
  }

  @Test
  void isEmptyTest_notEmpty() {
    CustomQueue test = new CustomQueue(List.of(1));
    assertFalse(test.isEmpty());
  }

  @Test
  void addTest() {
    CustomQueue test = new CustomQueue(List.of(1, 2, 3));
    assertTrue(test.add(5));
  }

  @Test
  void offerTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    assertTrue(test.offer(5));
  }

  @Test
  void addAllTest() {
    CustomQueue test = new CustomQueue(List.of(1, 2, 3));
    assertTrue(test.addAll(List.of(3, 2)));
  }

  @Test
  void removeTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    test.remove();
    Exception e = assertThrows(NullPointerException.class, test::remove);
    assertEquals("The collection is empty", e.getMessage());
  }

  @Test
  void removeTest_object() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, () -> test.remove(1));
  }

  @Test
  void removeAllTest() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, () -> test.removeAll(List.of(1, 2)));
  }

  @Test
  void pollTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    test.poll();
    assertNull(test.poll());
  }

  @Test
  void containsTest() {
    CustomQueue test = new CustomQueue(List.of(1, 2, 3));
    assertTrue(test.contains(2));
    assertFalse(test.contains(10));
  }

  @Test
  void containsAllTest() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, test::iterator);
  }

  @Test
  void elementTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    assertEquals(1, test.element());
  }

  @Test
  void elementTest_emptyQueue() {
    CustomQueue test = new CustomQueue();
    Exception e = assertThrows(NullPointerException.class, test::element);
    assertEquals("Queue is empty", e.getMessage());
  }

  @Test
  void peekTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    assertEquals(1, test.element());
  }

  @Test
  void peekTest_emptyQueue() {
    CustomQueue test = new CustomQueue();
    assertNull(test.peek());
  }

  @Test
  void toArrayTest() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, test::toArray);
  }

  @Test
  void iteratorTest() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, test::iterator);
  }

  @Test
  void clearTest() {
    CustomQueue test = new CustomQueue(List.of(1));
    assertThrows(UnsupportedOperationException.class, test::clear);
  }

  @Test
  void retainAllTest() {
    CustomQueue test = new CustomQueue();
    assertThrows(UnsupportedOperationException.class, () -> test.retainAll(List.of(1, 2)));
  }
}
