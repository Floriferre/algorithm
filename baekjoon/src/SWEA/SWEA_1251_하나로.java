package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
/*
@author 정여민
@since 2023. 8. 24.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV15StKqAQkCFAYD&probBoxId=AYoaJoMabgADFAU6&type=PROBLEM&problemBoxTitle=0821%EC%A3%BC&problemBoxCnt=3
@git
@youtube
@performance
@category #
@note 
	<문제 설명> 
	인도네시아 내의 모든 섬을 연결해야하는 프로젝트 
	환경부담금 : 환경부담세율(E) * 해저터널의 길이(L)^2
	총 환경 부담금을 최소로 하면서 N개의 모든 섬을 연결할 수 있는 교통 시스템
	
	<입력>
	첫 줄 : 테스트 케이스 개수
	TC1 : 섬의 개수 N
	TC2 : 각 섬의 x좌표
	TC3 : 각 섬의 y좌표
	TC4 : 환경부담세율 실수 E

	<풀이>
	입력 받은 후, 각 거리별 환경부담금 계산해서 넣어두고
	크루스칼 알고리즘 돌리면 될 듯? 


*/

public class SWEA_1251_하나로 {

	static class Edge implements Comparable<Edge> {
		int from, to;
		double weight;

		/**
		 * @param from
		 * @param to
		 * @param weight
		 */
		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	}
	
	static void make() {
		parents = new int[N];
		for(int i = 0; i < N; i++) {
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
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	

	static int N, m;

	static int[] islandX;
	static int[] islandY;
	
	static int[] parents;
	static ArrayList<Edge> edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 수
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			// 섬의 x좌표, y좌표를 받을 배열
			islandX = new int[N];
			islandY = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { // X 좌표 입력 받기
				islandX[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) { // Y 좌표 입력 받기
				islandY[i] = Integer.parseInt(st.nextToken());
			}
			// 환경부담세율 저장 (마지막에 한 번만 곱해도 되지 않나?)
			st = new StringTokenizer(br.readLine());
			double E = Double.parseDouble(st.nextToken());

			
			edgeList = new ArrayList<>();
			// 섬간의 거리 저장해둠 			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					edgeList.add(new Edge(i, j, distance(islandX[i], islandY[i], islandX[j], islandY[j])));
				}
			}
			
			// 여기만 해결하면 될 듯! 
			edgeList.sort(null);
			
			make();
			
			Double result = 0.0;
			int count = 0;
			for(Edge edge : edgeList) {
					if(union(edge.from, edge.to)) {
						result += edge.weight;
						if(++count == N-1) break;
					}

			}
			sb.append("#" + t + " " + Math.round(result*E) + "\n");
		}
		System.out.println(sb);

	}

	// 거리 계산할 메서도
	public static double distance(int x, int y, int x2, int y2) {
		long distX = Math.abs(x-x2);
		long distY = Math.abs(y-y2);
		
		return distX*distX + distY*distY;
	}

}
