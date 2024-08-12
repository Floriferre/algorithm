package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 24.
@see https://www.acmicpc.net/problem/17281
@git
@youtube
@performance
@category #
@note 
*/

public class BJ_17281_야구 {
	
	static int [] players = {1, 2, 3, 5, 6, 7, 8, 9};	// 4번타자는 늘 1타석에 섬
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());	// 이닝 수
		
		for(int n = 0; n < N; n++) {
			
		}
	}
	
	
	public static void permutation(int nthChoice, int [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {	// 슨열 생성 완료
			System.out.println(Arrays.toString(choosed));
			// 항상 1타는 4번 타자임 
			
			ArrayDeque<Integer []> que = new ArrayDeque<>();
			int out = 0; // 아웃 갯수 셀 것
			
			
			
			
			return;
		}	
		for(int i = 0; i < players.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = players[i];
				permutation(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}

}
