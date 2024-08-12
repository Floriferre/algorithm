package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 7.
@see https://www.acmicpc.net/problem/2493
@git
@youtube
@performance
@category #
@note 
	N개의 높이가 서로 다른 탑
	탑의 기둥에는 레이저 신호를 수신하는 장치
	레이저 신호는 가장 먼저 만나는 단 하나의 탑에서 수신 가능
	왼쪽 방향으로 레이저가 쏘아짐
	첫째줄 탑의 개수 N
	둘째줄 탑의 높이 N개
	출력 :
		레이저 신호를 수신하는 탑의 번호, 존재하지 않으면 0 출력
	** IDEA : 0번째에 아주 큰 기둥을 세워서 모든 레이저를 다 받을 수 있게 하는 것도 괜찮다! 
*/

public class BJ_2493_탑 {

	public static void main(String[] args) throws IOException {
		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  StringTokenizer st = new StringTokenizer(br.readLine());
		  StringBuilder	sb = new StringBuilder();

		  
		  // 탑의 갯수 N
		  int N = Integer.parseInt(st.nextToken());
		  
		  // 탑의 높이 저장할 배열, 인덱싱 편하게 하려고 N+1개로 선언
		  
		  st = new StringTokenizer(br.readLine());
		  
		  Stack<int []> stack = new Stack<>();
		  for(int i = 1; i < N+1; i++) {
			  int top = Integer.parseInt(st.nextToken());
			  // 스택이 비어있지 않는 동안
			  while(!stack.isEmpty()) {
				  // 레이저 수신이 가능한 경우
				  if(stack.peek()[1] > top) {
					  sb.append(stack.peek()[0] + " ");
					  break;
				  }
				  
				  stack.pop(); 
			  }
			  if(stack.isEmpty()) {
				  sb.append("0 ");
			  }
			  
			  stack.push(new int [] {i, top});
		  }
		  System.out.println(sb);
	}

}






























