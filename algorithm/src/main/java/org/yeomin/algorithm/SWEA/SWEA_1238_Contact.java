package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 23.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15B1cKAKwCFAYD
@git
@youtube
@performance
@category #
@note 
	비상연락망. A->B에게 연락. 연락을 받은 사람은 동시에 자신이 연락할 수 있는 사람에게 연락. 
	연락이 끝났을 때, 마지막으로 연락을 받은 사람 중 가장 큰 번호를 가진 사람은? 
	
	<Solution>
	1. 방향 그래프, 연락이 있을 수 있고 없을 수 있음.
	2. 인접리스트와 인접행렬 중 무엇을 사용할 것인가? -> 연락 인원이 최대 100명이니까 인접행렬을 써도 될 것 같다!  
	3. 그래프 탐색을 하자 -> DFS와 BFS 중 어떤 것을 사용할 것인가?
					-> 같은 depth끼리 탐색하는 BFS가 좀 더 나을 것 같다! 
	
	<제약 사항> 
	1. 연락 인원은 최대 100명, 부여될 수 있는 번호는 1~100
		단, 중간에 비어있는 번호가 있을 수 있음!
	2. 한 사람이 다수의 사람에게 연락이 가능한 경우 동시에 전달
	3. 한 번 연락을 받은 사람에게 다시 연락을 하는 일이 없다 <- visited 배열 사용해서 방문한 곳은 다시 안 가게 하자!
	4. 연락 받을 수 없는 사람이 있을 수도.
	
	
	<입력> 
	* 10개의 테스트 케이스
	TC1 : 입력받는 데이터 길이, 시작점
	TC2 : 입력받는 데이터 {from to from to...}의 형태 
			동일한 쌍이 들어올 수 있음. 입력받는 데이터는 순서 상관 없음! 	


*/

public class SWEA_1238_Contact {
	static int L, Start;	// 입력 데이터 길이 L, 연락 시작점 Start
	
	static int [][] graph;	// 비상 연락망 상태 저장할 배열
	static boolean [] visited;	// 방문여부 저장할 배열 
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 총 10개의 테스트 케이스
		for(int i = 1; i < 11; i++) {
			st = new StringTokenizer(br.readLine());	// 입력 처리
			L = Integer.parseInt(st.nextToken());	// 입력받는 데이터의 길이
			Start = Integer.parseInt(st.nextToken());	// 연락 시작점
			
			graph = new int [101][101];	// 1 base index 이므로
			visited = new boolean[101];	// 1 base index 이므로 
			// 그래프에 비상 연락망 정보 저장 
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < L/2; j++) {	// 연락 상태가 from to 두 개씩 들어오니까 입력 길이의 절반만큼 수행
				int from = Integer.parseInt(st.nextToken());	// 누가 연락하나?
				int to = Integer.parseInt(st.nextToken());	// 누구에게로? 
				graph[from][to] = 1;	// 방향 그래프이므로 하나만 저장
			}
			sb.append("#" + i);	// 출력 형식 맞추기
			BFS(Start, graph);	// DFS 돌면서 제일 마지막으로 연락한 받은 사람 중 가장 큰 번호를 가진 사람 찾기 
			
		}
		System.out.println(sb);	// 결과 한 번에 모아서 출력 
		
		
	}
	
	// BFS를 써보자. 얘는 좀 귀엽게 생겼다 
	static void BFS(int start, int [][] adjMatrix) {	// 시작점, 그래프 정보 입력 받기
		Queue<Integer> que = new ArrayDeque<>();	// 큐 선언
		ArrayList<Integer> list = new ArrayList<>();	// 각 레벨 별 최댓값을 저장해둘 리스트
		
		que.offer(start);	// 큐에 시작점 넣기
		visited[start] = true;	// 큐에 넣은 것은 방문처리
		
		int max = 0;	// 번호가 제일 큰 사람
		
		while(!que.isEmpty()) {	// 큐가 비어있지 않으면 계속 탐색 진행
			int tempSize = que.size();	// depth별로 체크하기 위해 현재 큐의 사이즈만큼만 돌릴 것.
			max = 0;	// 번호가 제일 큰 사람 체크하기 위해 max값 초기화
			while(tempSize-- > 0) {	// 해당 level 돌기
				int current = que.poll();	// 큐에서 꺼내서
				for(int i = 1; i < 101; i++) {	// 1~100번까지 사람들 중 
					if(adjMatrix[current][i] == 1 && !visited[i]) {	// 연락 가능한 사람이 있고, 아직 연락을 한 상태가 아니면
						que.offer(i);	// 큐에 넣고
						visited[i] = true;	// 방문처리
						max = Math.max(max, i);	// 번호가 제일 큰 사람 업데이트 
					}
				}
			}
			list.add(max);	// 리스트에 해당 depth에서 번호가 제일 큰 사람 넣기 
		}
		// 제일 마지막에 연락 받은 사람 중 가장 큰 사람은 어떻게 구할 것인가? 
		// 왜냐면 지금의 방법으로는 제일 마지막에 탐색한 것도 max가 0으로 저장이 되어버린다 
		// 리스트에 저장된 것 중 첫 번째가 아닌, 두 번째 것이 제일 마지막 depth의 최대 번호 
		sb.append(" ").append(list.get(list.size()-2)).append("\n");
	}
}
