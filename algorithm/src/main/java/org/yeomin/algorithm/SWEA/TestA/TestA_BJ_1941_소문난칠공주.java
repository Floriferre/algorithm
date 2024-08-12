package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 10.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<풀이>
*	1. 조합으로 7자리 구하기
*		1-1. 이다솜 파가 몇 명인지 같이 넘겨주기(혹은 임도연 파가 몇명인지)
*	2. 구한 7자리로 bfs 돌려서 연결되어있는지 확인하기
*		2-1. 몇 명이 연결되어있는지 확인할 변수 필요
*		2-2. 7자리 중에 연결된 부분이 있으면 큐에 다시 넣어주기!
*
*	오케이 가보자고 
*
*
*/

public class TestA_BJ_1941_소문난칠공주 {

	static char [][] map = new char[5][5];	// 자리 입력 받음 
	
	static int choosed [] = new int [7];
	static boolean [] visited = new boolean[7];	// 선택한 7자리에 대한 방문처리 
	
	static int result;	// 결과 저장
	
	static int [] dx = {-1, 1, 0, 0};
	static int [] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int i = 0; i < 5; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		combination(0, 0, choosed, 0);
		
		System.out.println(result);		
	}
	
	private static void combination(int nth, int startIndex, int [] choosed, int DoyeonCnt) {
		
		// 임도연 파가 더 많으면 리턴
		if(DoyeonCnt > 3 ) return;
		
		// 7자리 선택
		if(nth == 7) {
			visited = new boolean [7];
			bfs(choosed[0]/5, choosed[0]%5, visited);	// 가장 처음 선택된 것부터 넘겨줘서 탐색 시작
			return;
		}
		
		for(int i = startIndex; i < 25 ; i++) {	// 25칸 중에 7칸 선택할 것
			choosed[nth] = i;
			combination(nth+1, i+1, choosed, (map[i/5][i%5] == 'Y') ? DoyeonCnt+1 : DoyeonCnt);
		}
		
	}

	private static void bfs(int x, int y, boolean [] visited) {
		Queue<point> que = new ArrayDeque<>();
		
		que.offer(new point(x, y)); // 가장 처음을 큐에 넣어줌
		visited[0] = true; // 가장 처음은 방문했어
		int num = 1; // 몇명인지 셀거야 : 처음 큐에 하나 넣었으니 1로 시작해야해! 
		
		while(!que.isEmpty()) {	// 큐가 비어있지 않운 동안 셀 건데
			point current = que.poll();	// 큐에서 원소 꺼내서
			
			// 사방 탐색 시작
			for(int d = 0; d<4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];
				
				if(isIn(nx, ny)) {	//  자리 배치도 안에 있는 것만 탐색
					int nxt = nx * 5 + ny;	// choosed 배열에 든 것과 체크하려고
					for(int i = 0; i < 7; i++) {	// choosed 배열에 있는 7자리 중에
						if(!visited[i] && choosed[i] == nxt) {	// 연결된 자리가 있는지 탐색
							visited[i] = true;
							num++;	// 사람 수 늘려주기
							que.offer(new point(nx, ny));
							break; // 이 탐색에서 찾았으면 다음 탐색으로 넘기기 이래도 되나? 암튼
						}
					}
				}
			}
		}
		// 모든 탐색이 끝났을 때 사람수가 7이면 결과+1
		if(num == 7) result++; 
	}

	private static class point{
		int x, y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "point [x=" + x + ", y=" + y + "]";
		}
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx <5 && ny >=0 && ny<5;
	}


}
