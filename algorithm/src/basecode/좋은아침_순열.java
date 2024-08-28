package basecode;

import java.util.Arrays;

public class 좋은아침_순열 {
	static char [] src = {'a', 'b', 'c','d'};
	public static void main(String[] args) {
		
		int a = 10;
		int b = 20;
		
		calc(a, b);
		
		
		src = "hello java world".toCharArray();
		makePermuation(0, new char [3] , new boolean [src.length]);

	}
	static int cnt = 0;
	
	
	
	// 재귀를 이용해서 3개를 고르는 순열을 작성해보자
	
	public static void makePermuation(int nthCheck, char [] choosed, boolean [] visited) {
		
		System.out.println("hit count" + ++cnt);
		if(nthCheck == choosed.length) {
			System.out.println( Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthCheck] = src[i];
				makePermuation(nthCheck+1, choosed, visited);
				visited[i] = false;
			}
				
		}
	}
	
	static void calc(int a, int b) {
		System.out.println(a+b);
		System.out.println(a*b);
	}

}
