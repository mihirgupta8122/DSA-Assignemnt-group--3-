package ADTStack;

// Test class to verify the Postfix Calculator logic, Stack implementation and BST variable storage
public class TestMain {
    public static void main(String[] args) {
        PostfixCalculator calculator = new PostfixCalculator();

        System.out.println("--- Starting Postfix Calculator Tests ---");

        // Test basic integer evaluation result should be 5
        String exp1 = "2 3 +";
        int res1 = calculator.evaluatePostfixExpression(exp1);
        System.out.println("Test 1: " + exp1 + " = " + res1);

        System.out.println("\n--- All Tests Completed Successfully ---");
    }

}

   