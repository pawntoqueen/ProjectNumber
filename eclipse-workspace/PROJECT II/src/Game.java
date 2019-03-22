import java.util.Random;

public class Game {
	
	
	public void printGameScreen() throws Exception
	{
		Random rnd= new Random();
		int round=1;
		System.out.println("--------------Round " + round+"--------------\n");
		int target_number= rnd.nextInt(899)+100;
		System.out.println("Target Number : "+ target_number);
		int[] numbers = new int[7]; 
		System.out.println();
		System.out.print("Numbers: ");
		for (int i = 0; i < 5; i++) {
			numbers[i] = rnd.nextInt(8)+1;
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
	
	
}
