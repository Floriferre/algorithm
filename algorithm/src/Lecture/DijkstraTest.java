package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DijkstraTest {

	static class Node {
		int vertex, weight; // 관계를 맺고 있는 타정점 정보
		Node next; // 연결 리스트 유지를 위한 다음 노드 참조

		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", next=" + next + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());	// 시작 정점
		int end = Integer.parseInt(st.nextToken());
		
		
		Node [] adjList = new Node[V]; // 인접 리스트
		int [] distance = new int [V]; // 시작점에서 자신으로 오는 최단 거리
		boolean [] visited = new boolean[V]; // 방문 처리 리스트

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		final int INF = Integer.MAX_VALUE;
		Arrays.fill(distance, INF);
		
		distance[start] = 0;
		int min = 0, stopOver = 0;
		for (int i = 0; i < V; ++i) {	// 모든 정점을 다 처리할 때까지 반복
			// step1 : 미방문 정점 중 출발지에서 가장 가까운 정점을 경유지로 선택 
			stopOver = -1;
			min = INF;
			for (int j = 0; j < V; ++j) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					stopOver=j;
				}
			}
			if(stopOver==-1) break; // 경유지 못 찾았으면 끝내기
			
			// step2 : 경유지 찾았으면 방문처리
			visited[stopOver] = true;
			// 상황에 따라 처리 : 경유지 = 도착지면 끝내기(출발지에서 모든 정점으로의 최단거리를 구할시에는 break하지 말 것!)
			if(stopOver == end) break;
			
			// step3 : 경유지를 이용하여 미방문 정점들의 출발지에서 자신으로의 최소 비용 고려
			for(Node temp = adjList[stopOver]; temp != null; temp = temp.next) {
				// 해당 정점이 방문정점이 아니고 현재 정점에서 갈 수 있는 정점인 경우
				// 최소 거리 정점을 거쳐서 해당 정점을 갈 경우의 토탈 가중치와 기존까지 계산된 해당 정점까지의 토탈 가중치를 비교하여 최소값을 만족하는 
				if(!visited[temp.vertex] && distance[temp.vertex] > min + temp.weight) {
					distance[temp.vertex] = min + temp.weight;
				}
			}	
		}
		// 
		System.out.println(distance[end] != INF? distance[end] : -1);
		
		
	}

}
