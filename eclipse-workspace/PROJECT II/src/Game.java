import java.util.Scanner;

import enigma.core.Enigma;
import enigma.event.TextMouseEvent;
import enigma.event.TextMouseListener;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("NUMBERS", 100, 35, 20, 5);

	public static TextMouseListener tmlis;

	// ------ Standard variables for mouse ------
	public static int mousepr; // mouse pressed?
	public static int mousex; // mouse text coords.
	public static int mousey;
	// ----------------------------------------------------

	public static String choice = "";

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
			try {answer.takeResult(calculate); } catch(NumberFormatException n) {System.out.println("wrong input! Computer win.");}
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
				if (answer.getResult() != operation.postfixEvaluation(operation.toString(), false)) {

					System.out.println("Your result doesn't match your input value.");
					computerScore += point;
					System.out.println("Computer gets " + point + " points\n");

				} else {

					playerScore += point;
					System.out.println("Player gets " + point + " points\n");
				}

			}
			// computer's result is closer to target number
			else {
				System.out.println();
				System.out.println("Computer solution steps:");
				calculate.printSolutionSteps();

				if (Math.abs(calculate.getLastResult() - question.getTargetNumber()) <= 10)
					point = 15 - Math.abs(calculate.getLastResult() - question.getTargetNumber());
				else
					point = 5;

				computerScore += point;
				System.out.println("Computer gets " + point + " points\n");

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

	public static String mouseControl() throws InterruptedException {

		// ------ Standard code for mouse ------

		tmlis = new TextMouseListener() {
			public void mouseClicked(TextMouseEvent arg0) {
			}

			public void mousePressed(TextMouseEvent arg0) {
				if (mousepr == 0) {
					mousepr = 1;
					mousex = arg0.getX();
					mousey = arg0.getY();
				}
			}

			public void mouseReleased(TextMouseEvent arg0) {
			}
		};
		cn.getTextWindow().addTextMouseListener(tmlis);

		// ----------------------------------------------------

		int px = 5, py = 5;

		while (true) {
			if (mousepr == 1) { // if mouse button pressed

				px = mousex;
				py = mousey;
				if (mousex >= 0 && mousex <= 13 && mousey == 1)
					choice = "start";
				if (mousex >= 0 && mousex <= 10 && mousey == 3)
					choice = "options";
				if (mousex >= 16 && mousex <= 46 && mousey == 3)
					choice = "easy";
				if (mousex >= 16 && mousex <= 46 && mousey == 4)
					choice = "normal";
				if (mousex >= 16 && mousex <= 46 && mousey == 5)
					choice = "hard";
				if (mousex == 0 && mousey == 3)
					choice = "back to menu";
				if (mousex >= 0 && mousex <= 15 && mousey == 5)
					choice = "instructions";
				if (mousex >= 0 && mousex <= 7 && mousey == 7)
					choice = "exit";
				if (mousex >= 0 && mousex <= 15 && mousey == 9)
					choice = "options -> menu";
				if (mousex >= 0 && mousex <= 15 && mousey == 29)
					choice = "instructions -> menu";
				if (mousex >= 39 && mousex <= 47 && mousey == 20)
					choice = "go to menu";

				mousepr = 0; // last action

			}
			// thread command just used for mouse control
			Thread.sleep(20);
			return choice;
		}
	}

	public void menu() throws InterruptedException {

		cn.getTextWindow().setCursorPosition(0, 10);
		System.out.println("\r\n"
				+ " ____  _____  _____  _____  ____    ____  ______   ________  _______     ______   \r\n"
				+ "|_   \\|_   _||_   _||_   _||_   \\  /   _||_   _ \\ |_   __  ||_   __ \\  .' ____ \\  \r\n"
				+ "  |   \\ | |    | |    | |    |   \\/   |    | |_) |  | |_ \\_|  | |__) | | (___ \\_| \r\n"
				+ "  | |\\ \\| |    | '    ' |    | |\\  /| |    |  __'.  |  _| _   |  __ /   _.____`.  \r\n"
				+ " _| |_\\   |_    \\ \\__/ /    _| |_\\/_| |_  _| |__) |_| |__/ | _| |  \\ \\_| \\____) | \r\n"
				+ "|_____|\\____|    `.__.'    |_____||_____||_______/|________||____| |___|\\______.' \r\n"
				+ "                                                                                  \r\n" + "");

		cn.getTextWindow().setCursorPosition(30, 20);
		System.out.println("click to | Start |");

		while (true) {
			mouseControl();
			if (choice.equals("go to menu"))
				break;

		}

		clearConsole(25, 100);

		while (!choice.equals("start")) {

			clearConsole(10, 50);

			cn.getTextWindow().setCursorPosition(0, 1);
			System.out.println("| Start Game |");
			System.out.println();
			System.out.println("| Options |");
			System.out.println();
			System.out.println("| Instructions |");
			System.out.println();
			System.out.println("| Exit |");

			mouseControl();

			if (choice.equals("options")) {

				clearConsole(10, 20);
				while (true) {
					cn.getTextWindow().setCursorPosition(0, 0);
					System.out.println();
					System.out.println("Select difficulty : ");
					System.out.println();
					System.out.println("				| Easy    (Target range : 20) |");
					System.out.println("				| Normal  (Target range : 10) |");
					System.out.println("				| Hard    (Target range : 1)  |");
					System.out.println("\n");
					System.out.println("\n| Back to Menu |");
					mouseControl();
					if (choice.equals("easy")) {
						targetRange = 20;
						cn.getTextWindow().setCursorPosition(18, 7);
						System.out.println("Difficulty is Easy!	");
					}
					if (choice.equals("normal")) {
						targetRange = 10;
						cn.getTextWindow().setCursorPosition(18, 7);
						System.out.println("Difficulty is Normal!	");
					}
					if (choice.equals("hard")) {
						targetRange = 1;
						cn.getTextWindow().setCursorPosition(18, 7);
						System.out.println("Difficulty is Hard!	");
					}
					if (choice.equals("options -> menu"))
						break;

				}
			}
			if (choice.equals("instructions")) {

				clearConsole(10, 20);
				cn.getTextWindow().setCursorPosition(0, 0);
				System.out.println();
				cn.getTextWindow().setCursorPosition(0, 1);
				System.out.println(
						"   Game is played with two players: human player and computer.A random target number between 100-999 is determined initially. Players will try to reach/approach this target number in 30 seconds. \r\n"
								+ "Players use five random small numbers between 1-9, and one big random number (25, 50, 75 or 100) and 4 basic operations (* / + -) to reach this target.\r\n"
								+ "\r\n" + "   Game Playing Rules/Stages\r\n" + "\r\n"
								+ "   Target number is chosen randomly (100-999). Five small numbers are chosen randomly (1-9). One big number is chosen randomly (25/50/75/100). 30-second countdown starts. \r\n"
								+ "During this time, human player makes calculations in his mind or with pen and paper. Computer tries to reach the target by using random operations with randomly chosen numbers.\r\n"
								+ "   Rules for operations:\r\n"
								+ "     *	Players can use each number only once (given or calculated).\r\n"
								+ "     *	Players do not have to use all the numbers.\r\n"
								+ "     *	For division operation, integer division will be used.  \r\n"
								+ "     *	If parenthesis are not used, multiplication and division have higher priority. Operations which have the same priority are calculated from left to right. \r\n"
								+ "   When the time is up, players will announce their result numbers. The player with the closest     number explains how he reached this number.\r\n"
								+ "If the explanation is correct, he gets the point; if it is wrong, then his opponent gets that point. The one who reaches 100 points first wins the game.\r\n"
								+ "\r\n" + "   Scoring\r\n" + "\r\n"
								+ "If the difference is less than or equals to 10; point = 15 - Abs(TargetNumber - CalculatedNumber)\r\n"
								+ "If the difference is greater than 10; point = 5\r\n" + "");
				System.out.println();
				System.out.println("| Back to Menu |");
				while (true) {
					mouseControl();
					if (choice.equals("instructions -> menu")) {

						clearConsole(32, 100);
						break;
					}

				}
			}
			if (choice.equals("exit"))
				System.exit(0);

		}

		clearConsole(10, 40);

	}

	public void clearConsole(int x, int y) {
		for (int i = 0; i < x; i++) {

			for (int j = 0; j < y; j++) {

				cn.getTextWindow().setCursorPosition(j, i);
				System.out.println(" ");

			}

		}
	}

	int y_coordinate = -1;

	public void printGameScreen(Question question) {

		calculate = new ComputerAnswer();

		cn.getTextWindow().setCursorPosition(0, y_coordinate);
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

		y_coordinate -= 4;

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

			if (time1 - time2 >= 1) {
				System.out.print(countdown-- + " ");

				time2 = time1;
			}

		} while (countdown >= 0);
		System.out.println("\n");

	}

}