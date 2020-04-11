// A min priority queue implementation based on William Fiset's course
import java.util.*;
import java.lang.*;

class PriorityQueue<T extends Comparable<T>> {
  // A variable to keep track of the size of the heap
  private int heapSize = 0;

  // A variable to actually keep track of the heap
  private List<T> heap;

  // Constructors for the PriorityQueue
  PriorityQueue() {
    this(1);
  }

  PriorityQueue(int size) {
    heap = ArrayList<T>(size);
  }

  // Returns if heap is empty
  public boolean isEmpty() {
    return heapSize == 0;
  }

  // Returns the size of the heap
  public int size() {
    return heapSize;
  }

  // Returns the first element of the heap
  public T peek() {
    if(isEmpty()) return null;
    return heap.get(0);
  }

  // Removes and returns the first element of the heap
  public T poll() {
    return removeAt(0);
  }


  // Returns whether the heap contains the given element (linear)
  public boolean contains(T e) {
    for(int i = 0; i < heapSize; i++) {
      if(heap.get(i).compareTo(e) == 0) return true;
    }

    return false;
  }

  // Adds the given element to the heap
  public void add(T e) {
    // Add the element to the end of the array
    heap.add(e);
    heapSize++;

    // Swim up the element as necessary
    swim(heapSize-1);
  }

  // Returns if element at index i is less than element at index j
  public boolean less(int i, int j) {
    T iIndex = heap.get(i);
    T jIndex = heap.get(j);
    return iIndex.compareTo(jIndex) <= 0;
  }

  // Function to swim the element at index k as far up as it can go
  private void swim(int k) {
    int parent = (k-1)/2;

    while(k > 0 && less(k, parent)) {
      swap(k, parent);
      k = parent;
      parent = (k-1)/2;
    }
  }

  // Function to sink the element at index k until it satisfies the heap invariant
  private void sink(int k) {
    while(true) {
      int leftChild = 2*k+1;
      int rightChild = 2*k+2;
      int smallest = leftChild;

      // Check if right child is smaller
      if(right < heapSize && less(rightChild, left)) smallest = rightChild;

      // Stop if we're outside the tree, or if we can't sink k anymore
      if(left >= heapSize || less(k, smallest)) break;

      swap(smallest, k);
      k = smallest;
    }
  }

  // Function to swap two nodes in our heap.
  private void swap(int i, int j) {
    T iElement = heap.get(i);
    T jElement = heap.get(j);

    heap.set(i, jElement);
    heap.set(j, iElement);
  }

  // Remove given element from the heap
  public boolean remove(T element) {
    if(element == null) return false;
    // Linear search for element
    for(int i = 0; i < heapSize; i++) {
      if(element.equals(heap.get(i))) {
        removeAt(i);
        return true;
      }
    }

    return false;
  }

  // Removes a node at a particular index
  private T removeAt(int i) {
    if(isEmpty()) return null;

    heapSize--;
    T removed = heap.get(i);
    swap(i, heapSize);

    heap.set(heapSize, null);

    if(i == heapSize) return removed;
    T elem = heap.get(i);

    // Try sinking element
    sink(i);

    // If sinking did not work, try swimming
    if(heap.get(i).equals(elem)) swim(i);
    return removed;
  }

}
