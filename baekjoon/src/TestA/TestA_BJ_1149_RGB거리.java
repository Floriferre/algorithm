package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TestA_BJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		int [][] dp = new int[N][5];
		int [][] map = new int[N][5];
		
		for(int i[]:dp) {
			Arrays.fill(i, Integer.MAX_VALUE);
		}
		
		for(int i=0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[i][1] = r;
			map[i][2] = g;
			map[i][3] = b;
		}
		dp[0][1] = map[0][1];
		dp[0][2] = map[0][2];
		dp[0][3] = map[0][3];
		
		for(int i=1; i<N; i++) {
			dp[i][1] = Math.min(dp[i-1][2] + map[i][1], dp[i-1][3] + map[i][1]);
			dp[i][2] = Math.min(dp[i-1][1] + map[i][2], dp[i-1][3] + map[i][2]);
			dp[i][3] = Math.min(dp[i-1][1] + map[i][3], dp[i-1][2] + map[i][3]);
		}
//		
//		for(int []i: dp) {
//			System.out.println(Arrays.toString(i));
//		}
		
		System.out.println(Math.min(dp[N-1][1], Math.min(dp[N-1][2], dp[N-1][3])));

	}

}
