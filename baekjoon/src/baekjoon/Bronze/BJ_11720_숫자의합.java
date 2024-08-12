package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11720_숫자의합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		int sum = 0;
		st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		
		
		for(int i = 0 ; i < N; i++) {
			sum += str.charAt(i) - '0';
		}
		
		System.out.println(sum);
	}

}
