package baekjoon.Bronze;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_10809_알파벳찾기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		// a 97
		
		int [] count = new int[26];
		Arrays.fill(count, -1);
		
		String s = sc.next();
		
		for(int i = 0; i < s.length(); i++) {
			if(count[(int)s.charAt(i)-97] == -1) count[(int)s.charAt(i)-97] = i;
		}

		for(int c : count) {
			sb.append(c + " ");
		}
		System.out.println(sb);
	}

}
