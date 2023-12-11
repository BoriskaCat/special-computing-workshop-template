package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.Nonnull;

/**
 * Custom doubly linked list with interface implementation.
 */

public class DoublyLinkedList implements List<Object> {

  private int size = 0;
  private Node head;
  private Node tail;

  private static class Node {
    Object value;
    Node prev;
    Node next;

    Node(Object value, Node prev, Node next) {
      this.value = value;
      this.prev = prev;
      this.next = next;
    }
  }

  public DoublyLinkedList() {}

  /**
   * Class constructor.
   */
  public DoublyLinkedList(Collection<?> newObjectsCollection) {
    this();

    this.addAll(newObjectsCollection);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return (head == null);
  }

  @Override
  public boolean add(Object newObject) {

    if (head == null) {
      head = new Node(newObject, null, null);
      tail = head;

    } else {

      Node newNode = new Node(newObject, tail, null);
      tail.next = newNode;
      tail = newNode;
    }

    size++;
    return true;
  }

  @Override
  public void add(int index, Object newObject) {

    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (index == 0) {
      Node newNode = new Node(newObject, null, head);
      head.prev = newNode;
      head = newNode;
    } else {
      Node offsetNode = head;

      for (int i = 0; i < index; i++) {
        offsetNode = offsetNode.next;
      }

      Node newNode = new Node(newObject, offsetNode.prev, offsetNode);
      offsetNode.prev.next = newNode;
      offsetNode.prev = newNode;
    }

    size++;
  }

  @Override
  public boolean addAll(Collection newObjectsCollection) {
    for (Object newObject : newObjectsCollection) {
      this.add(newObject);
    }
    return true;
  }

  @Override
  public boolean addAll(int index, @Nonnull Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'addAll' is undefined");
  }

  @Override
  public Object remove(int index) {

    if (this.isEmpty()) {
      throw new NullPointerException("The collection is empty");
    }

    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    size--;

    if (size == 0) {
      head = null;
      tail = null;

    } else if (index == size) {
      tail.prev.next = null;
      tail = tail.prev;

    } else if (index == 0) {
      head.next.prev = null;
      head = head.next;

    } else {
      Node deletedNode = head;
      for (int i = 0; i < index; i++) {
        deletedNode = deletedNode.next;
      }
      deletedNode.prev.next = deletedNode.next;
      deletedNode.next.prev = deletedNode.prev;
    }

    return true;
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'remove' is undefined");
  }

  @Override
  public boolean removeAll(@Nonnull Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'removeAll' is undefined");
  }

  @Override
  public boolean contains(Object node) {
    int nodeIndex = this.indexOf(node);
    return nodeIndex != -1;
  }

  @Override
  public boolean containsAll(@Nonnull Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'containsAll' is undefined");
  }

  @Override
  public Object get(int index) {

    if (this.isEmpty()) {
      return null;
    }

    if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }

    Node requiredNode = head;
    for (int i = 0; i < index; i++) {
      requiredNode = requiredNode.next;
    }
    return requiredNode.value;
  }

  @Override
  public int indexOf(Object o) {

    Node searchedNode = head;
    for (int i = 0; i < size; i++) {
      if (searchedNode.value.equals(o)) {
        return i;
      }
      searchedNode = searchedNode.next;
    }
    return -1;
  }

  @Override
  public int lastIndexOf(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'lastIndexOf' is undefined");
  }

  @Override
  @Nonnull
  public Object[] toArray() {
    Object[] result = new Object[size];
    Node convertibleNode = head;
    for (int i = 0; i < size; i++) {
      result[i] = convertibleNode.value;
      convertibleNode = convertibleNode.next;
    }

    return result;
  }

  @Override
  @Nonnull
  public <T> T[] toArray(@Nonnull T[] a) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'toArray' is undefined");
  }

  @Override
  @Nonnull
  public Iterator<Object> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'iterator' is undefined");
  }

  @Override
  @Nonnull
  public ListIterator<Object> listIterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'listIterator' is undefined");
  }

  @Override
  @Nonnull
  public ListIterator<Object> listIterator(int index) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'listIterator' is undefined");
  }

  @Override
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'clear' is undefined");
  }

  @Override
  public Object set(int index, Object element) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'set' is undefined");
  }

  @Override
  @Nonnull
  public List<Object> subList(int fromIndex, int toIndex) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'subList' is undefined");
  }

  @Override
  public boolean retainAll(@Nonnull Collection<?> c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'retainAll' is undefined");
  }
}