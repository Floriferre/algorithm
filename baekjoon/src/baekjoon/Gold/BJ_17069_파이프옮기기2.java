package baekjoon.Gold;

import java.io.*;
import java.util.StringTokenizer;

public class BJ_17069_파이프옮기기2 {
	
	static int n;
	static int[][] map;
	static long[][][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		dp = new long[n+1][n+1][4];
		
		StringTokenizer st =null;
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		dp[1][2][1] =1;  //초기 파이프 가로방향 위치
		for(int i=1;i<n+1;i++) {
			for(int j=3;j<n+1;j++) {
				if(map[i][j] ==1) continue;
				
				//가로
				dp[i][j][1] = dp[i][j-1][1]+dp[i][j-1][2]; 
				//세로
				dp[i][j][3] = dp[i-1][j][2] + dp[i-1][j][3]; 
				//대각선
				if(map[i][j-1] ==0 && map[i-1][j] ==0)  {
					dp[i][j][2] = dp[i-1][j-1][1] + dp[i-1][j-1][2] + dp[i-1][j-1][3];
				}
				
					
			}
		}
		System.out.println(dp[n][n][1]+dp[n][n][2]+dp[n][n][3]);
	}
}