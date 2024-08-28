package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 25.
@see https://www.acmicpc.net/problem/1753
@git
@youtube
@performance
@category #
@note 
	<문제>
	방향 그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
	모든 간선의 가중치는 10 이하의 자연수다.
	=> 다익스트라 써보자!

	<입력>
	첫째줄 : 정점 개수 V, 간선 개수 E (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 
	둘째줄 : 시작 정점 번호 K(1<=K<=V)
	셋째줄 ~ E개의 줄 : 각 간선을 나타내는 세 개의 정수 (U, v, w) u->v : w
	u != v, 1<= w <=10
	서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음
	
	<출력> 
	첫째줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력
	시작점 자신은 0으로 출력, 경로가 존재하지 않는 경우은 INF 출력
	
	<풀이>
	시작점 ~ 모든 정점으로의 최단 경로? -> 오늘 배운 다익스트라 알고리즘을 써보자! 

*/

public class BJ_1753_최단경로 {

	static class Node{
		int vertex, weight;
		Node next;
		/**
		 * @param vertex
		 * @param weight
		 * @param next
		 */
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());	// 정점의 개수
		int E = Integer.parseInt(st.nextToken());	// 간선의 개수
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); 	// 시작 정점의 번호
		
		Node [] adjList = new Node[V+1];	// 간선 정보 저장
		int [] distance = new int [V+1];	// 시작점에서 자신으로 오는 최단 거리
		boolean [] visited = new boolean[V+1]; // 방문 처리 
		
		for (int i = 0; i < E; i++) {	// E개의 간선 정보 입력 받기 (u, v, w) 형태
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u] = new Node(v, w, adjList[u]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);	// 초기 거리 무한대로 세팅
		
		distance[S] = 0; 	// 시작 정점의 거리 0으로 초기화
		int min = 0, stopOver = 0;	// 거리 최솟값 잴 변수, 더이상 갈 곳 없는 지 체크할 변수
		for (int i = 0; i < V; i++) {	// 모든 정점을 다 처리할 때까지 반복
			// step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택
			stopOver = -1;
			min = INF;
			for (int j = 1; j < V+1; j++) {
				if(!visited[j] && min > distance[j]) {	// 아직 방문하지 않은 곳인데 최솟값 갱신이 가능하면
					min = distance[j];
					stopOver = j;
				}
			}
			
			if(stopOver == -1) break;	// 경유지 못 찾았으면 끝내기
			
			// step2 : 경유지 찾았으면 처리
			visited[stopOver] = true;
			
			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소 비용 고려
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				// 해당 정점이 방문 정점이 아니고, 현재 정점에서 갈 수 있는 정점인 경우
				// 최소 거리 정점을 거쳐서 해당 정점을 갈 경우의 토탈 가중치와 기존까지 계산된 해당 정점의 가중치 비교하여 최솟값 업데이트
				if(!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}
		}
		
		for (int i = 1; i < distance.length; i++) {
			sb.append(distance[i] != INF? distance[i] : "INF").append("\n");
		}	
		System.out.println(sb);
	}

}


































