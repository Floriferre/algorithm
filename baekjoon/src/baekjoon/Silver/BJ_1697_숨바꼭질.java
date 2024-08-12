package baekjoon.Silver;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
@author 정여민
@since 2023. 8. 21.
@see https://www.acmicpc.net/problem/1697
@git
@youtube
@performance
@category #
@note 
*	숨바꼭질 
*	수빈 : 현재 N 0<=N<=100,000
*	동생 : K 0 <= K <= 100,000
*	수빈이가 위치가 X일 때 걸으면 1초후 x-1 or x+1 이동
*					순간이동 : 2*X로 움직임
*	수빈이와 동생의 위치가 주어졌을 때 수빈이가 동생을 찾을 수 있는 가장 빠른 시간은?
*/

public class BJ_1697_숨바꼭질 {
	static int N, K, result;
	static int visited [] = new int [100001]; // 방문 체크 할 것
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();	// 수빈이 위치
		K = sc.nextInt();	// 동생 위치
		
		
		
		System.out.println(BFS(N));
	}
	
	
	
	
	private static int BFS(int n) {
		Queue<Integer> que = new ArrayDeque<>();
	
		que.offer(n);
		visited[n] = 0;
		
		while(!que.isEmpty()) {
			
			int a = que.poll();
			if(a == K) {
				return visited[a];
			}
			
			if(0<=a-1 && a-1<=100000 && visited[a-1]==0) {
				visited[a-1] = visited[a] + 1;
				que.offer(a-1);
			}
			if(0<=a+1 && a+1<=100000 && visited[a+1]==0) {
				visited[a+1] = visited[a] + 1;
				que.offer(a+1);
			}
			if(0<=a*2 && a*2<=100000 && visited[a*2]==0) {
				visited[a*2] = visited[a] + 1;
				que.offer(a*2);
			}
			
//			System.out.println(que);
			
			
			
//			for(int i = a-1; i < a+1; i *= 2) {
//				if(0<=i && i<=100000 && visited[a]==0) {
//					visited[i] = visited[a]+1;
//					que.offer(i);
//				}
//			}
		}
		return 0;
	
	
	
	
	
	}
}
