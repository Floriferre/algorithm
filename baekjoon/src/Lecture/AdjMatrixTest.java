package Lecture;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class AdjMatrixTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt();
		int E = sc.nextInt();
		
		int [][] adjMatrix = new int [V][V];
		
		//간선 있으면 1, 없으면 0
		
		for (int i = 0; i < E; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}
		
		for (int[] is : adjMatrix) {
			System.out.println(Arrays.toString(is));
		}
		
//		bfs(adjMatrix);
		
		dfs(adjMatrix, new boolean [V], 0);
		
	}
	
	private static void bfs(int [][] adjMatrix) {
		int size = adjMatrix.length;
		Queue<Integer> que = new ArrayDeque<>();	// 큐에 넣는 값은 방문대상을 관리할 값과 그 밖의 값들을 넣을 수 있다
		boolean [] visited = new boolean[size];
		
		// 탐색의 시작점 정점0으로 하자
		que.offer(0);
		visited[0] = true;
		
		while(!que.isEmpty()) {
			int current = que.poll();
			
			System.out.println((char) (current+65));
			
			//현 정점의 인접정점을 체크하며 대기열에 넣기
			for (int i = 0; i < size; i++) {
				if(adjMatrix[current][i] ==1 && !visited[i]) {
					que.offer(i);
					visited[i] = true;
				}
			}
		}	
	}
	
	private static void dfs(int [][] adjMatrix, boolean [] visited, int current) {
		
		visited[current] = true;
		System.out.println((char) (current+65));
		
		//현 정점의 인접정점을 체크하며 대기열에 넣기
		for (int i = 0, size = adjMatrix.length; i < size; i++) {
			if(adjMatrix[current][i] !=0 && !visited[i]) {
				dfs(adjMatrix, visited, i);
			}
		}
	}	
}
