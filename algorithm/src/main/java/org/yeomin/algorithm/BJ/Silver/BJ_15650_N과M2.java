package baekjoon.Silver;

import java.util.Arrays;
import java.util.Scanner;

/*
@author 정여민
@since 2023. 8. 2.
@see https://www.acmicpc.net/problem/15650
@git
@youtube
@performance
@category #
@note 
*/

public class BJ_15650_N과M2 {
	
	static int [] src;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// N과 M (2)
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		src = new int [N];
		for(int i = 0; i < N; i++ ) {
			src[i] = i+1;
		}
		comb(0, 0, new int [M]);
		
		
	
	}
	
	static void comb(int nth, int startIndex, int [] choosed) {
		
		if(nth == choosed.length) {
			for(int i = 0; i < choosed.length; i++) {
				System.out.print(choosed[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = startIndex; i < src.length; i++) {
			choosed[nth] = src[i];
			comb(nth+1, i+1, choosed);
		}
		
	}

}
