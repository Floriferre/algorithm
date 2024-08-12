package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 9.
@see https://www.acmicpc.net/problem/2563
@git
@youtube
@performance
@category #구현
@note 
	가로 세로 크기가 각각 100 인 도화지 
	 위에 가로 세로 크기가 10인 정사각형 도화지 붙임 
	 검은 영역의 넓이 구하는 프로그램 
	 
	 시간제한 1초 메모리 제한 128mb
	 
	 첫째줄 : 색종이의 수
	 	색종이를 붙인 위치 : 색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리, 색종이 아랫변과 도화지 아래쪽 변 사이의 거리 
	걍 배열 탐색하자..완탐... 이유는 모르겠다 
	예전에 계속 실패한 걸 보면 복잡한 수식을 활용하는 건 아닌 것 같다...
	
	시간 복잡도 : 100 * 100 을 탐색해봤자 얼마 안 걸린다 사실 수식 계산하는 거 잘 모른다 O(n^2)이라는 것 밖에 
				그리고 이걸 색종이 갯수만큼 반복해도 10*10 탐색 * 100이라 오래 안 걸릴 것 같다 
	공간 복잡도 : 이것도 난 잘 모른다 배열이 안 터졌으니까 괜찮았던 게 아닐까 
	 
*/

public class BJ_2563_색종이 {
	static int [][]board = new int [100][100];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 색종이 수 
		int N = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			// x : 왼쪽 위의 x좌표  / y : 왼쪽 위 y좌표
			int y = Integer.parseInt(st.nextToken());	
			int x = Integer.parseInt(st.nextToken());
			
			for(int r = x; r < x+10; r++) {
				for(int c = y; c<y+10; c++) {
					board[r][c] = 1;
				}
			}
			
		}	
		
		int count = 0;
		for(int r = 0; r < 100; r++) {
			for(int c = 0; c<100; c++) {
				if(board[r][c] == 1) {
					count++;
				}
			}
		}
		sb.append(count);
		
		System.out.println(count);
		
		
			
	}

}
