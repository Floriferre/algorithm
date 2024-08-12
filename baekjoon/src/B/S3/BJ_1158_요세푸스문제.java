package B.S3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 7.
@see https://www.acmicpc.net/problem/1158
@git
@youtube
@performance # 294692KB 488ms
@category # 큐, 자료구조, 구현 
@note 
	요세푸스 문제 : N명의 사람들이 원을 이루면서 앉아있음. 양의 정수 K.
	순서대로 K번째 사람을 제거. 남은 사람들로 이루어진 원을 따라 이 과정을 반복. 
	N명의 사람이 모두 제거될 때까지 계속 
	제거되는 사람의 순서 = 요세푸스 순열 
	
	연결 리스트? 
	배열? -> 배열로 풀어보자~ 
	
	배열은 무슨 그냥 큐로 풀어라 
	큐에서 K만큼 원소 빼고 뒤에다 집어넣어! 
*/

public class BJ_1158_요세푸스문제 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// 큐를 사용해보자 
		Deque<Integer> que = new ArrayDeque<>();
		
		// que에 인덱스 집어 넣기 
		for(int i = 1 ; i < N+1; i++) {
			que.offerLast(i);
		}
		
		sb.append('<');
		
		// 큐가 빌 때 까지 실행
		while(!que.isEmpty()) {
//			System.out.println(que);
			for(int i = 0; i < K; i ++) {
				int x = que.pollFirst();
				que.offerLast(x);
			}
			if(que.size() == 1) {
				sb.append(que.pollLast() + ">");
				break;
			}
			sb.append(que.pollLast() + ", ");
		}

		System.out.println(sb);
		
	}
	


}
