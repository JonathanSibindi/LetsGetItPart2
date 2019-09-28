import java.util.*;
public class Program {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int [][] start = new int[7][7];

		int [][] end = new int[7][7];
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				start[i][j] = in.nextInt();
			}
		}
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				end[i][j] = in.nextInt();
			}
		}
		
		print(end);
	}

	private static void print(int[][] end) {
		// TODO Auto-generated method stub
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				System.out.print(end[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
