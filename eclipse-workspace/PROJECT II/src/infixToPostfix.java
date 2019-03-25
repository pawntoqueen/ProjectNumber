import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class infixToPostfix {

	Stack stack = new Stack();
	ArrayList<String> operators;
	String postfix = "";

	public infixToPostfix(String infix) {

		
		operators = new ArrayList<String>();

		operators.add("+");
		operators.add("-");
		operators.add("*");
		operators.add("/");
		operators.add("(");
		operators.add(")");

		while (infix.length() > 0) {

			String operand;
			String operator;
			boolean operation;

			if (!operators.contains(infix.substring(0, 1))) {
				while (!operators.contains(infix.substring(0, 1)) && !infix.isEmpty()) {
					operand = infix.substring(0, 1);
					postfix = postfix + operand;
					infix = infix.substring(1);
				}
				postfix += " ";
				System.out.println(postfix);
			} else if (operators.get(4).equals(infix.substring(0, 1))) {
				stack.push(infix.substring(0, 1));
				infix = infix.substring(1);
			} else if (operators.get(5).equals(infix.substring(0, 1))) {
				while (!stack.peek().equals("(")) {
					postfix = postfix + " " + stack.pop();
					System.out.println(postfix);
				}
				stack.pop();
				infix = infix.substring(1);
			} else {
				operator = infix.substring(0, 1);

				if (!stack.isEmpty()) {
					do {
						operation = true;
						if ((operator.equals("*") || operator.equals("/"))
								&& (stack.peek().equals("+") || stack.peek().equals("-")))
							operation = false;
						else if (stack.peek().equals("("))
							operation = false;
						if (operation == true) {
							postfix = postfix + " " + stack.pop();
							System.out.println(postfix);
						}

					} while (operation && !stack.isEmpty());
				}
				stack.push(operator);
				infix = infix.substring(1);
				postfix += " ";
			}
		}

		while (!stack.isEmpty()) {
			postfix = postfix + " " + stack.pop();
		}
	}

	public String toString() {
		return postfix;
	}

	public static void main(String[] args) {

		// Scanner input = new Scanner(System.in);
		// System.out.print("Enter player's solution : ");
		// String infix = input.next();
		infixToPostfix postfix = new infixToPostfix("(1+6)*9/4*(75-9)");
		System.out.println("Postfix expression : " + postfix);

	}

}