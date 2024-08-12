package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 23.
@see https://www.acmicpc.net/problem/17471
@git
@youtube
@performance
@category #
@note 
*	게리맨더링 : 선거구 변경 
*	N개의 구역, 2개의 선거구. 
*		각 선거구는 연결되어있어야 한다 <- Union find 가능? 
*	두 선거구에 포함된 인구수 차이 최소로 하려고 한다, 인구 차이의 최솟값? 
*
*	<입력> 
*	첫째줄 : 구역의 개수 N
*	둘째줄 : 인구 1~N까지 순서대로 
*	셋째줄 ~ N 개줄 : 구역, 인접한 구역의 정보 <- 간선 1, 무방향 그래프 
*
*	<출력>
*	두 선거구 인구 차이의 최솟값 출력
*	나눌 수 없는 경우 -1
*
*	<Solution>
*	1. 부분집합
*	2. DFS, BFS
*	3. 인구수 차이 구하기 
*
*
*
*
*/

public class BJ_17471_게리맨더링 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static LinkNode[] area; // 구역에 대한 정보 저장
	
	static LinkNode[] area1, area2;
	

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		area = new LinkNode[N]; // 1 base index를 -> 0으로 바꿀 것이므로

		// 도시별 인구수 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			area[i].people = Integer.parseInt(st.nextToken());
		}

		// 도시별 연결 정보 저장
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			
			// 양방향 그래프
			area[from] = new LinkNode(to, area[from]);
			area[to] = new LinkNode(from, area[to]);
		}
		
		

	}

	// 각 구역은 구역 이름, 인구수, 연결된 부모와 자식에 대한 정보가 있음
	static class LinkNode {
		int name;
		int people;
		LinkNode next;
		LinkNode parent;
		
		public LinkNode(int name, LinkNode next) {
			super();
			this.name = name;
			this.next = next;
		}

	}

//	static void makeSubset(final int nth, boolean[] status) {
//		if (nth == status.length) {
//			// 여기에서 만들어진 서브셋 사용하기 <- 여기에서 선거구 나눠버리자 
//			area1 = new LinkNode[status.length];	// 조합으로 선택된 것 넣기
//			area2 = new LinkNode[N-status.length];	// 선택되지 않은 것 넣기 
//			for(int i = 0, idx1 = 0, idx2 = 0; i < status.length; i++) {
//				if(status[i]) {
//					area1[idx1] = area[i];
//					idx1++;
//				}else {
//					area2[idx2] = area[i];
//					idx2++;
//				}
//			}
//			
//			// 이제 여기에서 DFS 돌리고, 인구수 체크하면 됨!! 
////			dfs(area1, new boolean [area1.length])
//			
//			
//			return;
//		}
//		status[nth] = true;
//		makeSubset(nth + 1, status);
//		status[nth] = false;
//		makeSubset(nth + 1, status);
//	}
	
//	// 연결 되어있는지 체크할 DFS
//	static void dfs(LinkNode node, boolean [] visited) {
//		// 1. 방문처리
//		visited[node.name] = true;
//		
//		// 2. 사용하기 
//		
//		// 3. 다음 자식 찾기
//		for(LinkNode child = area[node.name]; child != null; child = child.next) {
//			if(!visited[child.name]) {	// 방문한 적 없으면
//				child.parent = node;
//				dfs(child, visited);
//			}
//		}
//		
//		
//		
//	}

}
