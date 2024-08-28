package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import org.xml.sax.InputSource;

/*
@author 정여민
@since 2023. 10. 12.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	N명의 사람
*	서로 아는지 아닌지 정보가 주어짐
*	몇다리 건너서 알고 있으면 같은 무리
*	총 몇 개의 무리가 있는가? 
*
*	<입력>
*	첫째줄 : 테스트케이스개수 T
*	TC1 : 사람 수 N, 서로 알고 있는 관계의 수 M
*	M개의 줄 : 서로 알고 있는 두 사람의 정보 
*
*	<출력>
*	무리의 개수 출력
*
*	<풀이>
*	DFS로 풀어보자 DFS 요새 너무 안 써서 해야해
*
*	서로 알고 있는 사람들 입력 받을 때 나는 0베이스를 쓸 거니까 그거 고려해서 입력 받기 
*
*	
*
*
*/


public class TestA_SWEA_7465_창용마을무리의개수DFS {

	static int N, M;
	static int result;
	
	static int graph[][];
	static boolean [] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t <= T; t++) {
			result = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			graph = new int [N][N];
			
			for(int [] i: graph) {	// 그래프 0으로 초기화 
				Arrays.fill(i, 0);
			}
			
			for(int i=0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) -1;
				int b = Integer.parseInt(st.nextToken()) -1;
				graph[a][b] = graph[b][a] = 1;
			}
			
			visited = new boolean[N];
			for(int i=0; i <N; i++) {
				if(!visited[i]) {
					dfs(i);
					result++;
				}
			}
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int start) {
		visited[start] = true;
		
		for(int i=0; i<N; i++) {
			if(!visited[i] && graph[start][i] == 1) {	// 연결 정보가 있으면
				dfs(i);
			}
		}
	}

}
