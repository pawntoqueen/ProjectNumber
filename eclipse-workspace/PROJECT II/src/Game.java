import java.util.Random;
import java.util.Scanner;

public class Game {

	public void printGameScreen() throws Exception {
		Random rnd = new Random();
		int round = 1;
		System.out.println("-------------------------------------- Round " + round
				+ " --------------------------------------------");
		int target_number = rnd.nextInt(899) + 100;
		System.out.println("Target Number : " + target_number);
		int[] numbers = new int[7];
		System.out.println();
		System.out.print("Numbers: ");
		for (int i = 0; i < 5; i++) {
			numbers[i] = rnd.nextInt(8) + 1;
			System.out.print(numbers[i] + " ");
		}
		numbers[6] = (rnd.nextInt(4) + 1) * 25;
		System.out.println(numbers[6]);
		System.out.println();
		System.out.print("Duration: ");

		time();

		

		System.out.print("Please enter your operation : ");
		Scanner sc = new Scanner(System.in);
		String operation = sc.next();
		System.out.println();
		System.out.println(
				"--------------------------------------------------------------------------------------------");

	}

	public void time() {
		int countdown = 30;
		long time1 = 0;
		long time2 = System.currentTimeMillis();

		do {

			// bütün islemler burada olacak

			time1 = System.currentTimeMillis();

			if (time1 - time2 >= 50) {
				System.out.print(countdown-- + " ");

				time2 = time1;
			}

		} while (countdown != 0);
		System.out.println("\n");

	}

	

}
