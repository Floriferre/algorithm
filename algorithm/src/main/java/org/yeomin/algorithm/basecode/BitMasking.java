package basecode;

import java.util.Arrays;

public class BitMasking {
	static char [] src = {'a', 'b', 'c', 'd'};
	public static void main(String[] args) {
			
	}
	
	
	static void makePermutation(final int nthChoice, char [] choosed, boolean[] visited) {
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
	
	static void makeCombination(int nth, int StartIndex, char [] choosed) {
		if(nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = StartIndex; i <src.length; i++ ) {
			choosed[nth] = src[i];
			makeCombination(nth+1, StartIndex+1, choosed);
		}
	}
	
	static void makeSuSet1(final int nthCheck, boolean [] status) {
		if(nthCheck == status.length ) {
			printsubset(status);
			return;
		}
		
		status[nthCheck] = true;
		makeSuSet1(nthCheck+1, status);
		status[nthCheck] = false;
		makeSuSet1(nthCheck+1, status);
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
}
