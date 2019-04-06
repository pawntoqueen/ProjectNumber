import enigma.core.Enigma;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("NUMBERS", 100, 30, 20, 5);

	Question question = new Question();
	Operation operation = new Operation();
	Answer answer = new Answer();

	Game() throws Exception {

		printGameScreen();
		ComputerAnswer calculate = new ComputerAnswer();
		answer.takeInput();
		if (answer.inputControl(question.getRandomNumbers())) {

			operation.infixToPostfix(answer.getInfix());
			operation.postfixEvaluation(operation.toString());

		} else {
			cn.getTextWindow().setCursorPosition(10, 15);
			System.out.println("Your input is invalid.");
		}

	}

	public void printGameScreen() {

		System.out.println("-------------------------------------- Round " + ++Question.round
				+ " --------------------------------------------");

		System.out.println("Target Number : " + question.getTargetNumber());

		System.out.print("Numbers: ");
		for (int i = 0; i < 6; i++) {
			System.out.print(question.getRandomNumbers()[i] + " ");
		}
		System.out.println();

		System.out.print("Duration: ");
		question.time();

		System.out.println(
				"--------------------------------------------------------------------------------------------");

	}

}
