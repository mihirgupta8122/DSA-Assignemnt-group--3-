package ADTStack;

public class BinarySearchTree {
    
    // Internal class that represents a single node in the BST
    private class Node {
        String key;     
        int value;      
        Node left, right;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;

    // Inserts a new variable or updates an existing one.
    public void insert(String key, int value) {
        root = insertRecursive(root, key, value);
    }

    private Node insertRecursive(Node root, String key, int value) {
        // Base case is if tree/branch is empty, create a new node
        if (root == null) return new Node(key, value);
        
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            // If key is smaller go to the left subtree
            root.left = insertRecursive(root.left, key, value);
        } else if (cmp > 0) {
            // If key is larger go to the right subtree
            root.right = insertRecursive(root.right, key, value);
        } else {
            // Key already exists update its value
            root.value = value;
        }
        return root;
    }

    // Searches for a variables value by its name
    public Integer search(String key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) return current.value;
            current = (cmp < 0) ? current.left : current.right; // Navigate left or right
        }
        return null; // Key not found case
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

    // Deletes all of the nodes from the tree by nulling the root
    public void deleteAll() {
        root = null;
    }

    // Displays the tree in a hierarchical structure for visualization
    public void displayTree() {
        if (root == null) {
            System.out.println("The variable tree is currently empty.");
        } else {
            displayRecursive(root, 0);
        }
    }

    private void displayRecursive(Node root, int level) {
        if (root == null) return;
        
        // Prints right subtree first
        displayRecursive(root.right, level + 1);
        
        // Formatting
        System.out.println("    ".repeat(level) + "|-- " + root.key + " (" + root.value + ")");
        
        // Print left subtree
        displayRecursive(root.left, level + 1);
    }
}