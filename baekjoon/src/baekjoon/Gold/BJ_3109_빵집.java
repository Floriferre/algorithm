package baekjoon.Gold;
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

public class BJ_3109_빵집 {

	static int R, C;
	static char[][] map;
	static boolean[][] visited;
	static int result = Integer.MIN_VALUE;
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

		// dfs 돌면서 그리디한 방법으로 파이프 연결하기
		for (int i = 0; i < R; i++) {
			if(dfs(i, 0, map)) cnt++;
		}

		System.out.println(cnt);

	}

	private static boolean dfs(int x, int y, char[][] map) {	// boolean으로 하는 이유 : 길을 연결하고 나서 돌아가야하는데, 이렇게 처리하지 않으면 다음 빈공간을 계속 탐색한다 (재귀함수 특성상) 

		for (int d = 0; d < 3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (isIn(nx, ny) && map[nx][ny] == '.') {
				// 종료 처리 해줘야함
				map[nx][ny] = 'x';
				if (ny == C - 1) { // 마지막 열에 닿으면 바로 종료시킴
					return true;
				}
				if (dfs(nx, ny, map))
					return true;
			}
		}
		
		return false;

	}

	private static boolean isIn(int nx, int ny) {
		return nx >= 0 && nx < R && ny >= 0 && ny < C;
	}

}
