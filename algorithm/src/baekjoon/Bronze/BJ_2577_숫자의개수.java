package baekjoon.Bronze;

import java.util.Scanner;

public class BJ_2577_숫자의개수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		
		int result = A*B*C;
		
		int [] lst = new int [10];
		
		while(result > 0) {
			lst[result%10] += 1;
			result /= 10;
		}
		
		for(int i = 0; i < 10; i++) {
			System.out.println(lst[i]);			
		}

	}

}
