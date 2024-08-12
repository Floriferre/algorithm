package baekjoon.Gold;

/*
@author 정여민
@since 2024-04-17, 수, 14:0
@see https://www.acmicpc.net/problem/1197
@git
@youtube
@performance
@category #
@note

	<문제>
	그래프가 주어졌을 때, 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오
	* 최소 스패닝 트리 : 주어진 모든 그래프의 정점을 연결하는 부분 그래프 중에서 그 가중치의 합의 최소인 트리

	<입력>
	첫재줄 : 정점 개수 V 간선 개수 E (1 ≤ V ≤ 10,000) (1 ≤ E ≤ 100,000)
	E개 줄 : 각 간선에 대한 정보 나타내는 세 정수 A, B, C
			A와 B 정점이 가중치 C로 연결되어있음
			(가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은)

	<출력>
	MST의 가중치 출력

	<풀이>
	MST를 구하는 방법엔 프림, 크루스칼 알고리즘

	** 프림 알고리즘 **
	시작 정점을 기준으로 가장 작은 간선과 연결된 정점을 선택하여 모든 노드를 연결
	1. 임의의 간선 선택
	2. 현 점정으로부터 가장 낮은 가중치를 갖는 정점 선택
	3. 모든 간전에 대해 반복

	참고 : https://velog.io/@jodawooooon/Java-BOJ-1197-%EC%B5%9C%EC%86%8C-%EC%8A%A4%ED%8C%A8%EB%8B%9D-%ED%8A%B8%EB%A6%AC-MST

	** 크루스칼 알고리즘 ** (유니온 파인드)
	간선의 가중치의 합이 최솟값이 되도록 모든 노드를 연결
	1. 간선 데이터를 오름차순 정렬
	2. 간선을 확인하며 현 간선이 사이클을 발생시키는지 확인
		* 발생시키지 않으면 MST에 포함
	3. 모든 간선에 대해 반복


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_1197_최소스패닝트리 {

	static int V;    // 정점 개수
	static int E;    // 간선 개수

	public static int[] parent;

	static ArrayList<Node> nodeList;

	public static void make() {
		// parent 만들기
		parent = new int[V + 1];

		// parent 초기화
		for (int i = 0; i < V + 1; i++) {
			parent[i] = i;
		}
	}

	// union find
	public static int find(int x) {
		if (parent[x] == x) {    // x의 부모가 자기자신일 때 리턴
			return x;
		}
		return parent[x] = find(parent[x]);    // x의 부모가 자기자신이 아니면, 어떤 녀석인지 찾아서 업데이트 (패스 압축)
	}

	public static boolean union(int x, int y) {
		int parentX = find(x);
		int parentY = find(y);

		if (parentX == parentY) {    // 두 정점이 같은 그룹이면
			return false;
		}
		parent[parentX] = parentY;    // X의 부모값을 Y의 부모로 업데이트 (두 개를 같은 그룹으로 만들기)
		return true;
	}

	// Node 만들기
	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		nodeList = new ArrayList<>();

		// 각 간선 정보
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			nodeList.add(new Node(a, b, c));
		}

		Collections.sort(nodeList);    // cost 순으로 노드 정렬

		make();

		int sum = 0;
		int count = 0;

		for (Node n : nodeList) {
			if (union(n.from, n.to)) {    // 연결 가능하고 아직 연결 안 했으면 연결
				sum += n.cost;
				count++;

			}
		}

		System.out.println(sum);

	}
}
