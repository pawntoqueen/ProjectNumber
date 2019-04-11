public class ComputerAnswer {

	String[] operators = { "+", "-", "*", "/" };
	int min = 999;
	static int targetRange = 0;
	private int lastResult;

	int counter = 0;

	private int stepNumber = 0;
	private ComputerSolutionSteps[] solutionSteps;

	ComputerAnswer(Question question) {

		int targetNumber = question.getTargetNumber();
		Queue queue;
		int num1 = 0, num2 = 0;
		solutionSteps = new ComputerSolutionSteps[1000000];

		do {

			queue = new Queue(6);
			int result = 0;

			for (int i = 0; i < question.getRandomNumbers().length; i++) {
				queue.Enqueue(question.getRandomNumbers()[i]);
			}

			while (queue.Size() > 1) {

				int random1 = (int) (Math.random() * queue.Size() + 1);
				for (int i = 0; i < random1; i++) {
					queue.Enqueue(queue.Dequeue());
				}
				num1 = (int) queue.Dequeue();

				int random2 = (int) (Math.random() * queue.Size());
				for (int i = 0; i < random2; i++) {
					queue.Enqueue(queue.Dequeue());
				}
				num2 = (int) queue.Dequeue();

				int randomOperator = (int) (Math.random() * 4);

				if (randomOperator == 0)
					result = num1 + num2;
				else if (randomOperator == 1)
					result = num1 - num2;
				else if (randomOperator == 2)
					result = num1 * num2;
				else if (randomOperator == 3 && num2 != 0)
					result = num1 / num2;

				queue.Enqueue(result);
				addSolutionStep(num1, num2, operators[randomOperator], result);

				if (Math.abs(targetNumber - result) < min) {

					min = Math.abs(targetNumber - result);
					lastResult = result;

				}

			}
			counter++;

		} while (Math.abs(lastResult - targetNumber) > targetRange);

		System.out.println("#######################################");
		System.out.println("########## Last Result : " + lastResult + " ##########");
		System.out.println("#######################################\n");

		System.out.println(counter + "\n");

		for (int i = 0; i < solutionSteps.length; i++) {

			if (solutionSteps[i] != null && solutionSteps[i].getResult() == lastResult) {

				System.out.println(solutionSteps[i - 4].display());
				System.out.println(solutionSteps[i - 3].display());
				System.out.println(solutionSteps[i - 2].display());
				System.out.println(solutionSteps[i - 1].display());
				System.out.println(solutionSteps[i].display());
			}

		}
		System.out.println();

	}

	public void addSolutionStep(int num1, int num2, String operator, int result) {

		solutionSteps[stepNumber++] = new ComputerSolutionSteps(num1, num2, operator, result);

	}

	public int getLastResult() {
		return lastResult;
	}

	public void setLastResult(int lastResult) {
		this.lastResult = lastResult;
	}

}