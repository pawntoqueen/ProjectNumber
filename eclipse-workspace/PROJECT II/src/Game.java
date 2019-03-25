import java.util.Random;

public class Game {
	
	static int step = 1;
	
	public void printGameScreen() throws Exception
	{
		Random rnd= new Random();
		int round=1;
		System.out.println("--------------Round " + round+"--------------");
		int target_number= rnd.nextInt(900)+100;
		System.out.println("Target Number: "+ target_number);
		int[] numbers = new int[7]; 
		System.out.println();
		System.out.print("Numbers: ");
		for (int i = 0; i < 5; i++) {
			numbers[i] = rnd.nextInt(9) + 1;
			System.out.print(numbers[i]+" ");
		}
		numbers[6] = (rnd.nextInt(4)+1)*25;
		System.out.println(numbers[6]);
		System.out.println();
		System.out.print("Duration: ");
		for (int i = 30; i >=0; i--) {
			System.out.print(" "+i);
			Thread.sleep(100);
		}
		System.out.println();
		System.out.println("------------------------------------");		
	

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
				
				showPostFixEvalStep(expression, postfix, i);
				
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
				
				showPostFixEvalStep(expression, postfix, i);
				
			}
			
			
			
			
		} // for end
		System.out.println("-------------------------");
		
		// return the result
		return (Integer) postfix.Pop();
		
	}
	
	public void showPostFixEvalStep(String[] remain, Stack postfix, int iter) {
		
		/*
		 * This function prints one step of the function game.postfixEvaluation().
		 */
		
		Stack temp = new Stack(20); // temporary stack for showing the interior of the postfix stack.
		
		while(!postfix.isEmpty()) {
			temp.Push(postfix.Peek());
			postfix.Pop();
		}
		System.out.print("Step " + step + ": | ");
		
		while(!temp.isEmpty()) {
			System.out.print(temp.Peek() + " ");
			postfix.Push(temp.Peek());
			temp.Pop();
		}
		System.out.println();
		System.out.print("Postfix: ");
		for (int i = iter; i < remain.length; i++) {
			System.out.print(remain[i]);
		}
		step++;
		System.out.println();
		System.out.println();

	public Stack prefix() {
		String inpt="1+6*9/4*(75-9)";

		String[] convert = new String[20];
		int j=0, k=0;

		while (k<inpt.length()-1) {
			while(!inpt.substring(k,k+1).equals("*") && !inpt.substring(k,k+1).equals("+")&& !inpt.substring(k,k+1).equals("-")&&!inpt.substring(k,k+1).equals("/")&&!inpt.substring(k,k+1).equals("(")&&!inpt.substring(k,k+1).equals(")"))
			{	
				convert[j]+= inpt.substring(k,k+1); k++;
			}
			j++;
			if(inpt.substring(k,k+1).equals("*") || inpt.substring(k,k+1).equals("+") || inpt.substring(k,k+1).equals("-") || inpt.substring(k,k+1).equals("/") || inpt.substring(k,k+1).equals("(") || inpt.substring(k,k+1).equals(")"))
			{	
				convert[j]+= inpt.substring(k,k+1); k++;j++;
			}
		}

		Stack stcTemp= new Stack(convert.length);
		for (int i = 0; i < convert.length; i++) {
			if(convert[i]!=null) {
				System.out.print(" "+ convert[i].substring(4));
				stcTemp.Push(convert[i].substring(4));	
			}
			
		}
		return stcTemp;
  
	}
	
}

