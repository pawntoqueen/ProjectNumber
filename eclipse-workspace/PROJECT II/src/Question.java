import java.util.Random;

public class Question {
	
	Random rnd = new Random();
	private int targetNumber;
	private int[] randomNumbers = new int[7];
	static int round=0;
	
	public Question() {

		targetNumber = rnd.nextInt(899) + 100;
		for (int i = 0; i < randomNumbers.length-2; i++) {
			randomNumbers[i] = rnd.nextInt(8) + 1;
		}
		randomNumbers[5]=(rnd.nextInt(4) + 1) * 25;
	}

	public int getTargetNumber() {
		return targetNumber;
	}

	public void setTargetNumber(int targetNumber) {
		this.targetNumber = targetNumber;
	}

	public int[] getRandomNumbers() {
		return randomNumbers;
	}

	public void setRandomNumbers(int[] randomNumbers) {
		this.randomNumbers = randomNumbers;
	}

	
}
