package basecode;

import java.util.Arrays;

public class 좋은아침_0809 {
	static char[] src = { 'a', 'b', 'c', 'd' };
	public static void main(String[] args) {
		

	}
	
	
	static void makePermutation(int nthChoice, char [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				makePermutation(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	
	static void makeCombination(int nth, int startIndex, char [] choosed) {
		if(nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = startIndex; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombination(nth+1, i+1, choosed);
		}
		
	}
	
	
	static void printsubset(boolean [] status) {
		System.out.print("[");
		for(int i = 0; i < status.length; i++) {
			if(status[i]) {
				System.out.print(src[i]);
			}
		}
		System.out.println("]");
	}
	
	
	static void makeSubset(int nthCheck, boolean [] status) {
		if(nthCheck == status.length) {
			printsubset(status);
			return;
		}
		
		status[nthCheck] = true;
		makeSubset(nthCheck+1, status);
		status[nthCheck] = false;
		makeSubset(nthCheck+1, status);
	}
	
	static void makeSubset2() {
		for(int i = 0; i < 1 << src.length; i++) {
			System.out.println("[");
			
			for(int j = 0; j < src.length; j++) {
				if((i&(1<<j))>0) {
					System.out.println(src[j]);
				}
			}
			System.out.println("]");
		}
	}

}
