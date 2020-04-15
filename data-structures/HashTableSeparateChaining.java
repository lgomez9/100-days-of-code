// An implementation of a hash table using separate chaining
// Based on William Fiset's course on Udemy
import java.util.*;

public class HashTableSeparateChaining {
  // A class to hold entries in the hash table
  private class Entry<K, V> {
    K key;
    V value;
    int hash;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.hash = key.hashCode();
    }

    public boolean equals(Entry<K, V> e) {
      if(hash != e.hash) return false;
      return key.equals(e.key);
    }

    @Override
    // Overriding the toString method
    public String toString() {
      return key + " : " + value;
    }
  }

  private double maxLoadFactor;
  private int capacity, threshold, size = 0;
  private LinkedList<Entry<K, V>>[] table;

  // Constructor
  public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
    if(capacity < 0) throw new IllegalArgumentException("Illegal capacity");
    if(maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
      throw new IllegalArgumentException("Illegal maxLoadFactor");
    this.maxLoadFactor = maxLoadFactor;
    this.capacity = capacity;
    threshold = (int) (this.capacity * maxLoadFactor);
    table = new LinkedList[this.capacity];
  }

  // Return the size of the hash table
  public int size() {
    return size;
  }

  // Return if the table is empty
  public boolean isEmpty() {
    return size == 0;
  }

  // Converts a hash value to something within the range of the index
  public int normalizeHash(int keyHash) {
    return keyHash % capacity;
  }

  // Function to check if a key is in this hash table
  public boolean containsKey(K key) {
    int index = normalizeHash(key.hashCode());
    LinkedList<Entry<K, V>> indexList = table[index];
    for(Entry<K, V> e : indexList) if(e.key.equals(key)) return true;
    return false;
  }

  // Function to add an element to this hash table
  public void add(K key, V value) {
    if(containsKey(key)) return;

    int index = normalizeHash(key.hashCode());
    LinkedList<Entry<K, V>> indexList = table[index];
    // Check if the list exists
    if(indexList == null) indexList = new LinkedList<Entry<K, V>>();

    // Else
    indexList.add(new Entry(key, value));
    size++;

    // Check if this violates the threshold
    if(size > threshold) rehashTable();
  }

  // Function to remove an element from this hash table
  public V remove(K key) {
    // Make sure that the key is in the hash table
    if(!containsKey(key)) return null;

    int index = normalizeHash(key.hashCode());
    LinkedList<Entry<K, V>> indexList = table[index];

    // Find the appropriate entry and remove it
    for(Entry<K, V> e : indexList) {
      if(e.key.equals(key)) {
        indexList.remove(e);
        size--;
        return e;
      }
    }
  }

  // Function to get an element from this hash table
  public V get(K key) {
    // Make sure that the key is in the hash table
    if(!containsKey(key)) return null;

    int index = normalizeHash(key.hashCode());
    LinkedList<Entry<K, V>> indexList = table[index];

    // Return the appropriate value
    for(Entry<K, V> e : indexList) {
      if(e.key.equals(key)) return e.value;
    }
  }

  // Function to increase the hash table size and rehash
  private void rehashTable() {
    capacity *= 2;
    threshold = (int) (capacity * maxLoadFactor);

    LinkedList<Entry<K, V>>[] newTable = new LinkedList<>[capacity];

    for(int i = 0; i < table.length; i++) {
      if(table[i] != null) {
        for(Entry<K, V> e : table[i]) {
          int index = normalizeHash(e.hash);
          LinkedList<Entry<K, V>> indexList = newTable[index];
          if(indexList == null) indexList = new LinkedList<>();
          indexList.add(e);
        }

        table[i].clear();
        table[i] = null;
      }
    }

    table = newTable;
  }

  // Function that returns a list of the keys in this hash table
  public List<K> keyList() {
    List<K> keys = new ArrayList<>(size());
    for(LinkedList<Entry<K,V>> list : table) {
      if(list != null) for(Entry<K, V> e : list) keys.add(e.key);
    }
    return keys;
  }

  // Function that returns a list of the values in this hash table
  public List<V> valueList() {
    List<V> values = new ArrayList<>(size());
    for(LinkedList<Entry<K,V>> list : table) {
      if(list != null) for(Entry<K, V> e : list) keys.add(e.value);
    }
    return values;
  }
}
