package basecode;

import java.util.Arrays;

public class 좋은아침_0828 {
	static char[] src = { 'a', 'b', 'c', 'd' };
	public static void main(String[] args) {

	}
	
	private static void perm(int nthChoice, char [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for (int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				perm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	private static void DuPerm(int nthChoice, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for (int i = 0; i < src.length; i++) {
			choosed[nthChoice] = src[i];
			DuPerm(nthChoice+1, choosed);
		}
	}
	
	
	private static void Comb(int nthChoice, int startIndex, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for (int i = startIndex; i < src.length; i++) {
			choosed[nthChoice] = src[i];
			Comb(nthChoice+1, i+1, choosed);
		}
	}
	
	private static void DuComb(int nthChoice, int startIndex, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for (int i = startIndex; i < src.length; i++) {
			choosed[nthChoice] = src[i];
			Comb(nthChoice+1, i, choosed);
		}
	}
	
	private static void SubSet1(int nth, boolean [] status) {
		if(nth == status.length) {
			printSubset(status);
			return;
		}
		
		status[nth] = true;
		SubSet1(nth+1, status);
		status[nth] = false;
		SubSet1(nth+1, status);
	}
	
	private static void Subset2() {
		for(int i = 0; i < 1<<src.length; i++) {
			System.out.println("[");
			for(int j = 0; j < src.length; j++) {
				if((i & (1<<j))>0) {
					System.out.println(src[i]);
				}
			}
			System.out.println("]");
		}
	}
	
	

	private static void printSubset(boolean [] status) {
		System.out.println("[");
		for (int i = 0; i < status.length; i++) {
			if(status[i]) {
				System.out.print(src[i]);
			}
		}
		System.out.println("]");
	}
}
