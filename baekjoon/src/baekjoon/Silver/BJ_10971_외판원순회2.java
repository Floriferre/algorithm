package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 30.
@see https://www.acmicpc.net/problem/10971
@git
@youtube
@performance	16196KB, 356ms
@category # 브루트포스, 백트래킹, 외판원 순환 문제
@note 
*	<문제>
*	TSP : Traveling Salesman Problem
*	1~N번까지 번호가 매겨진 도시, 도시들 사이에 길(없을 수도 있음)
*	한 도시에서 출발해 N개의 도시를 거쳐 다시 원래의 도시로 돌아오는 순환 경로를 짜려고 한다 
*	이때 가장 적은 비용이 드는 여행 계획
*	
*	<입력>
*	첫째줄 : 도시의 수 N	(2<=N<=10)
*	둘째줄 ~ N : 비용 행렬, 1,000,000이하의 양의 정수, 갈 수 없는 곳은 0으로 
*
*	<출력>
*	최소 비용
*
*	<풀이>
*	순열 + BFS를 돌기에는 너무 빡센데..? 다시 돌아갈 수도 없고 
*	순열 해봐?	
*	뭔가 DP를 활용해야할 것 같긴 하다 근데 어떻게 하지? 
*
*/

public class BJ_10971_외판원순회2 {
	static int N;
	static int [][] W;
	static int min = Integer.MAX_VALUE;
	
	
	static int [][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		W = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0, new int [N], new boolean [N]);
		
		System.out.println(min);
		
	}
	
	private static void perm(int nthChoice, int [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			int sum = 0;
			for(int i=0; i < choosed.length -1; i++) {
				if(W[choosed[i]][choosed[i+1]] != 0) {	// 갈 수 있는 곳이면
					sum+=W[choosed[i]][choosed[i+1]];
				}else {
					return;
				}
			}
			if(W[choosed[N-1]][choosed[0]] == 0) return;	// 돌아가는 경로 없으면 패스
			sum += W[choosed[N-1]][choosed[0]];
			
			min = Math.min(min, sum); 
			
//			System.out.println(Arrays.toString(choosed));			
			
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = i;
				perm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	// DP + 비트마스킹으로 풀어보자! 
//	private int TSP(int now, int visited) {	// 현재 방문한 노드 번호, 몇개의 도시 방문했는지 
//		
//		// 모든 노드를 방문했는데
////		if(visited == end)
//	}
//	
	
	
	
	
	
}
