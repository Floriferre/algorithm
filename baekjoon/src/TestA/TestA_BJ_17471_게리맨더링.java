package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 11.
@see
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	N개의 구역, 1~N까지 번호, 두 개의 선거구 => 부분집합
*	선거구는 연결되어야함
*	인구 차이의 최솟값을 구해보자
*
*	<입력>
*	첫째줄 : 구역의 개수 N
*	둘째줄 : 인구 1 ~ N번 순서대로 주어짐 => 배열로 받자
*	N개의 줄 : 각 구역과 인접한 구역의 정보 
*			첫번째 정수 : 인접한 구역의 수
*			이후 : 인접한 구역 번호 
*			A와 B가 입접하면 B와 A도 인접하다 
*
*	<출력>
*	두 선거구 인구 차이의 최솟값 출력
*	두 선거구로 나눌 수 없는 경우에는 -1출력
*
*	<제한>
*	2 <= N <= 10
*	1 <= 구역의 인구 수 <= 100
*
*
*	<풀이>
*	1. 선거구 나누기 => 부분집합
*		1-1. nth = 선거구 N개 
*			- A나 B에 몰빵되는 경우 없게 (사실 이래도 괜찮은게 인구 차이 최솟값이라서)
*			- BFS 돌려서 A구역 B구역 전부 연결되어있는지 찾기
*			- 연결되었으면 인구수 차이 최솟값 업데이트  (bfs 돌렸는데 횟수가 2면)
*			- 리턴 꼭 할 것!
*		1-2. 선거구 할당
*			status[nth] = true
*			status[nth] = false
*
*	2. BFS
*		방문 X, 연결 정보가 있으면 방문하기 
*		연결 정보 저장해두고 i랑 연결된 원소들 죄다 큐에 넣기
*
*/

public class TestA_BJ_17471_게리맨더링 {

	
	static int N;
	static int [] population;
	static boolean [] visited;
	static int [][] map;
	
	static boolean [] status;
	
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		population = new int[N];	// 인구 정보 입력 받음
		for(int i =0;i < N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연결 정보 입력 받기 (난 행렬로 받아볼래) 0베이스 쓸거니까 그거 주의하기
		map = new int[N][N];
		for(int [] i : map) {	// map 연결 정보 0으로 초기화
			Arrays.fill(i, 0);
		}
		for(int i =0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); 	// 연결정보가 n개
			for(int j = 0; j < n; j++) {
				map[i][Integer.parseInt(st.nextToken())-1] = 1;	// 간선 정보 있으면 1로 바꾸기
			}
		}
		
		// 어후 림들다 입력 끝!
		// 이제 부분집합을 돌립시다
		status = new boolean [N];
		subset(0, status);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);			
		}

	}
	
	private static void subset(int nth, boolean [] status) {
		if(nth == status.length) {
			// BFS 돌리기 - 연결 구역 갯수 찾기
			visited = new boolean[N];
			int check = 0;
			for(int i=0; i < N; i++) {
//				System.out.println(Arrays.toString(visited));
				if(!visited[i]) {	// 방문한 적 없으면 bfs 돌리기
//					System.out.println("여기는 도니?");
					bfs(i, visited);					
					check++; // 연결 구역 갯수 세기
				}
			}
//			System.out.println(check);
			// 연결 구역 개수 2이면 인구차 최솟값 업데이트 
			if(check == 2) {
				// 각 구역 인구수 세기
				int popA = 0;
				int popB = 0;
				
				for(int i=0; i < N; i++) {
					if(status[i] == true) {
						popA += population[i];
					}else {
						popB += population[i];
					}
				}
//				System.out.println("result : " + result);
				result = Math.min(result, Math.abs(popA-popB));
			}
			
			return;
		}
		
		status[nth] = true; // A번 구역 할당
		subset(nth+1, status);
		status[nth] = false; // B번 구역 할당
		subset(nth+1, status);
	}
	
	private static void bfs(int start, boolean [] visited) {
		Queue<Integer> que = new ArrayDeque<>();
		que.offer(start);
		visited[start] = true;
		
		while(!que.isEmpty()) {
			int current = que.poll();
			
			for(int i =0; i < N; i++) {
				if(map[current][i] == 1 && !visited[i] && status[current] == status[i]) {	// 연결 정보가 있고 아직 방문한 적 없으면 
//					System.out.println("current, i" + (current) + " : "+ (i));
					que.offer(i);
					visited[i] = true;
				}
			}
			
		}
	}

}
