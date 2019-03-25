import java.util.Random;

public class Game {
	
	
	public void printGameScreen() throws Exception
	{
		Random rnd= new Random();
		int round=1;
		System.out.println("--------------Round " + round+"--------------");
		int target_number= rnd.nextInt(900)+100;
		System.out.println("Target Number: "+ target_number);
		int[] numbers = new int[7]; 
		System.out.println();
		System.out.print("Numbers: ");
		for (int i = 0; i < 5; i++) {
			numbers[i] = rnd.nextInt(9) + 1;
			System.out.print(numbers[i]+" ");
		}
		numbers[6] = (rnd.nextInt(4)+1)*25;
		System.out.println(numbers[6]);
		System.out.println();
		System.out.print("Duration: ");
		for (int i = 30; i >=0; i--) {
			System.out.print(" "+i);
			Thread.sleep(100);
		}
		System.out.println();
		System.out.println("------------------------------------");		
	

	}
	public Stack prefix() {
		String inpt="1+6*9/4*(75-9)";

		String[] convert = new String[20];
		int j=0, k=0;

		while (k<inpt.length()-1) {
			while(!inpt.substring(k,k+1).equals("*") && !inpt.substring(k,k+1).equals("+")&& !inpt.substring(k,k+1).equals("-")&&!inpt.substring(k,k+1).equals("/")&&!inpt.substring(k,k+1).equals("(")&&!inpt.substring(k,k+1).equals(")"))
			{	
				convert[j]+= inpt.substring(k,k+1); k++;
			}
			j++;
			if(inpt.substring(k,k+1).equals("*") || inpt.substring(k,k+1).equals("+") || inpt.substring(k,k+1).equals("-") || inpt.substring(k,k+1).equals("/") || inpt.substring(k,k+1).equals("(") || inpt.substring(k,k+1).equals(")"))
			{	
				convert[j]+= inpt.substring(k,k+1); k++;j++;
			}
		}

		Stack stcTemp= new Stack(convert.length);
		for (int i = 0; i < convert.length; i++) {
			if(convert[i]!=null) {
				System.out.print(" "+ convert[i].substring(4));
				stcTemp.Push(convert[i].substring(4));	
			}
			
		}
		return stcTemp;
	}
	
}
