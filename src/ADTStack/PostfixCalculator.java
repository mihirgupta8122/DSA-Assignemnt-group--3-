package ADTStack;

import java.util.EmptyStackException;

public class PostfixCalculator {
    private BinarySearchTree variableTree;
    private StackInterface<Integer> stack;

    public PostfixCalculator() {
        // Initialize the BST and the stack from scratch
        this.variableTree = new BinarySearchTree();
        this.stack = new ArrayStack<>(); 
    }

    public int evaluatePostfixExpression(String expression) {
        stack.clear(); // Makes sure the stack is empty before starting
        
        // Split expression into tokens by spaces
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (isOperator(token)) {
                // If operator is encountered pop the top two operands
                try {
                    int operand2 = stack.pop();
                    int operand1 = stack.pop();
                    int result = performOperation(token, operand1, operand2);
                    stack.push(result); // Push result back onto stack
                } catch (EmptyStackException e) {
                    throw new IllegalArgumentException("Invalid expression, not enough operands.");
                }
            } else {
                // Check if the token is a variable in the BST
                Integer value = variableTree.search(token);
                // Finds the variable vlaue
                if (value != null) {
                    stack.push(value); 
                } else {
                    // If its not a variable treat it as an integer literal
                    try {
                        int numericValue = Integer.parseInt(token);
                        stack.push(numericValue);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Error: '" + token + "' is neither a variable or a valid integer.");
                    }
                }
            }
        }
        
        // Final result is the only element remaining on the stack
        return stack.pop();
    }

    // Helper to identify supported operators
    private boolean isOperator(String token) {
        return "+-*/".contains(token) && token.length() == 1;
    }

    // Performs the specific math operation based on the operator token
    private int performOperation(String operator, int a, int b) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": 
                if (b == 0) throw new ArithmeticException("Division by zero error.");
                return a / b;
            default: throw new UnsupportedOperationException("Operator not supported.");
        }
    }

    // Stores a variable name and its value in the BST
    public void setVariable(String key, int value) {
        variableTree.insert(key, value);
    }

    // Removes all variables from tree
    public void deleteAllVariables() {
        variableTree.deleteAll();
    }

    //Shows the current state of the variable tree
    public void showTree() {
        variableTree.displayTree();
    }
}