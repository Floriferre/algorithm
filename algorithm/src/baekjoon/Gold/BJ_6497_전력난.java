package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 23.
@see https://www.acmicpc.net/problem/6497
@git
@youtube
@performance
@category #
@note 
*	가로등 소등. 미터수 만큼 돈을 절약 가능
*	그러나 두 집을 왕래할 때 불이 켜져 있지 않으면 X
*
*	<입력>
*	테스트케이스 여러개	
*	집의 수 m 길의 수 n (1 ≤ m ≤ 200,000, m-1 ≤ n ≤ 200,000)
*	n개의 줄, 길에 대한 정보 x, y, z : x번째 집과 y번째 집 사이에 양방향 도록 z미터
*	도시는 연결 그래프 형태, 도시상의 모든 길의 거리 합 < 2^31
*	입력의 끝에는 첫 줄에 0 2개 
*
*
*/

public class BJ_6497_전력난 {

	static class Edge implements Comparable<Edge> {
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
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int m, n;
//	static LinkNode[] graph;

	static int[] parents;
	static Edge[] edgeList;

	static void make() {
		parents = new int[m];
		for (int i = 0; i < m; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			if (m == 0) {
				break;
			}

			edgeList = new Edge[n];
			
			int sum = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				sum += z;
				edgeList[i] = new Edge(x, y, z);
			}
			
			Arrays.sort(edgeList);
			
			make();
			
			int result = 0; 	// MST 비용
			int count = 0; 	// 연결된 간선 개수
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					result += edge.weight;
					if(++count == m-1) break;
				}
			}
			sb.append(sum - result + "\n");
		}
		System.out.println(sb);
	}

}
