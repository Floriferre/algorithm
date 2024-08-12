package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 9.
@see https://www.acmicpc.net/problem/1966
@git
@youtube
@performance
@category #
@note 
	프린터 큐 : FIFO 구조 
	새로운 SW 
		1. 현재 큐의 가장 앞에 있는 문서의 중요도 확인
		2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 큐의 가장 뒤에 재배치 
			아니면 바로 인쇄
	현재 큐에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내자! 
	
	첫 줄 : 테스트케이스 수
		TC line1 : 문서의 개수 N, 몇 번째로 인쇄되었는지 궁금한 문서가 현재 큐에서 몇 번째에 놓여있는지 나타내는 정수 M
		TC line2 : 중요도 1~9, 중요도가 같은 문서가 여러 개 있을 수도 있다 /
	
	큐를 활용할까 우선순위 큐를 활용할까? 

*/

public class BJ_1966_프린터큐 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		// new Integer index, importance
		Queue<Integer []> que = new ArrayDeque<Integer[]>();

		// 우선 순위 큐를 사용해서 max heap을 만들면...? 현재까지 큐에서 최댓값이 무엇인지 알 수있음! 
		// 람다식을 활용하여 중요도를 기준으로 내림차순 (max heap 구성)
		PriorityQueue<Integer []> pq = new PriorityQueue<Integer []>((o1, o2) -> (o1[1]-o2[1])*-1);
		// reverse order : 최대힙
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t< T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i <N; i++) {
				int temp = Integer.parseInt(st.nextToken());
				que.offer(new Integer[] {i, temp});
				pq.offer(new Integer[] {i, temp});
			}
			
			int cnt = 0;
			while(true) {
				// 현재 큐의 가장 맨 앞의 원소의 중요도가 우선순위큐(max heap)에 있는 것의 중요도와 같으면 
				if(que.peek()[1] == pq.peek()[1]) {
					// 현재 큐의 가장 맨 앞의 원소의 인덱스가 우리가 인쇄해야할 문서이면 
					if(que.peek()[0] == M) {
						cnt++;
						break;
					}
					else {	// 인덱스가 다르면 같을 때까지 큐에서 제거(우선순위 큐에서도 제거)
						cnt++;
						que.poll();
						pq.poll();
					}
				}
				else {	// 중요도가 다르면 뽑아서 뒤에다 다시 넣어줌
					que.offer(que.poll());
				}
			}
			// 이후 사용을 위해 두 큐 비우기 
			que.clear(); pq.clear();
			sb.append(cnt + "\n");
		}
		System.out.println(sb);

	}

}
