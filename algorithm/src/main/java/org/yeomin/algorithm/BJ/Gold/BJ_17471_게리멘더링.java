package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17471_게리멘더링 {
	
	static int n;
	static int[] population, area;
	static ArrayList<Integer>[] list;
	static int min = Integer.MAX_VALUE;
	static boolean[] visited;
	
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		population = new int [n+1];	// 인구수 저장할 배열
		list = new ArrayList[n+1]; 	// 영역 정보 받아올 배열
		st = new StringTokenizer(br.readLine()); 
		for(int i = 1; i <n+1; i++) {	// 영역별 인구수 저장
			list[i] = new ArrayList<>();
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < n+1; i++) {	// 인접 구역 정도
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// 영역 저장
		area = new int[n+1];
		dfs(1);
		
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);

	}
	
	public static void dfs(int k) {
		if(k==n+1) {	// 모든 지역을 다 뽑았다면
			int area1 = 0;
			int area2 = 0;
			
			for(int i = 1; i < n+1; i++) {	// 부분집합 결과에 따라 선거구 나눠서 인구수 더하기
				if(area[i] == 1 ) area1 += population[i];
				else area2 += population[i];
			}
			
			visited = new boolean[n+1];
			int link = 0; // 연결된 영역의 개수를 셈
			for (int i = 1; i < n+1; i++) {
				if(!visited[i]) {
					bfs(i, area[i]);	// 연결된 지역들을 모두 방문표시하는 BFS 탐색
					link++;
				}
			}
			
			// 지역이 2개 나뉘어 있고, 2지 역안에서 서로 연결되어있으면 최솟값 계산
			if(link == 2) min = Math.min(min,  Math.abs(area1-area2));
			
			return;
		}
		
		// 다음 구역을 1 구역과 2구열에 넣어가며 조합 탄생
		area[k] =1;
		dfs(k+1);
		area[k] = 2;
		dfs(k+1);
	}
	
	public static void bfs(int idx, int areaNum) {
		Queue<Integer> q = new LinkedList<>();
		visited[idx] = true;
		q.offer(idx);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			
			for (int i = 0; i < list[current].size(); i++) {
				int next = list[current].get(i);
				if(area[next] == areaNum && !visited[next]) {
					q.offer(next);
					visited[next] = true;
				}
			}
		}
	}
	
	
	
	
	
	

}
