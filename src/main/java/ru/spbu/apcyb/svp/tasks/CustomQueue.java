package ru.spbu.apcyb.svp.tasks;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import javax.annotation.Nonnull;

/**
 * Abc.
 */
public class CustomQueue implements Queue<Object> {
  private final DoublyLinkedList queue;

  public CustomQueue() {
    queue = new DoublyLinkedList();
  }

  public CustomQueue(Collection<?> newObjectsCollection) {
    queue = new DoublyLinkedList(newObjectsCollection);
  }

  @Override
  public int size() {
    return queue.size();
  }

  @Override
  public boolean isEmpty() {
    return queue.isEmpty();
  }

  @Override
  public boolean add(Object newObject) {
    return queue.add(newObject);
  }

  @Override
  public boolean offer(Object newObject) {

    queue.add(newObject);
    return true;
  }

  @Override
  public boolean addAll(@Nonnull Collection newObjectsCollection) {
    return queue.addAll(newObjectsCollection);
  }

  @Override
  public Object remove() {
    return queue.remove(0);
  }

  @Override
  public boolean remove(Object o) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'remove' is undefined");
  }

  @Override
  public Object poll() {
    if (queue.isEmpty()) {
      return null;
    }
    return queue.remove(0);
  }

  @Override
  public boolean removeAll(@Nonnull Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'removeAll' is undefined");
  }

  @Override
  public boolean contains(Object newObject) {
    return queue.contains(newObject);
  }

  @Override
  public boolean containsAll(@Nonnull Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'containsAll' is undefined");
  }

  @Override
  public Object element() {

    Object first = queue.get(0);
    if (first == null) {
      throw new NullPointerException("Queue is empty");
    }
    return first;
  }

  @Override
  public Object peek() {
    if (queue.isEmpty()) {
      return null;
    }
    return queue.get(0);
  }

  @Override
  @Nonnull
  public Object[] toArray() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'toArray' is undefined");
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
  public void clear() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'clear' is undefined");
  }

  @Override
  public boolean retainAll(@Nonnull Collection c) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Method 'retainAll' is undefined");
  }
}
