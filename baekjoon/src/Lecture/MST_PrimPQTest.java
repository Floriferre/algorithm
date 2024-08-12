package Lecture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MST_PrimPQTest {

	static class Vertex implements Comparable<Vertex>{
		int no, weight;	// 정점번호, 트리정점과 연결했을 떄의 간선 비용 

		/**
		 * @param no
		 * @param weight
		 */
		public Vertex(int no, int weight) {
			super();
			this.no = no;
			this.weight = weight;
		}		
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, adjMatrix[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(st.nextToken());
		adjMatrix = new int[V][V];
		
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방문 정점 (트리 정점 표시)
		boolean [] visited = new boolean[V];
		int [] minEdge = new int[V];	// 자신과 트리의 정점들 간 최소 간선 비용
		
		PriorityQueue<Vertex> pQue = new PriorityQueue<>();
		
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);	// 최소값 갱신 위해 큰 값으로 세팅 
		minEdge[0] = 0;	//임의의 0정점을 트리 구성의 시작으로 하기 위해 세팅
		pQue.offer(new Vertex(0, minEdge[0]));
		
		int result = 0; // 최소 신장 트리 비용
		int min = 0, minVertex = 0, cnt = 0;	// 임시 최솟값
		
		while (!pQue.isEmpty()) {
			//step1 : 미방문(비트리) 정점 중 최소간선비용의 정점을 선택 
			Vertex current = pQue.poll();
			minVertex = current.no;
			min = current.weight;
			
			if(visited[minVertex]) {
				continue;	//먼저큐에 들어있던 것 뿐이므로 무시 
			}
			
			// step2 : 트리 정점에 추가
			visited[minVertex] = true;	// 방문처리 (BFS와 방문처리 하는 곳이 좀 다르다! 이거 명심할 것!)
			result += min;	// 신장 트리 비용 누적 
			if(++cnt == V) break;
			
			
			// step3 : 트리에 추가된 새로운 정점 기준으로 비트리 정점과의 간선 비용 고려
			for (int i = 0; i < V; i++) {
				if(!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {	// 트리에 추가되지 않은 비트리 정점이면서, minvertes와 i정점이 연결되어있으면
					minEdge[i] = adjMatrix[minVertex][i];
					pQue.offer(new Vertex(i, minEdge[i]));
				}
			}
			
		}
		System.out.println(result);
	}

}











