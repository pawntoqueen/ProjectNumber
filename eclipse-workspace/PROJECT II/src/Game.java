import java.util.Scanner;

import enigma.core.Enigma;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("NUMBERS", 100, 30, 20, 5);

	Scanner scan = new Scanner(System.in);

	ComputerAnswer calculate;
	Question question;
	Operation operation = new Operation();
	Answer answer = new Answer();

	int targetRange = 1;
	int playerScore = 0;
	int computerScore = 0;
	int point = 0;

	public Game() throws Exception {

		menu();

		while (playerScore < 100 && computerScore < 100) {

			question = new Question();
			printGameScreen(question);
			answer.takeResult(calculate);

			// player's result is closer to target number
			if (Math.abs(answer.getResult() - question.getTargetNumber()) <= Math
					.abs(calculate.getLastResult() - question.getTargetNumber())) {
				answer.takeInput();
				operation.infixToPostfix(answer.getInfix(), false);

				if (answer.inputControl(question.getRandomNumbers())
						&& answer.getResult() == operation.postfixEvaluation(operation.toString(), false)) {
					operation.infixToPostfix(answer.getInfix(), true);
					operation.postfixEvaluation(operation.toString(), true);
				}

				if (Math.abs(answer.getResult() - question.getTargetNumber()) <= 10)
					point = 15 - Math.abs(answer.getResult() - question.getTargetNumber());
				else
					point = 5;
				if (answer.getResult() != operation.postfixEvaluation(operation.toString(), false))
					System.out.println("Your result doesn't match your input value.");
				else
					playerScore += point;

			}
			// computer's result is closer to target number
			else {

				calculate.printSolutionSteps();

				if (Math.abs(calculate.getLastResult() - question.getTargetNumber()) <= 10)
					point = 15 - Math.abs(calculate.getLastResult() - question.getTargetNumber());
				else
					point = 5;

				computerScore += point;

			}

			System.out.println("Player Score : " + playerScore);
			System.out.println("Computer Score : " + computerScore);
			scan.nextLine();

			clearConsole(29, 100);

		}

		clearConsole(10, 40);

		if (playerScore > computerScore)
			System.out.println("Player Win!");
		else if (playerScore < computerScore)
			System.out.println("Computer Win!");
		else
			System.out.println("It's a draw!");

	}

	public void menu() {

		int choice;

		do {
			Scanner scan = new Scanner(System.in);
			cn.getTextWindow().setCursorPosition(0, 0);
			System.out.println("1 - Start");
			System.out.println("2 - Options");
			System.out.println("3 - Instructions");
			choice = scan.nextInt();

			scan.close();

			clearConsole(10, 40);
			if (choice == 2) {

				difficultySelection();
				clearConsole(10, 40);
			}
			if (choice == 3) {

				cn.getTextWindow().setCursorPosition(0, 0);
				System.out.println(
						"Game is played with two players: human player and computer. A random target number between 100-999 is determined initially. Players will try to reach/approach this target number in 30 seconds. Players use five random small numbers between 1-9, and one big random number (25, 50, 75 or 100) and 4 basic operations (* / + -) to reach this target.\r\n"
								+ "\r\n" + "Game Playing Rules/Stages\r\n" + "\r\n"
								+ "1. Target number is chosen randomly (100-999). \r\n"
								+ "2. Five small numbers are chosen randomly (1-9). \r\n"
								+ "3. One big number is chosen randomly (25/50/75/100). \r\n"
								+ "4. 30-second countdown starts.\r\n"
								+ "5. During this time, human player makes calculations in his mind or with pen and paper. Computer tries to reach the target by using random operations with randomly chosen numbers.\r\n"
								+ "6. Rules for operations:\r\n"
								+ "         *   Players can use each number only once (given or calculated).\r\n"
								+ "         *   Players do not have to use all the numbers.\r\n"
								+ "         *   For division operation, integer division will be used.  \r\n"
								+ "         *   If parenthesis are not used, multiplication and division have higher priority. \r\n"
								+ "              Operations which have the same priority are calculated from left to right. \r\n"
								+ "7. When the time is up, players will announce their result numbers.\r\n"
								+ "8. The player with the closest number explains how he reached this number.\r\n"
								+ "9. If the explanation is correct, he gets the point; if it is wrong, then his opponent gets that point.\r\n"
								+ "10. The one who reaches 100 points first wins the game.\r\n" + "\r\n"
								+ "Explanation of the Operations\r\n" + "\r\n"
								+ "If the human is the closest to the target number, he enters an infix expression. This expression must first be transformed to postfix expression, then this postfix expression must be evaluated. Stages of the transformation/evaluation must be shown on the screen step by step.\r\n"
								+ "\r\n"
								+ "If the computer player is the closest to the target number, it displays each operation used on the screen. \r\n"
								+ "\r\n" + "Scoring\r\n" + "\r\n"
								+ "If the difference is less than or equals to 10;point = 15 - Abs(TargetNumber - CalculatedNumber)\r\n"
								+ "If the difference is greater than 10; point = 5\r\n" + "");
				System.out.println("Press any key to go back to menu");
				Scanner wait = new Scanner(System.in);
				wait.nextLine();
				clearConsole(29, 100);

			}

		} while (choice != 1);

	}

	public void difficultySelection() {

		Scanner difficultyInput = new Scanner(System.in);

		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.println("Select difficulty: ");
		System.out.println("1. Easy    (Target range becomes 20)");
		System.out.println("2. Medium  (Target range becomes 10)");
		System.out.println("3. Hard    (Target range becomes 1)");

		String answer = " ";

		while (!"123".contains(answer)) {
			answer = difficultyInput.next();
			if ("123".contains(answer)) {
				if (answer.equals("1"))
					targetRange = 20;
				else if (answer.equals("2"))
					targetRange = 10;
				else
					targetRange = 1;
			} else
				System.out.println("Wrong input, please try again.");
		}

		difficultyInput.close();
	}

	public void clearConsole(int x, int y) {
		for (int i = 0; i < x; i++) {

			for (int j = 0; j < y; j++) {

				cn.getTextWindow().setCursorPosition(j, i);
				System.out.println(" ");

			}

		}
	}

	public void printGameScreen(Question question) {

		calculate = new ComputerAnswer();

		cn.getTextWindow().setCursorPosition(0, 0);
		System.out.println("-------------------------------------- Round " + ++Question.round
				+ " --------------------------------------------");

		System.out.println("Target Number : " + question.getTargetNumber());
		System.out.print("Numbers: ");

		for (int i = 0; i < 6; i++) {
			System.out.print(question.getRandomNumbers()[i] + " ");
		}
		System.out.println();

		System.out.print("Duration: ");
		calculateDuringTime(calculate, question);

		System.out.println(
				"--------------------------------------------------------------------------------------------");

	}

	public void calculateDuringTime(ComputerAnswer calculate, Question question) {

		int countdown = 30;
		long time1 = 0;
		long time2 = System.currentTimeMillis();
		boolean flag = true;

		do {
			time1 = System.currentTimeMillis();

			if (flag) {
				calculate.ComputerAnswer(question);
				if (Math.abs(calculate.getLastResult() - question.getTargetNumber()) < targetRange)
					flag = false;
			}

			if (time1 - time2 >= 100) {
				System.out.print(countdown-- + " ");

				time2 = time1;
			}

		} while (countdown >= 0);
		System.out.println("\n");

	}

}