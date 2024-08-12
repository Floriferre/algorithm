package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 1.
@see https://www.acmicpc.net/problem/1244
@git
@youtube
@performance 14104KB 128ms
@category # 구현, 시뮬레이션
@note 
* 	첫째줄 : N개의 스위치
*	둘째줄 : 스위치 상태 <- 배열로 받을 것
*	셋째줄 : 학생 수
*	넷째줄 ~ : 학생(남학생 1, 여학생 2), 받은 수
*		남학생 : 본인의 번호의 배수인 스위치 상태 flip
*		여학생 : 본인이 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치 포함하는 구간의 스위치 flip
		스위치는 20개씩 끊어서 출력
*/
public class BJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 스위치 개수
		int N = Integer.parseInt(st.nextToken());
		// 스위치 상태 : 스위치 번호가 1번부터 시작
		int [] switches = new int[N+1];
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <switches.length; i++) {
			switches[i]  = Integer.parseInt(st.nextToken());
		}
		
		// 학생 수
		st = new StringTokenizer(br.readLine());
		int student = Integer.parseInt(st.nextToken());
		
		// 학생 상태에 따른 변화
		for(int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			// 성별, 스위치 번호
			int s = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
//			System.out.println(Arrays.toString(switches));
			// 남학생일 때 : 스위치 번호 배수들 반대로 바꾸기 
			if(s == 1) {
				for(int j = n; j <switches.length; j += n) {
					// 배수에 해당하는 스위치 상태 바꾸기
//					System.out.println(j);
					if(switches[j] == 0) {
						switches[j] = 1;
					}else if(switches[j] == 1) {
						switches[j] = 0;
					}
				}
			}
			// 여학생일 때
			else if(s==2) {
				// 우선 본인의 자리는 무조건 상태 변화
				if(switches[n] == 0) {
					switches[n] = 1;
				}else if(switches[n] == 1) {
					switches[n] = 0;
				}
				
				// 대칭인 스위치 찾아서 상태 변화
				int cnt = 1;
				// 스위치 범위 내에 있을 때
				while((n-cnt) > 0 && (n+cnt) < switches.length) {
					// 대칭이면
					if(switches[n-cnt] == switches[n+cnt]) {
						if(switches[n-cnt]==0) {
							switches[n-cnt] = 1;
							switches[n+cnt] = 1;
						}else if(switches[n-cnt] == 1) {
							switches[n-cnt] = 0;
							switches[n+cnt] = 0;
						}
						cnt+=1;
					}else {
						break;
					}
				}

			}

		}
		//출력
		for(int i = 1; i<switches.length; i++) {
			System.out.print(switches[i] + " ");
			if(i%20 == 0) {
				System.out.println();
			}
		}
		
		
		
	}

}
