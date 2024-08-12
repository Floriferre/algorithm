package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/*
@author 정여민
@since 2023. 10. 24.
@see https://www.acmicpc.net/problem/4386
@git
@youtube
@performance
@category #
@note 
*
* 	<문제> 
* 	별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태
* 	모든 별은 별자리 위의 선을 통해 서로 직/간접적으로 이어져야 한다
* 	별자리 이을 때마다 거리만큼 비용이 든다
* 	별자리를 만드는 최소 비용?
* 
* 	<입력>
* 	첫째줄 : 별자리 개수 n (1 ≤ n ≤ 100)
* 	n개의 줄 : 각 별의 x, y좌표 실수 형태, 최대 소수점 둘째 자리 | 좌표는 1000을 넘지 않는 양의 실수
*	
*	<출력>
*	첫째 줄에 정답을 출력한다. 절대/상대 오차는 10-2까지 허용
*
*	<풀이>
*	별자리 사이의 거리를 구해서 최소 신장 트리를 구하는 것!
*	별자리 입력 받고나서 각 별끼리의 거리 구해서 넣어두기 
*
*
*/

public class BJ_4386_별자리만들기 {
	
	private static class Star implements Comparable<Star>{
		int start, end;
		double x1, x2;
		double y1, y2;
		double dist;
		public Star(int start, int end, double x1, double x2, double y1, double y2, double dist) {
			super();
			this.start = start;
			this.end = end;
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
			this.dist = dist;
		}
		@Override
		public String toString() {
			return "Star [start=" + start + ", end=" + end + ", x1=" + x1 + ", x2=" + x2 + ", y1=" + y1 + ", y2=" + y2
					+ ", dist=" + dist + "]";
		}
		
		@Override
		public int compareTo(Star o) {
			if(this.dist > o.dist) {
				return 1;
			}else if (this.dist == o.dist) {
				return 0;
			}else {
				return -1;
			}
			
			
		};
	}
	
	
	static int N;

	static double [][] stars;
	private static int[] parents;
	private static PriorityQueue<Star> pq;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		
		stars = new double[N+1][2];
		
		for(int i=1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		pq = new PriorityQueue<>();
		
		for(int i = 1; i < N+1; i++) {
			for(int j=1; j < N+1; j++) {
				double x1 = stars[i][0];
				double y1 = stars[i][1];
				
				double x2 = stars[j][0];
				double y2 = stars[j][1];
				
				double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
				
				pq.add(new Star(i, j, x1, x2, y1, y2, dist));
			}
		}
		
		make();
		
		double totalDist = 0.0;
		int size = pq.size();
		while(!pq.isEmpty() && size > 0) {

				Star star = pq.poll();
				
				if(union(star.start, star.end)) {
					totalDist += star.dist;
					size--;
				}
		}
		
		// 소수 둘째 자리까지 주어지므로 
		double result = (double) Math.round(totalDist*100)/100;
		System.out.println(result);

	}
	
	private static void make() {
		parents = new int[N+1];
		
		for(int i=0; i <=N; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		
		parents[bRoot] = aRoot;
		return true;
	}
	

}
