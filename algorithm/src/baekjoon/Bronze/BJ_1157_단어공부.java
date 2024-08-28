package baekjoon.Bronze;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BJ_1157_단어공부 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		
		str = str.toUpperCase();
		
		// A to Z : 65가 A
		int [] alpha = new int [26];
		
		Arrays.fill(alpha, 0);
		


		for(int i = 0; i < str.length(); i++) {
			alpha[(int)str.charAt(i) - 65] += 1;
		}
		
		int max = -1;
		char c = '?';
		
		for(int i = 0; i < 26; i++) {
			if(alpha[i] > max) {	// 더 큰 값이 나오면 업데이트
				max = alpha[i];
				c = (char)(i+65);
			}else if(alpha[i] == max) {	// 같은 빈도수가 있으면 ?로 만들기
				c = '?';
			}
		}
		
		System.out.println(c);
		
		
		
		
		
//		int check = 0;
//		boolean ch = false;
//		for(int i = 1; i < alpha.length; i++) {
//			if(alpha[i] > alpha[i-1]) {
//				check = i;
//			}
//
//		}
		
//		System.out.println((char)(check+65));

	}

}
