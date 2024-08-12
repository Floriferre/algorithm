package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*

@author 정여민
@since 2023. 9. 18.
@see https://www.acmicpc.net/problem/1484
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	다이어트 하는 성원이 
*	G킬로그램이 더 찜 = 성원이의 현재 몸무게 ^2 - 기억하고 있던 몸무게^2
*
*	성원이의 현재 몸무게로 가능한 것을 모두 출력
*
*
*	<입력>
*	첫째 줄 : G G <=100,000
*
*
*	<출력>
*	한 줄에 하나씩 가능한 성원이의 현재 몸무게 오름차순 출력
*	가능한 몸무게가 없으면 -1 출력
*
*	<풀이>
*	가능한 몸무게를 다 구한다? 몸무게가 얼마나 될 줄 알고? 
*	이걸 완탐을 하면 시간 터진다
*	무엇이 좋을까? 투 포인터로 풀어보자! left right 포인터를 두어 제곱의 차를 구하면 되지 않을까?
*
*
*/
public class BJ_1484_다이어트 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 찐 몸무게 
		long G = Integer.parseInt(st.nextToken());
		
		// 현재 몸무게, 기억하는 몸무게
		long left, right;
		left = right = 1; // 둘다 초기값 1kg으로 설정
		
		while(true) {
			// 현재 몸무게와 기억중인 몸무게의 차이값
			long dif = right*right - left*left;
			
			// 차이가 100,000 (G의 max)를 넘고, right - left 차이가 1이면 -> 앞으로도 계속 이 차이를 보일 거기 때문에
			if(dif > 100000 & right-left ==1 ) break;
			
			if (dif == G) {	// 차이가 G랑 같으면 스트링빌더에 넣고 right, left 모두 +1
				sb.append(right + "\n");
				right++;
				left++;
			}
			else if (dif >G) {	// 현재 몸무게가 더 나가면 기억하는 몸무게 +1
				left++;
			}else {	// 현재 몸무게가 덜 나가면	현재 몸무게 + 1
				right++;
			}
			
			
		}
		
		if(sb.length() == 0) {	// 스트링 빌더에 담긴 내용이 없으면 = 몸무게 못 구하면
			System.out.println(-1);
		}else {
			System.out.println(sb);
		}

	}

}
