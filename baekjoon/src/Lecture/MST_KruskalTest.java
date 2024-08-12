package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MST_KruskalTest {

	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		/**
		 * @param from
		 * @param to
		 * @param weight
		 */
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
		
		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight;
			return Integer.compare(this.weight, o.weight);	// 음수인 상황이 있을까봐 (오버플로우 방지)
		}
		
	}
	static Edge[] edgeList;
	static int V, E;
	static int [] parents;
	
	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(a == parents[a]) return a;
		
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;	// 사이클이 발생샜다는 의미 
		
		parents[bRoot] = aRoot;	// 랭크 관리 넣어서 랭크 낮은 걸 높은 거에 붙이는 거 꼭 해보기 
		return true;
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		// 간선리스트를 가중치 기준 오름차순 정렬
		Arrays.sort(edgeList);
		
		// V개의 정점으로 make set 작업
		make();
		
		int result = 0; // MST 비용
		int count = 0; 	// 연결된 간선 개수
		for (Edge edge : edgeList) {	// 비용이 작은 간선 순으로 꺼내서 처리 
			if(union(edge.from, edge.to)) {
				result += edge.weight;
//				System.out.println(result);
				if(++count == V-1) break;
			}
		}
		System.out.println(result);
	}

}

























