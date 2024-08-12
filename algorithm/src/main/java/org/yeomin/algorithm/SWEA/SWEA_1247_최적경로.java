package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 {
	
	// 고객의 수
	static int N;
	
	// 회사와 집의 좌표를 받을 배열
	static int [] office = new int [2];
	static int [] house = new int [2];
	
	// 고객의 좌표를 받을 배열
	static int [][] customer; 
	
	// 결과를 저장할 배열
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			office[0] = Integer.parseInt(st.nextToken());
			office[1] = Integer.parseInt(st.nextToken());
			house[0] = Integer.parseInt(st.nextToken());
			house[1] = Integer.parseInt(st.nextToken());
			
			customer = new int [N][2];
			for(int n = 0; n < N; n++) {
				customer[n][0] = Integer.parseInt(st.nextToken());
				customer[n][1] = Integer.parseInt(st.nextToken());
			}
			
			// 결과를 늘 엄청 큰 수로 초기화
			ans = Integer.MAX_VALUE;
			
			// 
			dfs(0, office[0], office[1], new boolean[N], 0);
			
			sb.append("#" + t + " " + ans + "\n");
		
		}
		System.out.println(sb);
	}
	
	
	// nth : 몇 번째 집이냐 , x,y 현재 좌표, visited : 방문 여부 저장, dist : 현재까지 거리 계산하여 담을 변수
	public static void dfs(int nth, int x, int y, boolean [] visited, int dist) {
		
		if(dist >= ans) {
			return;
		}
		
		// 모든 고객을 만날 때까지 반복
		if(nth == N) {
			// 거리 계산은 회사까지의 거리로(왜내면 첫 시작을 집에서 시작해서)
			dist += distance(x, y, house[0], house[1]);
			
			// 더 작은 값으로 업데이트
			ans = Math.min(ans,  dist);	
			return;
		}
		
		// 순열로 탐색! 
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				// 넘길 때 꼭 현재까지의 위치 + 지금 방문한 여기까지의 거리를 더해서 넘겨줘야 함! 
				dfs(nth +1, customer[i][0], customer[i][1], visited, dist + distance(x,y, customer[i][0], customer[i][1]));
				visited[i] = false;
			}
		}
	}
	
	
	public static int distance(int x, int y, int x2, int y2) {
		return Math.abs(x-x2) + Math.abs(y-y2);
	}

}
