package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 11.
@see https://www.acmicpc.net/problem/15686
@git
@youtube
@performance
@category #
@note 
	NxN 도시, (r, c)번째 도시 
		0 빈칸, 1 집, 2 치킨집
		치킨거리 : 집과 가장 가까운 치킨 집 사이의 거리 
		도시의 치킨 거리 : 모든 집의 치킨 거리의 합
		(r1, c1)과 (r2, c2) 사이의 거리 = |r1-r2| + |c1-c2|
	치킨집의 개수 최대 M개 (나머지 폐업) 
	어떻게 골라야 도시의 치킨거리가 가장 작아지는가 

	1~M개의 치킨집을 고르는 방법 : 조합 
	치킨거리 치킨집마다 미리 구해놓기 -> 2가 몇개 있는지 세서 배열 만들어서 관리 
	DFS BFS? 
	
	걍 배열 쭉 돌면서 1인것과 2인것 나눠서 배열에 저장해버리고. 거리 업데이트? 
*/



public class BJ_15686_치킨배달 {
	public static class Node{
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	static int N, M;
	static int [][] chickenDistance;
	static List<Node> chicken = new ArrayList<>();
	static List<Node> house = new ArrayList<>();
	
	static int [][] map;
	
	static boolean [] isVisited; // 치킨집 방문했는지 체크 
	
	static int min = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int [N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					house.add(new Node(i, j));
				}else if(map[i][j] == 2) {
					chicken.add(new Node(i,j));
				}
			}
		}
		
		isVisited = new boolean [chicken.size()];
		ftn(0,0);
		System.out.println(min);
		
				
	}
	
	public static void ftn(int nth, int startIndex) {
		if(nth == M) {
			int total = 0; 
			for(int i = 0; i < house.size(); i++) {
				int sum = Integer.MAX_VALUE;
				for(int j = 0; j < chicken.size(); j++) {
					if(isVisited[j]) {
						int dist = Math.abs(house.get(i).x - chicken.get(i).x) + Math.abs(house.get(i).y - chicken.get(i).y);
						sum = Math.min(sum, dist);
					}
				}
				total += sum;
			}
			min = Math.min(total, min);
			return;
		}
		
		for(int i = startIndex; i < chicken.size(); i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				ftn(nth + 1, i+1);
				isVisited[i] = false;
			}
		}
		
		
		
	}

}
