package TestA;

import java.io.*;
import java.util.*;

/*
@author 정여민
@since 2023. 10. 11.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	두께 D, 가로 W
*	각 셀은 특성 A 혹은 B를 갖고 있음
*	성능검사 : 단면의 모든 세로 방향에 대해서 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우만 통과
*	약 투입 : 특정 행을 모두 A or B로 바꿔버림
*	약품 최소 투입 횟수?
*	약품을 투입하지 않고도 성능검사를 통과하는 경우 0 출력
*
*	<제약사항>
*	두께 (3≤D≤13)
* 	가로크기 (1≤W≤20)
* 	합격기준 (1≤K≤D)
*	특성은 A, B 두 개만 존재
*	
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 보호 필름의 두께 D, 가로크기 W, 합격기준 K
*	D개의 줄 : 단면의 정보 특성 A : 0 / 특성 B : 1
*
*	<출력>
*	성능검사를 통과할 수 있는 약품의 최소 투입 횟수
*
*	<풀이>
*	1. 약품 투입 : 부분집합
*		1-1. 매 회차마다 성능 검사 통과 여부 체크
*			1-1-1. 통과하면 최소 약품수인지 업데이트 
*		1-2. 다음으로 넘겨줄 때 약품 투입 횟수 같이 넘겨주자 => 최소 약품 횟수 체크하려고
*		1-3. 각 행에 약품A 투입한 경우, 약품 B 투입한 경우, 아무것도 투입하지 않은 경우로 나뉜다  
*	2. 성능검사
*		2-1. 해당 필름이 성능 검사를 통과했는지 확인하기 위해 열에 K개 연속된 것이 있는지 확인, 하나라도 만족 못하면 false 반환
*
*/

public class TestA_2112_보호필름 {

	static int[][] map;
	static int R, C, K;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			result = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[R][C];
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < C; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			subset(map, 0, 0);

			sb.append("#" + t + " " + result + "\n");

		}
		System.out.println(sb);
	}

	private static void subset(int[][] map, int nth, int cnt) {
		// 가지치기 넣기 : 현재까지의 최솟값보다 크면 볼 필요 없다
		if(cnt >= result) return;
		
		if (nth == R) { // 모든 행을 다 체크했으면 최소 약품수 업데이트
			if (isOk(map)) {
				result = Math.min(result, cnt);
			}
			return;
		}

		// 아무 약품도 쓰지 않을 때
		subset(map, nth + 1, cnt);

		// 약품 A를 처리했을 때
		subset(copyMap(map, nth, 0), nth + 1, cnt + 1);

		// 약품 B를 처리했을 때
		subset(copyMap(map, nth, 1), nth + 1, cnt + 1);


	}

	private static int[][] copyMap(int[][] map, int row, int type) { // 약품 처리에 따라 배열 값 복사해서 넘겨주기
		int[][] temp = new int[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
					temp[r][c] = map[r][c];
			}
		}
		
		if(type == 0) {	// 약품 A
			for(int i=0; i <C; i++) {
				temp[row][i] = 0;
			}
		}
		else {	// 약품 B
			for(int i=0; i <C; i++) {
				temp[row][i] = 1;
			}
		}
		
		return temp;
	}

	private static boolean isOk(int[][] map) { // 성능 검사 : 각 열마다 연속된 K개의 배열이 있어야 함

		boolean flag = false;
		for (int c = 0; c < C; c++) {
			int current = map[0][c]; // 제일 위의 것을 기준으로
			int cnt = 1;
			flag = false;
			for (int r = 1; r < R; r++) {
				if (current == map[r][c]) {
					cnt++;
				} else { // 다른 셀이 나타나면 초기화
					cnt = 1;
					current = map[r][c];
				}
				if (cnt == K) { // 연속으로 K개가 있으면
					flag = true;
					break;
				}
			}
			// 한 줄 다 돌았는데 flag가 false이면 성능검사 실패니까 바로 false 리턴
			if (!flag)
				return false;
		}

		// 모든 열에 대해 만족하면 성능검사 통과한 것!
		return true;
	}

}
