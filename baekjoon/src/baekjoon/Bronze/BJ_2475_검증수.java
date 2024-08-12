package baekjoon.Bronze;

import java.util.Scanner;

public class BJ_2475_검증수 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int x = 0;
		
		long result = 0;
		
		for(int i = 0 ; i < 5; i++) {
			x = sc.nextInt();
			result += x*x;
		}
		
		System.out.println(result%10);
		
		
		
	}

}
