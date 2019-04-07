
public class ComputerAnswer {
	String[] operators= {"+","-","*","/"};
	int min =999,lastResult=0;
	
	ComputerAnswer(Question question){		
		int targetNumber = question.getTargetNumber();
		Queue queue = new Queue(1000);
		int num1=0, num2=0;
		do {
			int result=0;
			for (int i = 0; i < question.getRandomNumbers().length-1; i++) {
				queue.Enqueue(question.getRandomNumbers()[i]);
			}
			
				int random1 = (int) (Math.random() * queue.Size());
				if(queue.Size()>1) {
					for (int i = 0; i < random1; i++) {
						queue.Enqueue(queue.Dequeue());
					}
					 num1=(int)queue.Dequeue();
				}
				
				if(queue.Size()>1) 
				{
					int random2 = (int) (Math.random() * queue.Size());
					for (int i = 0; i < random2; i++) {
						queue.Enqueue(queue.Dequeue());
					}
					num2=(int)queue.Dequeue();
				}
				
				int randomOperator = (int) (Math.random() * 4);
				
				if (randomOperator == 0)
					result = num1 + num2;
				else if (randomOperator == 1)
					result = num1 - num2;
				else if (randomOperator == 2)
					result = num1 * num2;
				else if (randomOperator == 3 && num2 !=0)
					result = num1 / num2;
				
				queue.Enqueue(result);
				

			
			if (Math.abs(targetNumber - result) < min) {

				min = Math.abs(targetNumber - result);
				lastResult = result;

			}
		}while(Math.abs(lastResult-targetNumber)>3);
		
		
		System.out.println("#######################################");
		System.out.println("########## Last Result : " + lastResult + " ##########");
		System.out.println("#######################################");

	}
}
