package baekjoon.Silver;

import java.util.Scanner;

/**
@author 정여민
@since 2023. 8. 11.
@see https://www.acmicpc.net/problem/2839
@git
@youtube
@performance 17696KB 208ms
@category #수학, 다이나믹 프로그래밍, 그리디 알고리즘 
@note 
	설탕 배달 Nkg -> 3kg 5kg 
	최대한 적은 봉지 들고 가려고 한다 
	N/5 -> 시작위치 
	나머지를 3으로 나눌 수 있으면 굳
	아니면 N/5 -1 해서 다시 구하기 
*/


public class BJ_2839_설탕배달 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int five = N/5;
		int fivemod = N%5;
		
		boolean check = false;
		while(five >= 0) {
			// 최적해를 찾은 경우 
			if((fivemod)%3 == 0) {
				System.out.println(five+fivemod/3); 
				check = true;
				break;
			}
			
			five -= 1;
			fivemod += 5;
		}
		if(!check) System.out.println(-1);
		

	}

}
