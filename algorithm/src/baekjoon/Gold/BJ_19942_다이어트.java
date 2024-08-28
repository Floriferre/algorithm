package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 24.
@see https://www.acmicpc.net/problem/19942
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	식재료 N개 중 몇 개를 선택해서 영양분(단백질, 탄수화물, 지방, 비타민)이 일정 이상이 되어야함
*	이 중 비용이 최소가 되는 것을 구하기
*
*	<입력>
*	첫째줄 : 식재료 개수 N개
*	둘째줄 : 단백질(mp), 지방(mf), 탄수화물(ms), 비타민(mv) 최소 영양 성분
*	N개의 줄 : i번째 식재료의 각 영양성분 4개 + 가격 
*
*	<출력>
*	최소비용
*	사용된 식재료의 번호를 공백으로 구분해 오름차순으로 한 줄에 출력
*
*	<풀이>
*	N개중 몇 개를 선택하는 것이므로 부분집합을 사용하자
*	+ 시간을 줄이기 위해 백트래킹(가지치기) 사용*
*
*
*
*
*/

public class BJ_19942_다이어트 {

	static int N, mpC, mfC, msC, mvC, result;
	static int [][] table;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<String> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());	// 식재료 개수
		
		// 단백질, 지방, 탄수화물, 비타민의 최소 영양성분
		st = new StringTokenizer(br.readLine());
		mpC = Integer.parseInt(st.nextToken());
		mfC = Integer.parseInt(st.nextToken());
		msC = Integer.parseInt(st.nextToken());
		mvC = Integer.parseInt(st.nextToken());
		
		table = new int[N][5];	// 식재료의 각 정보를 저장해둘 테이블, 0베이스 인덱스를 사용할 것이므로 주의! (문제는 1베이스 인덱스)
		
		// i번째 식재료의 단백질, 지방, 탄수화물, 비타민과 가격
		for(int i = 0; i < N; i++) {	
			st = new StringTokenizer(br.readLine());
			table[i][0] = Integer.parseInt(st.nextToken());
			table[i][1] = Integer.parseInt(st.nextToken());
			table[i][2] = Integer.parseInt(st.nextToken());
			table[i][3] = Integer.parseInt(st.nextToken());
			table[i][4] = Integer.parseInt(st.nextToken());				
		}
		
		result = Integer.MAX_VALUE;	// 최솟값을 구하는 것이므로 max로 초기화
		list = new ArrayList<>();	// 같은 비용인 집합 저장해둘 리스트
		makeSubset(0, new boolean[N], 0, 0, 0, 0, 0);	// 부분집합 생성
		
		if(result == Integer.MAX_VALUE) {	// 조건을 만족하는 답이 없으면 -1 출력
			System.out.println(-1);
			System.out.println();
		}else{
			Collections.sort(list);	// 조건을 만족하는 것 중 사전순으로 가장 빠른 것을 출력해야하므로 정렬
			System.out.println(result);	
            System.out.println(list.get(0));		
		}

	}
	
	private static void makeSubset(final int nthCheck, boolean [] status, int sum, int mp, int mf, int ms, int mv) {
		if(sum > result) {	// 가지치기 (현재까지의 비용이 기존에 구한 최소 비용보다 클 때) 
			return;
		}
		
		if(nthCheck == status.length) {	// 부분집합 생성
			
			if(result >= sum && ( mp >= mpC && mf >= mfC && ms >= msC && mv >= mvC )) {			// 비용이 덜 나가거나 같은 경우, 각 영양소별 최소 조건을 만족하면 
				if(result > sum ) {	// 비용이 덜 나가는 경우 
					list.clear();	// 리스트 초기화 
				}
				sb.setLength(0);	// 스트링 빌더도 비워두기
				
				for(int i = 0; i < status.length; i++) {
					if(status[i] == true) {
						sb.append((i+1) + " ");	// 스트링빌더에 i번째 식재료까지 오름차순으로 넣기
					}
				}
				list.add(sb.toString());	// 리스트에 넣기
				result = sum;	// 결과(비용) 업데이트 
			}
			
			return;
		}
		
		
		status[nthCheck] = true;	// 해당 식재료 선택 O
		makeSubset(nthCheck+1, status, sum + table[nthCheck][4], mp + table[nthCheck][0], mf +  table[nthCheck][1], ms + table[nthCheck][2], mv+ table[nthCheck][3]);	// 가격과 영양소별 값 함께 전달
		status[nthCheck] = false;	// 해당 식재료 선택 X
		makeSubset(nthCheck+1, status, sum, mp, mf, ms, mv);	
		
	}

}
