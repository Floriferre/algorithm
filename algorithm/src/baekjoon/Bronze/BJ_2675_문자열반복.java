package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2675_문자열반복 {

	public static void main(String[] args) throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb  = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for (int i = 0 ; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			for(int s = 0; s < str.length(); s++) {
				for(int j = 0; j < n; j++) {
					System.out.print(str.charAt(s));
				}
			}
			System.out.println();
			
		}
		
		
	}

}
