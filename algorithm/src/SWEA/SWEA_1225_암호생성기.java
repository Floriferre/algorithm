package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		Deque<Integer> que;
		
		for(int t = 0 ; t < 10; t ++) {
			
			que = new ArrayDeque<Integer>();
			st = new StringTokenizer(br.readLine());
			int test = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < 8; i++) {
				que.offerLast(Integer.parseInt(st.nextToken()));
			}
			
			// 
			boolean check = false;
			// 사이클 시작 - 1phase : 1 ~ 5까지 감소하는 시기
			int temp = -1;
			while(temp != 0) {
				for(int i = 1; i < 6; i++) {
					temp = que.pollFirst();
					temp -= i;
					if(temp <= 0) {
						temp = 0;
					}
					que.offerLast(temp);
					// 0 이 나오면 break
					if(temp == 0) {
						check = true;
						break;
					}
				}
				
			}
			// check true이면 0이 나온 것이고 
			
			sb.append("#").append(t+1);
			int x;
			System.out.println();
			while (que.peek() != 0) {
				x = que.poll();
				sb.append(" " + x);
			}
			sb.append(" " + 0 +"\n");
			
			
		}
		
		System.out.println(sb);
		
	}

}
