package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 9.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AV5LtJYKDzsDFAXc&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=4
@git
@youtube
@performance
@category #
@note 
	N^2 의 방
		1) 방이 존재하고 (상하좌우)
		2) 현재 방에 적힌 숫자 + 1 이어야 이동 가능
		3) 처음 어떤 수가 적혀 있어야 가장 많은 개수의 방이 이동 가능할까?
	DFS 돌리면 될 것 같긴 한데... 
		1) 갈 곳 없으면 return
		2) 갈 수 있으면 재귀
			할 때마다 max 값 update
	출력 형태 : 처음에 출발해야하는 방 번호, 최대 몇 개의 방을 이동가능한지
			
	첫번째 줄 : 테스트 케이스 개수 T
		TC line1: 하나의 정수 N
		TC line2: i번째 줄에 N개의 정수 공백 
		
		// 욕심쟁이 판다 문제와 비슷
		https://www.acmicpc.net/problem/1937 
*/

public class SWEA_1861_정사각형방 {
	
	static int max_value = Integer.MIN_VALUE;
	static int [][] board;
	static int index, num; // 방 번호, 몇 개의 방을 이동가능한지 
	
	// 탐색 시 사용할 변수 
	static int [] dx = {-1, 0, 0, 1};
	static int [] dy = {0, -1, 1, 0};
	
	static int N;
	
	static int count;	// 최대 몇 개의 방을 이동 할 수 있는 지 체크 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		StringBuilder sb = new StringBuilder();
		
		
		// 테스트 케이스 횟수
		int T = Integer.parseInt(st.nextToken());
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
//			visited = new boolean[N][N];
			board = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j <N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = 0; 
			index = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j<N; j++) {
					DFS(i,j, 1);
				}
			}
			
			sb.append("#" + t + " " + index + " " + count + "\n");
		}
		System.out.println(sb);
	}
	
	public static int DFS(int x, int y, int temp) {
		
		for(int d = 0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			// 범위 안에 있는 것만 
			if(nx<0 || nx>=N || ny<0||ny>=N) continue;
			else if(board[x][y] +1 != board[nx][ny]) continue;
			
			temp = DFS(nx,ny, temp +1);
			
			if(count < temp) {
				count = temp;
				index = board[x][y];
			}else if(count == temp && index > board[x][y]) {
				index = board[x][y];
			}
			
			break;
			
//			if(nx >= 0 && nx < N && ny>=0 && ny <N) {
//				// 정확히 1 차이 나는 것들만 
//				if((board[x][y] - board[nx][ny])==-1) {
//					DFS(nx,ny, temp +1);	
//					if(count < temp) {
//						count = temp;
//						index = board[x][y];
//					}else if(count == temp && index > board[x][y]) {
//						index = board[x][y];
//					}
//				}
//			}
			


		}
		return temp;
		
	}
}





























