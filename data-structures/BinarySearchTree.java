import java.util.*;
import java.lang.*;

// Implementation of a Binary Search Tree based on William Fiset's course on Udemy
// Supports find, add, and remove functions, does not support duplicate values

public class BinarySearchTree<T extends Comparable<T>> {
  // Node class
  class Node {
    T data;
    Node left;
    Node right;

    Node(T d) {
      data = d;
    }

    Node(T d, Node l, Node r) {
      data = d;
      left = l;
      right = r;
    }
  }

  // The root of the BST
  private Node root;

  // Function that returns if the tree is empty
  public boolean isEmpty() {
    return root == null;
  }

  // Function that returns the node with the given value or false
  public boolean find(T value) {
    return find(root, value);
  }

  // Private function to find the value
  private boolean find(Node n, T value) {
    // Base case, node is null
    if(n == null) return false;

    int cmp = value.compareTo(n.data);

    // Left subtree case
    if(cmp < 0) return find(n.left, value);
    // Right subtree case
    else if(cmp > 0) return find(n.right, value);
    // Found the value
    else return true;
  }

  // Function that adds the given value to the tree
  public void add(T value) {
    // Check if the tree already has the value
    if(find(value)) return;
    else root = add(root, value);
  }

  // Private add function
  private Node add(Node n, T value) {
    if(n == null) n = new Node(value);
    else {
      if(value.compareTo(n.data) < 0) n.left = add(n.left, value);
      else n.right = add(n.right, value);
    }

    return n;
  }

  // Function that removes the given value from the tree. Returns if successful removal
  public boolean remove(T value) {
    // Make sure that it exists
    if(find(value)) {
      remove(root, value);
      return true;
    }

    return false;
  }

  // Private function that removes a given value from the tree
  private Node remove(Node n, T value) {
    if(n == null) return null;

    // First find the value
    int cmp = value.compareTo(n.data);

    if(cmp < 0) n.left = remove(n.left, value);
    else if(cmp > 0) n.right = remove(n.right, value);
    // We're at the correct node
    else {
      // Three cases
      // First: No children nodes
      // Second: One child, left or right
      // Handle first and second with same logic
      if(n.left == null) {
        Node rightChild = n.right;

        n.data = null;
        n = null;

        return rightChild;
      } else if(n.right == null) {
        Node leftChild = n.left;

        n.data = null;
        n = null;

        return leftChild;
      }
      // Third: Two children
      else {
        // Find the successor (always pulling from the right)
        Node success = successor(n);

        // Swap data
        n.data = success.data;

        // Remove the swapped data from the right subtree
        n.right = remove(n.right, n.data);
      }
    }

    return n;
  }

  // Private function that returns the successor of a node with two children
  private Node successor(Node n) {
    Node rightChild = n.right;

    while(rightChild.left != null) rightChild = rightChild.left;
    return rightChild;
  }

  // Function that prints the BST in Level Order
  public void printTree() {
    LinkedList<Node> upNext = new LinkedList<>();

    upNext.add(root);

    System.out.print("Binary Search Tree: ");
    while(!upNext.isEmpty()) {
      Node curr = upNext.poll();

      if(curr.left != null)
        upNext.add(curr.left);
      if(curr.right != null)
        upNext.add(curr.right);

      System.out.print(curr.data + " ");
    }
    System.out.println();
  }

  public static void main(String args[]) {
    System.out.println("Binary Search Tree Implementation: ");

    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.add(4);
    bst.add(2);
    bst.add(6);
    bst.add(5);
    bst.add(8);

    bst.printTree();

    System.out.println("Removing node 5.");
    bst.remove(5);
    bst.printTree();
  }
}
