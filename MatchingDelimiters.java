 * Description: Checks matching delimiters using a stack and
 * displays the stack operations during execution.
 * Programmed by: Samraida P. Mamarda
 * Course: BSIT
 * CN: 48079
 * Subject: Data Structures And Algorithms
 * Last Modified: October 29, 2026
 * Version: 1.0
 

import java.util.Scanner;
import java.util.Stack;

public class MatchingDelimiters {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== STACK: MATCHING DELIMITERS ===");
        System.out.println("Enter a sequence such as {[()]} or ([)]");
        System.out.print("Input: ");

        String expression = input.nextLine();

        boolean valid = checkDelimiters(expression);

        System.out.println();
        System.out.println("Result: " + (valid ? "Valid" : "Invalid"));

        input.close();
    }

    public static boolean checkDelimiters(String expression) {
        Stack<Character> stack = new Stack<Character>();

        System.out.println("\n--- Stack Execution Trace ---");

        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);

            if (isOpening(current)) {
                stack.push(current);

                System.out.println("Read: " + current);
                System.out.println("Operation: PUSH " + current);
                System.out.println("Stack: " + stack);
                System.out.println();

            } else if (isClosing(current)) {
                System.out.println("Read: " + current);

                if (stack.isEmpty()) {
                    System.out.println("Operation: INVALID");
                    System.out.println(
                        "Reason: Closing delimiter '" + current
                        + "' has no matching opening delimiter."
                    );
                    System.out.println("Stack: " + stack);
                    return false;
                }

                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                if (matches(top, current)) {
                    stack.pop();

                    System.out.println("Operation: POP " + top);
                    System.out.println("Stack: " + stack);
                    System.out.println();

                } else {
                    System.out.println("Operation: INVALID");
                    System.out.println(
                        "Reason: Closing '" + current
                        + "' does not match opening '" + top + "'."
                    );
                    System.out.println("Stack: " + stack);
                    return false;
                }

            } else {
                System.out.println("Read: " + current);
                System.out.println("Operation: IGNORE (not a delimiter)");
                System.out.println("Stack: " + stack);
                System.out.println();
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("Operation: INVALID");
            System.out.println(
                "Reason: Unmatched opening delimiters remain: " + stack
            );
            System.out.println("Stack: " + stack);
            return false;
        }

        System.out.println("All delimiters matched. Stack is empty.");
        return true;
    }

    public static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    public static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    public static boolean matches(char opening, char closing) {
        return (opening == '(' && closing == ')')
            || (opening == '[' && closing == ']')
            || (opening == '{' && closing == '}');
    }
}
