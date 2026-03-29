package ADTStack;

public class BinarySearchTree {    
    // Internal class that represents a single node in the BST
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

    // Searches for a variables value by its name
    public Integer search(String key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.value;
            current = (cmp < 0) ? current.left : current.right; // Navigate left or right
        }
        return -1; // Key not found case
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


    // Deletes a specific variable from the tree by its key
    public void delete(String key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node root, String key) {
        // In a case where the key is not in the tree
        if (root == null) return null;

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            // Key is in the left subtree
            root.left = deleteRecursive(root.left, key);
        } else if (cmp > 0) {
            // Key is in the right subtree
            root.right = deleteRecursive(root.right, key);
        } else {
            
            // If node has only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // If the node has 2 child find the smallest one
            root.key = minValue(root.right);
            // Update value to match the new key
            root.value = search(root.key); 
            // Delete the duplicate node from the right subtree
            root.right = deleteRecursive(root.right, root.key);
        }
        return root;
    }

    // Helper method to find the smallest key in a subtree
    private String minValue(Node root) {
        String minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    // Find the smallest key in a subtree (used to find the in-order successor)
	  private Node findMin(Node node) {
		    while (node.left != null) {
			      node = node.left;
		    }
		    return node;
	  }

    // Deletes all of the nodes from the tree by nulling the root
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