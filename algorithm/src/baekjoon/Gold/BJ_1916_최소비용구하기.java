package baekjoon.Gold;

/*
@author 정여민
@since 2024-04-16, 화, 15:5
@see https://www.acmicpc.net/problem/1916
@git
@youtube
@performance
@category #
@note
	<문제>
	N개의 도시. 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스.
	A번째 도시에서 B번째 도시까지 가는데 드는 최소 비용을 출력.
	도시 번호는 1~N

	<입력>
	첫째줄 : 도시의 개수 N (1 ≤ N ≤ 1,000)
	둘째줄 : 버스의 개수 M (1 ≤ M ≤ 100,000)
	셋째줄 ~ M+2줄 : 출발도시 도착도시 버스비용
		0 <= 버스비용 < 100,000
	M+3번째 줄 : 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호

	<출력>
	출발 ~ 도착까지 드는 최소 비용

	<풀이>
	최소비용을 출력하는 것이므로 다익스트라 혹은 벨만포드 알고리즘이 유용할 듯
	하지만 나는 플로이드 워셜로 풀어볼테야

	* 플로이드 워셜 알고리즘 *
	한 지점에서 다른 모든 지점까지의 거리를 구하는 알고리즘
	1) 2차원 list 초기값을 무한대로 초기화
	2) 스스로에게 가는 건 0으로 초기화
	3) 간선 정보를 입력받아 해당 값 update
	4) 플로이드 워셜 알고리즘 수행
		* for k
			for a
				for b
					graph[a][b] = graph[a][k] + graph[k][b]
	=> 하지만 시간 초과가 나죠? ㅎㅎ

	* 다익스트라 알고리즘 *
	최단 경로를 찾는 알고리즘. 음의 가중치는 X
	1) 아직 방문하지 않은 정점 중 출발지로부터 가장 거리가 짧은 정점을 방문
	2) 해당 정점을 거쳐 갈 수 있는 정점의 거리가 이전 기록한 값보다 작으면 갱신
	* pq : 우선순위 큐로 정점과 출발지에서 점정까지 가는 최소 거리 저장
	* check : boolean 배열 - 해당 정점을 방문하는지 체크
	* dist : int 배열 - 출발지에서 최소거리를 기록

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1916_최소비용구하기 {

	// 우선순위 큐에 정점번호 + 가중치 저장을 위해 만듦
	public static class Node implements Comparable<Node> {
		// 정점, 비용
		int index;
		int cost;

		// 정점 번호, 가중치 저장
		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		// 가중치를 중심으로 우선순위가 정해짐
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());    // 도시의 개수
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());// 버스 개수

		//  인접 행렬 초기화
		int[][] matrix = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j) {
					matrix[i][j] = 0;
				} else {
					matrix[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		// 큐에 최소 거리 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			matrix[a][b] = Math.min(matrix[a][b], cost);    // 이 부분이 중요
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		// 정점 번호가 1부터 시작하므로 시작 인덱스 맞춰주기
		boolean[] visit = new boolean[N + 1];
		int[] dist = new int[N + 1];

		// 거리 초기값 무한대로 설정
		Arrays.fill(dist, Integer.MAX_VALUE);
		// 다익스트라 알고리즘
		// 정점과 출발지에서 정점까지 가는 최소 거리 저장할 우선 순위 큐
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		dist[start] = 0;    // 시작정점으로부터의 거리 0
		pq.offer(new Node(start, dist[start]));    // 가장 처음 시작 정점 넣기

		while (!pq.isEmpty()) {
			Node current = pq.poll();    // 현재 노드 꺼내기

			// 현재 노드에 방문한 적 있다면
			if (visit[current.index]) {
				continue;
			}

			// 현재 노드 방문하지 않았다면 방문
			visit[current.index] = true;

			// 인접한 것들 거리 업데이트
			for (int i = 1; i < N + 1; i++) {
				// 아직 방문한 적이 없고, 갈 수 있는 정점이면
				if (matrix[current.index][i] != Integer.MAX_VALUE && !visit[i]
					&& dist[i] > dist[current.index] + matrix[current.index][i]) {    // 거리를 업데이트 할 수 있으면
					dist[i] = dist[current.index] + matrix[current.index][i];    // 거리 덥데이트
					pq.offer(new Node(i, dist[i]));    // 다음 정점 큐에 넣기
				}
			}

		}

		System.out.println(dist[end]);

		// int[][] City = new int[N][N];    // 간선 정보 저장
		//
		//
		// // 3. 간선 정보 입력받아 해당 값 업데이트
		// for (int i = 0; i < M; i++) {
		// 	st = new StringTokenizer(br.readLine());
		// 	int a = Integer.parseInt(st.nextToken()) - 1;    // 출발 도시
		// 	int b = Integer.parseInt(st.nextToken()) - 1;    // 도착 도시
		// 	int cost = Integer.parseInt(st.nextToken());    // 비용
		//
		// 	City[a][b] = cost;
		// }
		//
		// // 4. 플로이드 워셜 알고리즘 돌리기
		// for (int k = 0; k < N; k++) {
		// 	for (int a = 0; a < N; a++) {
		// 		for (int b = 0; b < N; b++) {
		// 			City[a][b] = Math.min(City[a][k] + City[k][b], City[a][b]);
		// 		}
		// 	}
		// }
		//
		// st = new StringTokenizer(br.readLine());
		// int start = Integer.parseInt(st.nextToken()) - 1;
		// int end = Integer.parseInt(st.nextToken()) - 1;
		//
		// System.out.println(City[start][end]);

	}
}
