public class Answer {

	public static Boolean inputControl(int[] numbers, String input) {
		
		// Takes the numbers in the input string to an integer array
		String tempInput = input;
		tempInput = tempInput.replace('+', '@').replace('-', '@').replace('*', '@').replace('/', '@').replace('(', '@').replace(')', '@');
		String[] inputTemp = tempInput.split("@");
		int[] inputValues = new int[100];
		int counter = 0;
		
		for (int i = 0; i < inputTemp.length; i++) {
			if(!inputTemp[i].isEmpty()) {
				inputValues[counter] = Integer.valueOf(inputTemp[i]);
				counter++;
			}
				
		}
		
		Boolean valid = true;
		
		for (int i = 0; i < inputValues.length; i++) {
			valid = true;
			int counter1 = 0;
			int counter2 = 0;
			for (int j = 0; j < inputValues.length; j++) {

				if(inputValues[i] == inputValues[j])
					counter1++;
				
			}
			
			for (int k = 0; k < numbers.length; k++) {
				
				if(inputValues[i] == numbers[k])
					counter2++;
				
			}
			if(counter1 > counter2) {
				valid = false;
				break;
			}
				
			System.out.println(valid);
			
		}
		
		
		
		return false;
	}
	
	
}
