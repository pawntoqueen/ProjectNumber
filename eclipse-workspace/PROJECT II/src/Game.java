import java.util.Random;
import java.util.Scanner;

public class Game {

	public void printGameScreen() throws Exception {
		Random rnd = new Random();
		int round = 1;
		System.out.println("-------------------------------------- Round " + round
				+ " --------------------------------------------");
		int target_number = rnd.nextInt(899) + 100;
		System.out.println("Target Number : " + target_number);
		int[] numbers = new int[7];
		System.out.println();
		System.out.print("Numbers: ");
		for (int i = 0; i < 5; i++) {
			numbers[i] = rnd.nextInt(8) + 1;
			System.out.print(numbers[i] + " ");
		}
		numbers[6] = (rnd.nextInt(4) + 1) * 25;
		System.out.println(numbers[6]);
		System.out.println();
		System.out.print("Duration: ");

		time();

		
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------");

		Scanner input = new Scanner(System.in);
	    System.out.print("Enter player's solution : ");
		String infix = input.next();
		infixToPostfix postfix = new infixToPostfix(infix);
		
		postfixEvaluation(postfix.toString());
		

	}

	public void time() {
		int countdown = 30;
		long time1 = 0;
		long time2 = System.currentTimeMillis();

		do {

			// butun islemler burada olacak

			time1 = System.currentTimeMillis();

			if (time1 - time2 >= 50) {
				System.out.print(countdown-- + " ");

				time2 = time1;
			}

		} while (countdown != 0);
		System.out.println("\n");

	}

  
  	public int postfixEvaluation(String expressionString) throws Exception {
		/*
		 * This function evaluates the postfix expression.
		 */
		
		Stack postfix = new Stack(20);
		
		String[] expression = expressionString.split(" ");
		System.out.println("Postfix expression: " + expressionString);
		
		System.out.println("-------------------------");
		
		for (int i = 0; i < expression.length; i++) { // for begin
			
			// if the character is a number, push it into the stack.
			if( !("+-*/".contains(expression[i])) ) {
				postfix.Push(expression[i]);
			}
			// if it's an operator,
			// pop 2 integers and apply the operator,
			// then push the result into the stack.
			else if (postfix.Size() >= 2){
				
				showPostFixEvalStep(postfix);
				
				//
				int firstOperand = Integer.valueOf(String.valueOf(postfix.Peek()));
				postfix.Pop();
				int secondOperand = Integer.valueOf(String.valueOf(postfix.Peek()));
				postfix.Pop();
				//
				
				//
				if(expression[i].equals("+"))
					postfix.Push(secondOperand + firstOperand);
				else if(expression[i].equals("-"))
					postfix.Push(secondOperand - firstOperand);
				else if(expression[i].equals("*"))
					postfix.Push(secondOperand * firstOperand);
				else if(expression[i].equals("/"))
					postfix.Push(secondOperand / firstOperand);
				//
				
				showPostFixEvalStep(postfix);
				
			}
			
			
			
			
		} // for end
		System.out.println("-------------------------");
		
		// return the result
		return (Integer) postfix.Pop();
		
	}
	
	public void showPostFixEvalStep(Stack stack) {
		
		/*
		 * This function prints one step of the function game.postfixEvaluation().
		 */
		
		Stack temp = new Stack(stack.Size());
		
		System.out.println();
		while(!stack.isEmpty()) {
			if(Integer.valueOf(String.valueOf(stack.Peek())) / 10 > 0)
				System.out.println("| " + stack.Peek() + " |");
			else
				System.out.println("| " + stack.Peek() + "  |");
			temp.Push(stack.Pop());
			
		}
		System.out.println("------");
		while(!temp.isEmpty()) {
			stack.Push(temp.Pop());
		}
		System.out.println();
		
	}

  
  
}
