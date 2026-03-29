package ADTStack;

public class PostfixCalculator {

	// BST that stores variable names and their integer values
	private BinarySearchTree variableTree;

	// Stack used during postfix evaluation
	private StackInterface<Integer> stack;

	// Set up the calculator with a fresh BST and an empty stack
	public PostfixCalculator() {
		variableTree = new BinarySearchTree();
		stack        = new ArrayStack<>();
	}

	// Store a variable and its value in the BST
	// If the variable already exists, update its value
	public void setVariable(String key, int value) {
		variableTree.insert(key, value);
	}

	// Remove every variable from the BST
	public void deleteAllVariables() {
		variableTree.deleteAll();
	}

	// Display the current state of the variable BST on the console
	public void displayTree() {
		variableTree.displayTree();
	}

	// Evaluate a postfix arithmetic expression and return the integer result
	// Tokens must be separated by spaces, e.g. "x y + z *"
	public int evaluatePostfixExpression(String expression) {

		// Clear the stack before each evaluation so no state carries over
		stack.clear();

		// Split on whitespace to get individual tokens
		String[] tokens = expression.trim().split("\\s+");

		for (String token : tokens) {

			if (isOperator(token)) {
				// Operator: pop two operands and apply the operation
				if (stack.isEmpty()) {
					throw new IllegalArgumentException(
						"Malformed expression: not enough operands for operator '" + token + "'.");
				}
				int operand2 = stack.pop(); // Pushed second, so it is the right operand

				if (stack.isEmpty()) {
					throw new IllegalArgumentException(
						"Malformed expression: not enough operands for operator '" + token + "'.");
				}
				int operand1 = stack.pop(); // Pushed first, so it is the left operand

				int result = applyOperator(token, operand1, operand2);
				stack.push(result);

			} else {
				// Operand: either a known variable name or an integer literal
				int value;

				if (variableTree.contains(token)) {
					// Token matches a variable stored in the BST, look up its value
					value = variableTree.search(token);
				} else {
					// Try to parse the token as a plain integer
					try {
						value = Integer.parseInt(token);
					} catch (NumberFormatException e) {
						throw new IllegalArgumentException(
							"Unknown token '" + token + "': not an operator, variable, or integer.");
					}
				}

				// Push the resolved value and record it in the BST
				stack.push(value);
				variableTree.insert(token, value);
			}
		}

		// The single remaining item on the stack is the final answer
		if (stack.isEmpty()) {
			throw new IllegalArgumentException(
				"Expression evaluated to nothing - check the input.");
		}

		return stack.pop();
	}

	// Check if a token is one of the four supported arithmetic operators
	private boolean isOperator(String token) {
		return token.equals("+") || token.equals("-")
			|| token.equals("*") || token.equals("/");
	}

	// Apply an arithmetic operator to two operands and return the result
	private int applyOperator(String operator, int a, int b) {
		switch (operator) {
			case "+": return a + b;
			case "-": return a - b;
			case "*": return a * b;
			case "/":
				if (b == 0) {
					throw new ArithmeticException("Division by zero in postfix expression.");
				}
				return a / b;
			default:
				throw new IllegalArgumentException("Unknown operator: '" + operator + "'.");
		}
	}

} // end PostfixCalculator
