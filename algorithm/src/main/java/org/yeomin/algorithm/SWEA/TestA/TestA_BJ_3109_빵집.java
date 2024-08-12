package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 10.
@see https://www.acmicpc.net/problem/3109
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	빵집 R*C
*	첫번째 열 : 빵집의 가스관 / 마지막 열 : 원웅이의 빵집 
*	가스관과 빵집을 연결하는 파이프 설치, 건물이 있는 곳은 파이프 놓을 수 없음!
*	오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결 가능
*	경로는 서로 겹치면 X
*	가스관과 빵집을 연결하는 파이프라인의 최대 갯수를 구하는 프로그램 작성 
*
*	<입력>
*	첫째줄 : R, C (1 ≤ R ≤ 10,000, 5 ≤ C ≤ 500)
*	R개의 줄 : 빵집 근처 모습. 
*	. : 빈칸 
*	x : 건물
*	처음과 마지막 열은 늘 비어있음!
*
*	<풀이>
*	dfs 탐색을 사용하는 문제 : 사방 탐색을 할 필요가 없고, 바로 맨 마지막 열로 도착만 하면 되니까! 
*	위에서부터 탐색하는데 오른쪽 대각선 위 / 오른쪽 / 오른쪽 대각선 아래 순으로 탐색
*	첫째 열이 dfs의 입력값, 마지막 열이 도착값 
*	
*	부분집합 + dfs 로 해결해보자
*	1. 빵집의 열을 선택하는 건 부분집합
*	2. 열 선택했으면 dfs 돌려서 map 업데이트 
*
*
*/

public class TestA_BJ_3109_빵집 {

	static int R, C;
	static char[][] map;
	static int cnt;

	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 다시 풀어보자! bfs 돌리기~!! 
		for(int i = 0; i < R; i++) {
			if(dfs(i, 0)) {	// 가장 첫번째 열을 기준으로 돌리기 
				cnt++;	// 연결이 가능한 경우는 true를 return하므로 cnt++
			}
		}
		System.out.println(cnt);

	}

	private static boolean dfs(int x, int y) {
		
		// 3칸 중 어디로 이동 가능한지 탐색
		for(int d=0; d<3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(isIn(nx, ny) && map[nx][ny] != 'x') {	// 갈 수 있는 곳이면
				map[nx][ny] = 'x';	// 다른때 영향 받지 않게 map정보 바꿔두기
				if(ny == C-1) return true;	// 마지막 열까지 갔으면 true 리턴
				if(dfs(nx,ny)) return true;	// true 가 리턴된 경우 더이상 dfs 돌리지 않고 끝까지 true 리턴
			}
		}
		// 연결이 안 되는 경우는 false 리턴
		return false;
	}
	
	private static boolean isIn(int nx, int ny) {
		return nx>=0 && nx < R && ny>=0 && ny<C;
	}

}
