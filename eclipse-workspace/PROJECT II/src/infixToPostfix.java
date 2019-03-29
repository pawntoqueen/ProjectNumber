import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class infixToPostfix {

	Stack stack = new Stack();
	Stack temp = new Stack();
	String [] operators = {"+", "-", "*", "/", "(", ")" };
	String postfix = "";

	public infixToPostfix(String infix) {
		
		while (infix.length() > 0) {

			String operand;
			String operator;
			boolean operation;
			boolean notOperator = true;

			for (int i = 0; i < operators.length; i++) {
				if(operators[i].equals(infix.substring(0, 1)))
					notOperator = false;
			}
			
			if (notOperator) {
				while (notOperator && !infix.isEmpty()) {
					operand = infix.substring(0, 1);
					postfix = postfix + operand;
					infix = infix.substring(1);
					for (int i = 0; i < operators.length; i++) {
						if(operators[i].equals(infix.substring(0, 1)))
							notOperator = false;
					}
				}
				System.out.println(postfix + "\t\t\t"); printStack();
			} else if (operators[4].equals(infix.substring(0, 1))) {
				stack.push(infix.substring(0, 1));
				infix = infix.substring(1);
			} else if (operators[5].equals(infix.substring(0, 1))) {
				while (!stack.peek().equals("(")) {
					postfix = postfix + " " + stack.pop();
					System.out.println(postfix + "\t\t\t"); printStack();
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
							System.out.println(postfix + "\t\t\t"); printStack();
						}

					} while (operation && !stack.isEmpty());
				}
				stack.push(operator);
				System.out.println(postfix + "\t\t\t"); printStack();
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
	
	public void printStack() {
		
		System.out.println();
		while(!stack.isEmpty()) {
			
			System.out.println("| " + stack.peek() + " |");
			temp.push(stack.pop());
			
		}
		System.out.println("-----");
		while(!temp.isEmpty()) {
			stack.push(temp.pop());
		}
		System.out.println();
		
	}

	

}