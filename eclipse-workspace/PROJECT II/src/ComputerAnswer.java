
public class ComputerAnswer {

	ComputerAnswer() {

		String[] operators = { "+", "-", "*", "/" };
		int targetNumber = 500;
		int min = 999;
		int lastResult = 0;

		for (int k = 0; k < 5; k++) {

			int[] numbers = { 1, 2, 3, 4, 5, 100 };

			int random1 = 0, random2 = 0, randomOperator = 0;
			int result = 0, counter = 0;
			boolean calculate = false;

			for (int i = 0; i < 5; i++) {

				do {

					calculate = true;
					random1 = (int) (Math.random() * 6);
					random2 = (int) (Math.random() * 6);

					if (random1 == random2 || numbers[random2] == 0 || numbers[random1] < numbers[random2])
						calculate = false;
					if (numbers[random1] == 1000 || numbers[random2] == 1000)
						calculate = false;

				} while (!calculate);

				randomOperator = (int) (Math.random() * 4);

				if (randomOperator == 0)
					result = numbers[random1] + numbers[random2];
				else if (randomOperator == 1)
					result = numbers[random1] - numbers[random2];
				else if (randomOperator == 2)
					result = numbers[random1] * numbers[random2];
				else if (randomOperator == 3)
					result = numbers[random1] / numbers[random2];

				// System.out.println(numbers[random1] + " " + operators[randomOperator] + " " +
				// numbers[random2] + " = " + result);

				if (random1 < random2) {
					numbers[random1] = result;
					numbers[random2] = 1000;
				} else {
					numbers[random2] = result;
					numbers[random1] = 1000;
				}

			}
			// System.out.println("########## Result : " + result + " ##########");

			if (Math.abs(targetNumber - result) < min) {

				min = Math.abs(targetNumber - result);
				lastResult = result;

			}

		}
		System.out.println();
		System.out.println("#######################################");
		System.out.println("########## Last Result : " + lastResult + " ##########");
		System.out.println("#######################################");
		System.out.println();
	}

}
