package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// A형 검정 

/*	<문제>
 * 	N개의 마을을 2개 지역구(지역1, 지역2)로 분리, 지역구 별 대표 선정
 * 	지역구에 포함된 유권자 수 차이가 최소가 되도록!
 * 	간선 : 두 마을이 서로 인접해 있음을 알려줌
 * 	지역구 : 동일한 지역구에 포함된 마을들은 직접 연결 or 다른 마을을 통해 간접 연결
 * 
 * 	N개 마을의 유권자 수와 마을의 인접정보 -> N개 마을을 2개 지역구로 분리, 2개 지역구 유권자 수 최소화 하는 프로그램!
 * 
 * 	<제약 조건>
 * 	시간 제한 : 50개 TC 3초
 * 	4<= N <= 8
 * 	i 번째 마을의 유권자 수 1<= Pi <= 20 
 * 	마을 r과 마을 c가 인접한 경우, Rrc가 1, 인접하지 않은 경우 0, Rrc = Rcr
 * 	모든 마을은 서로 인접 or 인접한 마을을 통해 연결
 * 	모든 마을은 반드시 지역구 중 하나에 포함되어야 함!
 * 
 *  <입력>
 *  첫 줄 : 테스트케이스 개수 T
 *  TC1 : 마을의 개수 N
 *  N개의 줄 : 마을 인접 정보 Rrc
 *  다음 줄 : i번째 마을의 유권자 Pi
 *  
 *  <출력>
 *  TC별 답 #t 정답
 *  
 *  
 *  <Solution>
 *  부분 집합을 사용하는 문제!
 *  1. 부분집합으로 지역구 1과 지역구 2를 나눈다(DFS)
 *  2. 지역구 별로 연결이 되어있는지  (BFS, length ++해서 length가 2일 때만 오케이)
 * 	3. 각 지역구 별 유권자 수 구해서 차이가 최소가 되는 것을 구한다!  
 * 
 */

public class Solution {

	static class Town {
		int to;
		int people;
		int division;
		Town next;

		public Town(int to, Town next) {
			super();
			this.to = to;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Town [to=" + to + ", people=" + people + ", division=" + division + ", next=" + next + "]";
		}

	}

	static int N;
	static int[][] map; // 마을의 정보
	static int[] people; // 유권자 수 정보
//	static ArrayList<Town> towns;
	static Town[] towns;

	static int answer;

	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 개수
		for (int t = 1; t < T+1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 마을의 개수

			// map 정보 입력 받기
			map = new int[N][N]; // 1 base index 안 쓸거니까, 라벨링 주의하자!
			towns = new Town[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) { // 연결된 정보가 있으면
						towns[i] = new Town(j, towns[i]);
					}
				}
			}

			// 유권자 정보 입력 받기
			people = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				people[i] = Integer.parseInt(st.nextToken());
				for (Town k = towns[i]; k != null; k = k.next) {
					k.people = people[i];
				}
//				towns[i].people = people[i];
			}

			answer = Integer.MAX_VALUE;
			// 이제 부분집합을 만들고 연결 정보 파악해서
			makeSubset(0, new boolean[N]);

			sb.append("#" + t + " " + answer + "\n");

		}
		System.out.println(sb);
	}

	private static void makeSubset(int nth, boolean[] status) {
		if (nth == status.length) {
			// 부분집합 완성
			// towns에 넣었으니 division 값은 별 쓸모 없긴 하다
			int[] division = new int[N];
			division = Division(status); // 1번, 2번 지역구 나뉨!

			visited = new boolean[N]; // 방문 여부 체크할 변수
			int check = 0;
			for (int i = 0; i < N; i++) { // DFS 돌려서
				if (!visited[i]) { // 방문한 적 없는 곳이면 DFS 돌리기
					bfs(i, visited, division);
					check++;
				}
//				System.out.println(Arrays.toString(visited));
			}
//			if(check == 3) {
//				System.out.println(check);
//				System.out.println(Arrays.toString(status));
//				for (Town a : towns) {
//					System.out.println(a);
//				}
//				
//			}
			if (check != 2)
				return; // 정확히 2개의 지역구로 나뉜 거면 아래 진행

			// 유권자 수 구해서 차이 구하기
			int people1 = 0;
			int people2 = 0;
			for (int i = 0; i < status.length; i++) {
				if (status[i]) {
					people1 += people[i];
				} else
					people2 += people[i];
			}

			answer = Math.min(answer, Math.abs(people1 - people2)); // 최소 차이값으로 업데이트
//			System.out.println(answer);
			return;
		}

		status[nth] = true;
		makeSubset(nth + 1, status);
		status[nth] = false;
		makeSubset(nth + 1, status);
	}

	// 마을이 어떤 지역구에 속하는지 알려줄 함수, 1번 혹은 2번 지역구
	private static int[] Division(boolean[] status) {
		int[] temp = new int[N];
		for (int i = 0; i < status.length; i++) {
			if (status[i]) {
				temp[i] = 1;
				for (Town k = towns[i]; k != null; k = k.next) {
					k.division = 1;
				}
//				towns[i].division = 1;
			} else {
				temp[i] = 2;
				for (Town k = towns[i]; k != null; k = k.next) {
					k.division = 2;
				}
			}
		}
		return temp;
	}

	// 지역구가 잘 나뉘었는지 체크해야함
//	private static void dfs(int i, boolean[] visited) {
//		visited[i] = true;	// i번째 노드 방문처리
//		
//		for(Town t = towns; )
//		
//	}

	private static void bfs(int i, boolean[] visited, int[] division) { // 현재 노드, 방문 체크, 지역구 정보
		// 초기 세팅
		Queue<Town> que = new ArrayDeque<>();
		que.offer(towns[i]);
		visited[i] = true;

		while (!que.isEmpty()) { // BFS 실행

//			System.out.println("QUE" + que);
			Town current = que.poll();
			if (!visited[current.to]) {
				for (Town t = towns[current.to]; t != null; t = t.next) {
//					System.out.println("현재 T는" + t);

					if (current.division != t.division)
						continue; // 지역구가 다르면 다음 노드 탐색
//					t.people = current.people;
//					t.division = current.division;
					visited[current.to] = true;
					que.offer(t);
				}
			}
		}
//		System.out.println(Arrays.toString(visited));
//		System.out.println(Arrays.toString(division));

	}

}
