package ADTStack;

public class PostfixCalculator {

    // BST that stores variable names and their integer values
    private BinarySearchTree variableTree;

    // Stack used during postfix evaluation
    private StackInterface<Integer> stack;

    // Constructor initializes BST and stack
    public PostfixCalculator() {
        variableTree = new BinarySearchTree();
        stack = new ArrayStack<>();
    }

    // Store or update a variable in the BST
    public void setVariable(String key, int value) {
        variableTree.insert(key, value);
    }

    // Remove all variables from the BST
    public void deleteAllVariables() {
        variableTree.deleteAll();
    }

    // Display the current BST contents
    public void showTree() {
        variableTree.displayTree();
    }

    // Evaluate a postfix expression
    public int evaluatePostfixExpression(String expression) {

        // Ensure stack starts empty
        stack.clear();

        String[] tokens = expression.trim().split("\\s+");

        for (String token : tokens) {

            if (isOperator(token)) {

                // Check for enough operands
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Malformed expression: not enough operands for operator '" + token + "'");
                }
                int operand2 = stack.pop();

                if (stack.isEmpty()) {
                    throw new IllegalArgumentException(
                        "Malformed expression: not enough operands for operator '" + token + "'");
                }
                int operand1 = stack.pop();

                int result = performOperation(token, operand1, operand2);
                stack.push(result);

            } else {

                Integer value = variableTree.search(token);

                if (value != null || value != -1) {
                    // Token is a stored variable
                    stack.push(value);
                } else {
                    // Try parsing as integer literal
                    try {
                        int numericValue = Integer.parseInt(token);
                        stack.push(numericValue);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                            "Error: '" + token + "' is not a variable or valid integer.");
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Expression evaluated to nothing.");
        }

        return stack.pop();
    }

    // Identify supported operators
    private boolean isOperator(String token) {
        return token.equals("+") ||
               token.equals("-") ||
               token.equals("*") ||
               token.equals("/");
    }

    // Perform arithmetic operation
    private int performOperation(String operator, int a, int b) {

        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException("Division by zero error.");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}