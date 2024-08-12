package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

@author 정여민
@since 2023. 9. 17.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
@git
@youtube
@performance
@category #
@note 
*	<문제> 
*	보호 필름 제작 두께 D, 가로  크기 W
*	각 셀은 A, 혹은 B 특성
*	합격 기준 K : 세로 방향으로 각 열에서 연속된 특성의 개수가 K이상
*	약품 : row 하나를 A 혹은 B로 만들어버림
*	약품을 가장 적게 사용하여 성능검사 통과하고 싶다 
*
*	<제약사항>
*	1. 3<= D <=13
*	2. 1<= W <= 20
*	3. 1<= K <= D
*	4. 셀의 특성은 A, B 중 하나
*
*	<입력> 
*	첫째줋 : 테스트케이스 개수 T
*	TC1 : 보호필름 두께 D, 가로크기 W, 합격기준 K
*	D개의 줄 : 보호필름 정보 0과 1로 이루어짐(A는 0, B는 1)
*
*	<풀이>
*	완전 탐색. 
*	구성 1. 현재 상태의 셀이 합격기준 검사 통과하는지 판단하는 메서드
*	구성 2. 재귀 : 해당 row에 약품을 주입하지 않았을 때, 약품 A를 주입했을 때, 약품 B를 주입했을 때
*
*
*/

public class SWEA_2112_보호필름 {

	static int D, W, K;
	static int[][] map;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int [D][W];

			for (int d = 0; d < D; d++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[d][w] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			solution(map, 0, 0);

			sb.append("#" + t + " " + result + "\n");
		}

		System.out.println(sb);
	}

	private static void solution(int[][] map, int cnt, int currentRow) { // cnt : 약품 사용 횟수

//		System.out.println("이번회차에서는 : " + cnt);
//		for(int [] a : map) {
//			System.out.println(Arrays.toString(a));
//		}
//		System.out.println();
		
		if(cnt >= result) {
			return;
		}
		
		if(currentRow == D) {
			if (isOk(map)) { // 현재 맵이 성능검사 통과했는가?
				result = Math.min(result, cnt);
			}			
			return;
		}

		solution(map, cnt, currentRow + 1); // 약품 안 부었음
		// 약품 A 붓기
		solution(Drug(map, currentRow, 0), cnt + 1, currentRow+1);
		// 약품 B 붓기
		solution(Drug(map, currentRow, 1), cnt + 1, currentRow+1);
		
	}

	private static int[][] Drug(int[][] map, int currentRow, int type) {
		int[][] temp = new int [D][W];
		
		for(int d = 0; d < D; d++) {
			for(int w = 0; w <W; w++) {
				temp[d][w] = map[d][w];
			}
		}

		if (type == 0) { // 약품 A
			for (int i = 0; i < W; i++) {
				temp[currentRow][i] = 0;
			}
		} else { // 약품 B
			for (int i = 0; i < W; i++) {
				temp[currentRow][i] = 1;
			}
		}

		return temp;
	}

	private static boolean isOk(int[][] map) { // 현재 맵 상태 입력 받기
		boolean flag = false; // 성능 검사 통과했는지 체크

		for (int i = 0; i < W; i++) {
			int current = map[0][i];
			flag = false;

			int cnt = 1; // 연속으로 몇 개가 같은 타입인지 셀 변수
			for (int j = 1; j < D; j++) {
				if (current == map[j][i]) {
					cnt++;
				} else {
					cnt = 1;
					current = map[j][i];
				}
				if (cnt == K) {
					flag = true;
					break;
				}
			}
			if (!flag)
				return false;
		}
		return true;
	}

}
