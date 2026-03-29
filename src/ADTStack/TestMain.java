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

        testCalculater("2 3 5 + +", 10);
        testCalculater("9 3 /", 3);
        testCalculater("4 3 *", 12);
        testCalculater("16 12 -", 4);
        testCalculater("6 2 *", 12);

        System.out.println("\n--- All Tests Completed Successfully ---");
    }

    public static void testCalculater(String expression, int expectedResult) {
        PostfixCalculator calculator = new PostfixCalculator();
        int res1 = calculator.evaluatePostfixExpression(expression);
        System.out.println("Test 1: " + expression + " = " + res1 + " = " + expectedResult);
        if (res1 == expectedResult) {
            System.out.print(" Pass");
        } else {
            System.out.println("Fail");
        }
    }
}