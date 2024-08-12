package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

@author 정여민
@since 2023. 8. 20.
@see https://www.acmicpc.net/problem/2531
@git
@youtube
@performance 19904KB 224ms
@category # 슬라이딩 윈도우
@note 
*	회전초밥 음식점 - 자기가 좋아하는 초밥 골라 먹기
*	규칙 1. k개의 접시 연속으로 먹으면 할인 가격
*	규칙 2. 쿠폰에 적힌 초밥 하나 추가 제공(벨트 위에 없으면 새로 만들어 제공)
*	손님이 먹을 수 있는 초밥 가짓수의 최대는?
*
*	첫째줄 : 접시수 N, 초밥의 가짓수 d, 연속해서 먹는 접시수 k, 쿠폰번호 c
*	두번째줄 ~ N : 초밥 종류 나타내는 1이상 d 이하의 정수 
*	
*	길이가 고정되어 있으므로 슬라이딩 윈도우를 사용해보면 좋지 않을까? 
*
*
*/

public class BJ_2531_회전초밥 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		// 입력 받기
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 접시 수
		int d = Integer.parseInt(st.nextToken());	// 가짓수
		int k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시수
		int c = Integer.parseInt(st.nextToken());	// 쿠폰번호
		
		
		// 초밥을 배열에 저장 
		int [] food = new int[N];

		for(int n = 0; n <N; n++) {
			st = new StringTokenizer(br.readLine());
			food[n] = Integer.parseInt(st.nextToken());
		}

		// 초밥 가짓수 셀 배열, 1부터 시작하는 인덱스 사용
		int [] alreadyEat = new int [d+1];
		
		// 초밥 갯수
		int max = 0;
		for(int i = 0; i<k; i++ ) {	//우선 k개의 초밥을 선택하는데, 아직 먹지 않은 거면 count증가
			if(alreadyEat[food[i]]==0) {
				max++;
			}
			alreadyEat[food[i]]+=1; //현재까지 고른 접시에 있는 것들 수량 +1
		}
		
		// 쿠폰 쓰기 전의 최댓값
		int before = max;

		
		if(alreadyEat[c]==0) max++;
		
		// 슬라이딩 윈도우 사용, k번째 초밥부터 N-1까지 쭉 돌기
		for(int i = k; i < N+k; i++) {
			
			// 최대로 먹을 수 있는 초밥 가짓수, 쿠폰 사용 여부
			int temp = before;
			int coupon = 0;
			
			//맨 앞에거 빼고 
			alreadyEat[food[i-k]]-=1;
			// 초밥 가짓ㄱ수 줄일 수 있으면 지우기
			if(alreadyEat[food[i-k]] == 0) temp--;
			// k번째 거 더하기
			int end = (i%N);
			// 먹어보지 않은 초밥이면 가짓수 + 1
			if(alreadyEat[food[end]]==0) temp++;
			alreadyEat[food[end]]+=1;
			
			// 쿠폰 초밥 먹은 적 없으면 +1
			if(alreadyEat[c]==0) coupon =1;
			
			// 최댓값 갱신
			if(max < temp+coupon) max = temp+coupon;
			before = temp;
			
			
			
//			if(temp <= max) {
//				// 쿠폰으로 새로운 초밥을 먹을 수 있으면
//				if(alreadyEat[c]==0) {
//					temp = max+1;	//하나 추가
//				}
//				else {	// 이미 쿠폰에 적힌 번호 먹었으면
//					temp = max;
//				}
//			}
			
//			// end 이동
//			int end = (i+k-1)%N;	// 회전초밥이므로 모듈러 연산을 통해 계속 돌리기 
//			if(alreadyEat[food[end]]==0) {	// 안 먹었던 초밥이면 count+1
//				count++;
//			}
//			alreadyEat[food[end]]++;	// 먹은 것 처리
//			
//			// start 이동
//			alreadyEat[food[i-1]]--;	//맨 앞의 초밥 제거
//			if(alreadyEat[food[i-1]]==0) {	// 초밥 종류까지 제거할 수 있는 경우
//				count--;
//			}
			
		}
		System.out.println(max);
		
	}

}
