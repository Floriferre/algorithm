package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_8958_OX퀴즈 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0 ; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int sum = 0;
			int num = 0;
			for(int i = 0; i < s.length(); i++) {
				if(s.charAt(i) == 'O') {
					num++;
					sum += num;
				}else {
					num = 0;
				}
			}
			sb.append(sum + "\n");
		}
		System.out.println(sb);

	}

}
