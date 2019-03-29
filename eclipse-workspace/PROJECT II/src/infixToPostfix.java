class infixToPostfix {

	Stack stack = new Stack(25);
	Stack temp = new Stack(25);
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
				stack.Push(infix.substring(0, 1));
				infix = infix.substring(1);
			} else if (operators[5].equals(infix.substring(0, 1))) {
				while (!stack.Peek().equals("(")) {
					postfix = postfix + " " + stack.Pop();
					System.out.println(postfix + "\t\t\t"); printStack();
				}
				stack.Pop();
				infix = infix.substring(1);
			} else {
				operator = infix.substring(0, 1);

				if (!stack.isEmpty()) {
					do {
						operation = true;
						if ((operator.equals("*") || operator.equals("/"))
								&& (stack.Peek().equals("+") || stack.Peek().equals("-")))
							operation = false;
						else if (stack.Peek().equals("("))
							operation = false;
						if (operation == true) {
							postfix = postfix + " " + stack.Pop();
							System.out.println(postfix + "\t\t\t"); printStack();
						}

					} while (operation && !stack.isEmpty());
				}
				stack.Push(operator);
				System.out.println(postfix + "\t\t\t"); printStack();
				infix = infix.substring(1);
				postfix += " ";
			}
		}

		while (!stack.isEmpty()) {
			postfix = postfix + " " + stack.Pop();
		}
	}

	public String toString() {
		return postfix;
	}
	
	public void printStack() {
		
		System.out.println();
		while(!stack.isEmpty()) {
			
			System.out.println("| " + stack.Peek() + " |");
			temp.Push(stack.Pop());
			
		}
		System.out.println("-----");
		while(!temp.isEmpty()) {
			stack.Push(temp.Pop());
		}
		System.out.println();
		
	}

	

}