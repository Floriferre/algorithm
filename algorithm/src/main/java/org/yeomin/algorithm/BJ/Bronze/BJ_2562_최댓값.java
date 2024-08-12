package baekjoon.Bronze;

import java.util.Scanner;

public class BJ_2562_최댓값 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr;
		int index = 0;
		
		Scanner sc = new Scanner(System.in);
		
		int result = Integer.MIN_VALUE;
		for(int i = 0; i < 9; i++) {
			arr = sc.nextInt();
			if(result < arr) {
				result = arr;
				index = i+1;
			}
			
			
		}
		System.out.println(result);
		System.out.println(index);
	}

}
