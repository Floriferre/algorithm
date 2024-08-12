package basecode;

import java.util.Arrays;

public class 좋은아침_0813 {
	
	static char src [] = {'a', 'b', 'c', 'd'};

	public static void main(String[] args) {
		

	}
	
	private static void makePermutation(int nthChoice, char [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		// n개의 원소 중 r개를 뽑아 순서대로 나열한 것 nPn = n!
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				makePermutation(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	// n개의 원소 중 r개를 순서 없이 골라냄 n! / (n-r)! r!
	private static void makeCombination(int nthChoice, int StartIndex, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		for(int i = StartIndex ; i < src.length; i++) {
			choosed[nthChoice] = src[i];
			makeCombination(nthChoice+1, StartIndex+1, choosed);
		}
	}
	
	private static void subset1(int nth, boolean [] status) {
		if(nth == status.length) {
			// print subset
			return;
		}
		
		status[nth] = true;
		subset1(nth+1, status);
		status[nth] = false;
		subset1(nth+1, status);
	}
	
	private static void subset2 () {
		for(int i = 0; i < 1 << src.length; i++) {
			for(int j = 0; j < src.length; j++) {
				if((i & (1 << j))>0) {
					System.out.println(src[j]);
				}
			}
		}
	}
	
	private static void peintsubset(boolean [] status) {
		System.out.println("[");
		for(int i = 0; i < status.length; i++) {
			if(status[i]) {
				System.out.println(src[i]);
			}
		}
		System.out.println("]");
	}
	
	

}
