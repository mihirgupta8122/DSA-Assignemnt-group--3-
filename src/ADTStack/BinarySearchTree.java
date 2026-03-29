package ADTStack;

public class BinarySearchTree {

	// Inner class representing a single node in the BST
	private class Node {
		String key;   // Variable name (e.g. "x", "y")
		int value;    // Variable's integer value
		Node left;    // Left child (keys smaller than this node)
		Node right;   // Right child (keys larger than this node)

		// Create a new leaf node with the given key and value
		Node(String key, int value) {
			this.key   = key;
			this.value = value;
			this.left  = null;
			this.right = null;
		}
	}

	// Root node of the binary search tree
	private Node root;

	// Create an empty binary search tree
	public BinarySearchTree() {
		root = null;
	}

	// Insert a new key-value pair into the BST
	// If the key already exists, update its value
	public void insert(String key, int value) {
		root = insertRecursive(root, key, value);
	}

	// Recursive helper to find the correct position and insert the new node
	private Node insertRecursive(Node current, String key, int value) {
		// Base case: empty spot found, create the new node here
		if (current == null) {
			return new Node(key, value);
		}

		int cmp = key.compareTo(current.key);

		if (cmp < 0) {
			// Key is smaller, go left
			current.left = insertRecursive(current.left, key, value);
		} else if (cmp > 0) {
			// Key is larger, go right
			current.right = insertRecursive(current.right, key, value);
		} else {
			// Key already exists, update the value
			current.value = value;
		}

		return current;
	}

	// Search the BST for a key and return its value
	// Throws IllegalArgumentException if the key is not found
	public int search(String key) {
		Node current = root;

		while (current != null) {
			int cmp = key.compareTo(current.key);

			if (cmp == 0) {
				return current.value;    // Found the key
			} else if (cmp < 0) {
				current = current.left;  // Search left subtree
			} else {
				current = current.right; // Search right subtree
			}
		}

		// Key was never found in the tree
		throw new IllegalArgumentException("Variable '" + key + "' not found in BST.");
	}

	// Check if a key exists in the BST without throwing an exception
	public boolean contains(String key) {
		Node current = root;

		while (current != null) {
			int cmp = key.compareTo(current.key);

			if (cmp == 0) {
				return true;
			} else if (cmp < 0) {
				current = current.left;
			} else {
				current = current.right;
			}
		}

		return false;
	}

	// Remove the node with the given key from the BST
	public void delete(String key) {
		root = deleteRecursive(root, key);
	}

	// Recursive helper to find and remove the target node
	private Node deleteRecursive(Node current, String key) {
		if (current == null) {
			return null; // Key not in tree, nothing to do
		}

		int cmp = key.compareTo(current.key);

		if (cmp < 0) {
			// Target is in the left subtree
			current.left = deleteRecursive(current.left, key);
		} else if (cmp > 0) {
			// Target is in the right subtree
			current.right = deleteRecursive(current.right, key);
		} else {
			// Found the node to delete, handle the three cases

			// Case 1: leaf node with no children
			if (current.left == null && current.right == null) {
				return null;
			}

			// Case 2a: only a right child
			if (current.left == null) {
				return current.right;
			}

			// Case 2b: only a left child
			if (current.right == null) {
				return current.left;
			}

			// Case 3: two children, replace with in-order successor
			Node successor = findMin(current.right);
			current.key   = successor.key;
			current.value = successor.value;
			// Remove the successor from the right subtree
			current.right = deleteRecursive(current.right, successor.key);
		}

		return current;
	}

	// Find the smallest key in a subtree (used to find the in-order successor)
	private Node findMin(Node node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	// Remove every node from the BST, leaving it empty
	public void deleteAll() {
		root = null;
	}

	// Print the BST to the console in a hierarchical structure
	// Each node is shown as ||==> key:value, indented by its depth
	public void displayTree() {
		if (root == null) {
			System.out.println("(BST is empty - all variables deleted)");
			return;
		}
		displayRecursive(root, 0);
	}

	// Recursive helper to print each node indented by its depth
	// Right subtree is visited first so the tree reads top to bottom on screen
	private void displayRecursive(Node node, int depth) {
		if (node == null) {
			return;
		}

		// Visit right subtree first (appears higher on screen)
		displayRecursive(node.right, depth + 1);

		// Build indentation, two spaces per level of depth
		StringBuilder indent = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			indent.append("  ");
		}

		// Print this node, e.g. ||==> j:5
		System.out.println(indent + "||==> " + node.key + ":" + node.value);

		// Visit left subtree (appears lower on screen)
		displayRecursive(node.left, depth + 1);
	}

} // end BinarySearchTree
