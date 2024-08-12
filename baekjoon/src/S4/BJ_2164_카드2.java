package S4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/*
@author 정여민
@since 2023. 8. 4.
@see https://www.acmicpc.net/problem/2164
@git
@youtube
@performance 39896KB 284ms
@category #자료구조, 큐
@note 
	N장의 카드 -> 1~N까지 번호, 1번카드가 제일 위에, N번카드가 제일 아래
	반복:
		1. 제일 위에 있는 카드 버림
		2. 그 다음 카드를 제일 아래로 옮김 
	이 과정을 반복해야 하는데 어떤 자료구조가 유용할까? 
	=> '큐'를 사용해서 앞에서 빼고 뒤에서 더하고 과정을 반복해보자 
*/

public class BJ_2164_카드2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Deque<Integer> que = new ArrayDeque<>();
		
		for(int i = 1; i <N+1; i++) {
			que.offerLast(i);
		}
		
		int result = 0;
		while(true) {
			result = que.pollFirst();
			if(que.isEmpty()) break;
			int temp = que.pollFirst();
			que.offerLast(temp);
		}
		System.out.println(result);
	}

}
