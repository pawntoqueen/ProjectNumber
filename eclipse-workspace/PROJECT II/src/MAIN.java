
public class MAIN {

	public static void main(String[] args) throws Exception {
		Game game = new Game();
		//game.printGameScreen();
		
		System.out.println("Result: " + game.postfixEvaluation("1 6 9 * 4 / 75 9 - * +"));
	}
	
	
}
