package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 1.
@see https://www.acmicpc.net/problem/15649
@git
@youtube
@performance 메모리 81272KB 시간 2560ms
@category #완전탐색, 순열
@note 
	자연수 N과 M
		1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열 -> 순열 문제!
		1~N까지 배열을 하나 만들고, 길이가 M인 순열을 모두 구하자! 

*/



public class BJ_S3_15649_N과M {
	static int src [] ;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		src = new int [N];
		for (int i = 0; i < N; i++) {
			src[i] = i+1;
		}
		
		perm(0, new int [M], new boolean[N]);
		
	}
	
	static void perm(int nthChoice, int [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
//			System.out.println(Arrays.toString(choosed));
			
			for(int i = 0; i < choosed.length; i++) {
				System.out.print(choosed[i] + " ");
			}
			System.out.println();
			
			return;
		}
	
		// 재귀 처리
		for(int i = 0; i < src.length; i++) {
			// 방문한 적 없으면
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				perm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}

}
