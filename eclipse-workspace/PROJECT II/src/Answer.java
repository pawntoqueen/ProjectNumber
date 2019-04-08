import java.util.Scanner;

public class Answer {

	private String infix;

	public Boolean inputControl(int[] numbers) {

		String input = infix;
		String[] inputArray = input.split("");
		Boolean validCharsOnly = true;
		
		// the user didn't use any letters and/or other characters.
		for (int i = 0; i < input.length(); i++) {
			if(!"+-*/0123456789()".contains(inputArray[i])) {
				validCharsOnly = false;
				break;
			}
		}
		
		if (validCharsOnly) {
			
			// Takes the numbers in the input string to an integer array
			input = input.replace('+', '@').replace('-', '@').replace('*', '@').replace('/', '@').replace('(', '@')
					.replace(')', '@');
			String[] inputSplit = input.split("@");
			int[] inputValues = new int[100];
			int counter = 0;
			for (int i = 0; i < inputSplit.length; i++) {
				if (!inputSplit[i].isEmpty()) {
					inputValues[counter] = Integer.valueOf(inputSplit[i]);
					counter++;
				}

			}
			// the user used only random numbers
			Boolean usedRandomNumbers = true;
			for (int i = 0; i < counter; i++) {
				usedRandomNumbers = true;
				int counter1 = 0;
				int counter2 = 0;
				for (int j = 0; j < counter; j++) {

					if (inputValues[i] == inputValues[j])
						counter1++;

				}

				for (int k = 0; k < numbers.length; k++) {

					if (inputValues[i] == numbers[k])
						counter2++;

				}
				if (counter1 > counter2) {
					usedRandomNumbers = false;
					break;
				}

			}
			
			// correct parenthesis
			Boolean correctParenthesis = true;
			Boolean temp = false;
			for (int i = 0; i < inputArray.length; i++) {
				if (!temp && inputArray[i].equals(")")) {
					correctParenthesis = false;
					break;
				}
				
				if (!temp && inputArray[i].equals("(")) {
					temp = true;
				}
			}
			
			
			// no adjacent "+-*/"
			Boolean noAdjacentOperators = true;
			for (int i = 0; i < inputArray.length - 1; i++) {
				if ("+-*/".contains(inputArray[i]) && "+-*/".contains(inputArray[i+1])){
					noAdjacentOperators = false;
					break;
				}
			}
			
			if(!usedRandomNumbers)
				System.out.println("You must only use the given numbers.");
			if(!correctParenthesis)
				System.out.println("You should use parenthesises correct.");
			if(!noAdjacentOperators)
				System.out.println("You can't have two operators next to each other.");
			
			return usedRandomNumbers && correctParenthesis && noAdjacentOperators;
		}
		else { // if not validCharsOnly
			System.out.println("You should only use valid characters.");
			return false;
		}
	}

	public void takeInput() {

		Scanner input = new Scanner(System.in);
		System.out.print("Enter player's solution: ");
		this.infix = input.next();
		System.out.println();
		input.close();

	}

	public String getInfix() {
		return infix;
	}

	public void setInfix(String infix) {
		this.infix = infix;
	}

}
