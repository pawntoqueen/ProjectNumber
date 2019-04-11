import java.util.Scanner;

import enigma.core.Enigma;

public class Game {
	
	ComputerAnswer calculate;

	static enigma.console.Console cn = Enigma.getConsole("NUMBERS", 100, 30, 20, 5);

	Operation operation = new Operation();
	Answer answer = new Answer();

	Game() throws Exception {
		
		difficultySelection();
		
		Question question = new Question();
		printGameScreen(question);
		
		calculate = new ComputerAnswer(question);
			
		answer.takeInput();
		if (answer.inputControl(question.getRandomNumbers())) {
			operation.infixToPostfix(answer.getInfix());
			operation.postfixEvaluation(operation.toString());
		}

	}
	
	public void difficultySelection() {
		
		Scanner difficultyInput = new Scanner(System.in);
				
		System.out.println("Select difficulty: ");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		
		
		String answer = " ";
		
		while (!"123".contains(answer)) {
			answer = difficultyInput.next();
			if ("123".contains(answer)) {
				if (answer.equals("1"))
					ComputerAnswer.targetRange = 20;
				else if (answer.equals("2"))
					ComputerAnswer.targetRange = 10;
				else
					ComputerAnswer.targetRange = 1;
			} 
			else
				System.out.println("Wrong input, please try again.");
		}
		
		difficultyInput.close();
	}
	
	public void printGameScreen(Question question) {

		System.out.println("-------------------------------------- Round " + ++Question.round
				+ " --------------------------------------------");

		System.out.println("Target Number : " + question.getTargetNumber());

		System.out.print("Numbers: ");
		for (int i = 0; i < 6; i++) {
			System.out.print(question.getRandomNumbers()[i] + " ");
		}
		System.out.println();

		System.out.print("Duration: ");
		time();

		System.out.println(
				"--------------------------------------------------------------------------------------------");

	}


	public void time() {
		int countdown = 30;
		long time1 = 0;
		long time2 = System.currentTimeMillis();

		do {
			time1 = System.currentTimeMillis();

			if (time1 - time2 >= 10) {
				System.out.print(countdown-- + " ");

				time2 = time1;
			}

		} while (countdown >= 0);
		System.out.println("\n");

	}

}