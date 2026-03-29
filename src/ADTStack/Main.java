package ADTStack;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		// Display welcome dialog before the program starts
		JOptionPane.showMessageDialog(
			null,
			"A text-based calculator that can evaluate arithmetic expressions\n"
			+ "in postfix notation using a stack data structure and handle variables\n"
			+ "using a binary search tree (BST).\n"
			+ "You will design and implement an array-based stack, and then develop and\n"
			+ "implement a provided algorithm to evaluate postfix expressions, handle\n"
			+ "integer operands and basic arithmetic operations, integrate a binary search\n"
			+ "tree, and handle deletion of all variables.\n"
			+ "The correctness of your implementation can be verified by following the\n"
			+ "provided postfix expressions as test cases in the sample runs.\n\n"
			+ "Press OK to Start",
			"Welcome to",
			JOptionPane.INFORMATION_MESSAGE
		);

		// Single calculator instance reused across all 8 test cases
		PostfixCalculator calculator = new PostfixCalculator();

		// Divider printed between each test case for readability
		String divider = "==================================================";

		// Postfix expression 1 - variables: x=5, y=3, z=4
		// Expression: x y + z *  which is  (5 + 3) * 4 = 32
		System.out.println(divider);
		System.out.println("Postfix Expression 1: x y + z *");

		calculator.setVariable("x", 5);
		calculator.setVariable("y", 3);
		calculator.setVariable("z", 4);

		int result1 = calculator.evaluatePostfixExpression("x y + z *");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result1);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 2 - variables: a=2, b=3, c=4
		// Expression: a b * c +  which is  (2 * 3) + 4 = 10
		System.out.println(divider);
		System.out.println("Postfix Expression 2: a b * c +");

		calculator.setVariable("a", 2);
		calculator.setVariable("b", 3);
		calculator.setVariable("c", 4);

		int result2 = calculator.evaluatePostfixExpression("a b * c +");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result2);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 3 - variables: m=8, n=2, p=3
		// Expression: m n / p +  which is  (8 / 2) + 3 = 7
		System.out.println(divider);
		System.out.println("Postfix Expression 3: m n / p +");

		calculator.setVariable("m", 8);
		calculator.setVariable("n", 2);
		calculator.setVariable("p", 3);

		int result3 = calculator.evaluatePostfixExpression("m n / p +");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result3);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 4 - variables: q=7, r=3, s=2
		// Expression: q r - s *  which is  (7 - 3) * 2 = 8
		System.out.println(divider);
		System.out.println("Postfix Expression 4: q r - s *");

		calculator.setVariable("q", 7);
		calculator.setVariable("r", 3);
		calculator.setVariable("s", 2);

		int result4 = calculator.evaluatePostfixExpression("q r - s *");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result4);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 5 - variables: d=1, e=2, f=3
		// Expression: d e + f *  which is  (1 + 2) * 3 = 9
		System.out.println(divider);
		System.out.println("Postfix Expression 5: d e + f *");

		calculator.setVariable("d", 1);
		calculator.setVariable("e", 2);
		calculator.setVariable("f", 3);

		int result5 = calculator.evaluatePostfixExpression("d e + f *");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result5);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 6 - variables: g=2, h=3, i=4, j=5
		// Expression: g h i + * j /  which is  2 * (3 + 4) / 5 = 2
		// This is the worked BST example from the assignment document
		System.out.println(divider);
		System.out.println("Postfix Expression 6: g h i + * j /");

		calculator.setVariable("g", 2);
		calculator.setVariable("h", 3);
		calculator.setVariable("i", 4);
		calculator.setVariable("j", 5);

		int result6 = calculator.evaluatePostfixExpression("g h i + * j /");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result6);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 7 - variables: k=2, l=3, m=4, n=5
		// Expression: k l + m n - *  which is  (2 + 3) * (4 - 5) = -5
		System.out.println(divider);
		System.out.println("Postfix Expression 7: k l + m n - *");

		calculator.setVariable("k", 2);
		calculator.setVariable("l", 3);
		calculator.setVariable("m", 4);
		calculator.setVariable("n", 5);

		int result7 = calculator.evaluatePostfixExpression("k l + m n - *");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result7);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		// Postfix expression 8 - variables: o=9, p=3, q=5, r=2, s=7
		// Expression: o p / q r * + s -  which is  (9/3) + (5*2) - 7 = 6
		System.out.println(divider);
		System.out.println("Postfix Expression 8: o p / q r * + s -");

		calculator.setVariable("o", 9);
		calculator.setVariable("p", 3);
		calculator.setVariable("q", 5);
		calculator.setVariable("r", 2);
		calculator.setVariable("s", 7);

		int result8 = calculator.evaluatePostfixExpression("o p / q r * + s -");

		System.out.println("\nBinary Search Tree:");
		calculator.displayTree();
		System.out.println("\nResult: " + result8);

		calculator.deleteAllVariables();
		System.out.println("All variables have been deleted.");
		System.out.println("BST after deletion:");
		calculator.displayTree();

		System.out.println(divider);
		System.out.println("All 8 test cases complete.");

	} // end main

} // end Main
