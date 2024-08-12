package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 16.
 * @see https://www.acmicpc.net/problem/6987
 * @git
 * @youtube
 * @performance
 * @category #브루트포스, 백트래킹
 * @note 월드컵 조별 최종 예선 6개국 각각 5번의 경기 승, 무승부, 패의 수. 결과가 가능하면 1, 불가하면 0 출력
 * 
 *       첫째줄에서 넷째줄 : 6개국의 결과가 나라별로 승, 무승부, 패의 순서 18개의 수 승, 무, 패의 수 : 0~5 가능
 * 
 *       체크해야할 것 1. 승과 패의 합은 동일 2. 무승부는 짝수 무슨부가 서로 다른 애들에게 분포되어야 함! <- 이걸 어떻게
 *       체크할까?
 * 
 *       <sol> 1. 변수 - 승의 갯수 - 패의 갯수 ============> 얘네는 둘이 갯수 같은지 체크! - 무의 갯수 -
 *       짝수인지 - 종류가 2개 이상인지
 *       다른 사람 코드  봤습니다..ㅠㅠ 
 */

public class BJ_6987_월드컵 {

	static int[] home = { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, };
	static int[] away = { 1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5 };

	static int[][] score = new int[6][3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 갓 지우의 추천인 조합으로 풀어봅시다 -> 완탐 -> 순열 조합 부분집합
		for (int t = 0; t < 4; t++) {
			// 배열 초기화
			int total = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					score[i][j] = Integer.parseInt(st.nextToken());
					total += score[i][j];
				}
			}

			if (total != 30) {
				sb.append(0 + " ");
				continue;
			}

			if (game(0))
				sb.append(1 + " ");
			else
				sb.append(0 + " ");

		}
		System.out.println(sb);

//		int victory;
//		int defeat;
//		int tie;
//		int series;	// boolean 만등어서 체크할까?
//		int [] check;
//		// 계속 진행할지 선택하는 변수
//		boolean flag;
//		
//		for (int t = 0; t < 4; t++) {
//			st = new StringTokenizer(br.readLine());
//			victory = defeat = tie = series = 0;
//			flag = false;
//			check  = new int[6];
//			for(int i = 0; i < 6; i++) {
//				int temp = 0;
//				if(!flag) {
//					for(int j = 0; j < 3; j++) {
//						score[i][j] = Integer.parseInt(st.nextToken());
//						
//						if(score[i][j] > 7 || score[i][j] < 0) {
//							flag = true;
//							break;
//						}
//						
//						
//						if(j==0) victory += score[i][j];
//						else if(j==1) {
//							// 종류 더하는 거 가야함! 
//							if(score[i][j] != 0) {
//								if(i%2 == 0) {
//									series += score[i][j];
//								}else {
//									series -= score[i][j];
//								}
////								series += 1; 
//							}
//							tie += score[i][j];
//						}
//						else defeat += score[i][j];
//						temp += score[i][j];
//					}
//					// 매 회차마다 5의 배수인지 확인
//					if(temp != 5) {
//						flag = true;
//						break;
//					}
//				}
//			}
////			System.out.println(victory + " : " + defeat + " : " + tie);
////			System.out.println("check : " + Arrays.toString(check));
//			// 승패 비교
//			if(!flag) {				
//				if(victory != defeat) {
//					flag = true;
//				}
//				else if(tie%2 != 0) {
//					flag = true;
//				}
//				else if((victory + defeat + tie) != 30) {
//					flag = true;
//				}
//				else{
//					// 무승부일 때 조건 비교 
//					if(series != 0) {
//						flag = true;
//					}					
//				}
//			}
//			if(!flag) sb.append(1 + " ");
//			else sb.append(0 + " ");
//			
//		}
//		
//		System.out.println(sb);
	}

	private static boolean game(int game) {
		if (game == 15) {
			return true;
		}

		// 홈 팀이 이기는 경우
		if (score[home[game]][0] > 0 && score[away[game]][2] > 0) {
			score[home[game]][0]--;
			score[away[game]][2]--;
			if (game(game + 1))
				return true;
			score[home[game]][0]++;
			score[away[game]][2]++;
		}

		// 어웨이 팀이 이기는 경우
		if (score[home[game]][2] > 0 && score[away[game]][0] > 0) {
			score[away[game]][0]--;
			score[home[game]][2]--;
			if (game(game + 1))
				return true;
			score[away[game]][0]++;
			score[home[game]][2]++;
		}

		// 무승부인 경우
		if (score[home[game]][1] > 0 && score[away[game]][1] > 0) {
			score[home[game]][1]--;
			score[away[game]][1]--;
			if (game(game + 1))
				return true;
			score[home[game]][1]++;
			score[away[game]][1]++;
		}

		return false;
	}

}
