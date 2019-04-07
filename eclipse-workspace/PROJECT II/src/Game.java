import enigma.core.Enigma;

public class Game {

	static enigma.console.Console cn = Enigma.getConsole("NUMBERS", 100, 30, 20, 5);


	Operation operation = new Operation();
	Answer answer = new Answer();

	Game() throws Exception {
		Question question = new Question();
		question.printGameScreen();
		
		ComputerAnswer calculate = new ComputerAnswer(question);
			
			
		answer.takeInput();
		if (answer.inputControl(question.getRandomNumbers())) {

			operation.infixToPostfix(answer.getInfix());
			operation.postfixEvaluation(operation.toString());

		} else {
			cn.getTextWindow().setCursorPosition(10, 15);
			System.out.println("Your input is invalid.");
		}

	}

	

}
