package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 1.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV14ABYKADACFAYh&probBoxId=AYlH3z4K78oDFAVR+&type=PROBLEM&problemBoxTitle=0731%EC%A3%BC&problemBoxCnt=3
@git
@youtube
@performance 메모리 20,916KB 시간 130ms
@category # 완전 탐색일까 순열일까 
@note 
	100 * 100 배열로 주어지는 사다리
		1 : 사다리
		0 : 빈 공간
	아래 방향으로 진행 but 좌우 방향으로 이동 가능한 통로를 발견하면 방향 전환
		- 기본적으로 X에서 위로 올라간다 
			- 방향 전환 2가지 
				오른쪽 사다리
				왼쪽 사다리 
			- dx, dy 변수를 만들어서 탐색을 하자! 
	사다리이므로 탐색은 모든 곳에서 하는 것이 아니라, 제일 첫 줄에서 1인 것들만 탐색!
	도착지점 X : 2 
	
*/

public class SWEA_Ladder1 {
	
	// 사다리 정보 저장
	static int [][] ladder ;
	// 방문 체크할 배열
	static boolean [][] visited;
	// X에서 거꾸로 탐색해서 나아가자~ 
	static int [] dx = { -1, 0, 0};
	static int [] dy = { 0, 1, -1};
	
	static int X = 0;
	
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		// 테스트 케이스 10개
		for(int t = 0; t < 10; t++) {
			
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			// 초기화
			ladder = new int[100][100];
//			visited = new boolean[100][100];
			result = 0;
			X = 0;
//			Arrays.fill(visited, false);
			// 사다리 정보 저장
			for (int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 마지막 줄에서 2번 찾기
			for (int i = 0; i < 100; i++) {
				if(ladder[99][i] == 2) {
					X = i;
					break;
				}
			}
//			System.out.println("데트스 번호 : "+ t + " : " +X);
			
			// 탐색 진행
			dfs(98,X);
			// Stringbuilder로 한 번에 출력하는 것도 괜츈함! 
			System.out.printf("#%d %d\n",t+1 ,result);
			
//			for(int i = 99; i > -1; i--) {
//				for(int j = 99; j>-1; j--) {
//					
//					for(int d = 0; d < 3; d++) {
//						int nx = i + dx[d];
//						int ny = j + dy[d];
//						
//						// visited하지 않고 범위 안에 있으면!
//						if(nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && !visited[nx][ny]) {
//							visited[nx][ny] = true;
//						}
//					}
//				}
//			}	
		}
	}
	
	// DFS로 탐색? 
	
	public static void dfs(int x, int y) {
		
		if(x == 0) return;
		
//		visited[x][y] = true;
		for(int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// visited하지 않고 범위 안에 있으면!
			if(nx >= 0 && nx < 100 && ny >= 0 && ny < 100 && ladder[nx][ny] == 1) {
//				visited[nx][ny] = true;
				result = ny;
				ladder[nx][ny] = 0;
				if(nx == 0) {
					return;
				}
				dfs(nx,ny);
//				visited[nx][ny] = false;
			}
		}
	}
	

}
