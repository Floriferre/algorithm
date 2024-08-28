package baekjoon.STUDY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 8.
@see https://www.acmicpc.net/problem/1966
@git
@youtube
@performance
@category # 구현, 자료구조, 시뮬레이션, 큐 
@note 
	기존 프린터 : 큐 (FIFO)
	새로운 프린터기 : 문서의 중요도 확인 
		if (나머지 문서 중 중요도 더 높은 거 있으면 큐의 가장 뒤쪽에 넣기)
		else 인쇄 
	첫줄 : 테스트케이스 수
	 	첫번쨰 줄 : 문서의 개수, 알고 싶은 문서의 현재 위치
		두번째 줄 : N개 문서의 중요도 
*/

public class BJ_1966_프린터큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb;
		
		for(int t = 0; t < br.read(); t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			Deque<Integer []> que = new ArrayDeque<Integer[]>();
			for(int i = 0; i < N; i++) {
				que.push(new Integer [] {i, Integer.parseInt(st.nextToken())});
			}
		}

	}

}
