package Practice;

import java.util.Arrays;

public class exercise_0803 {

	static char [] src;
	public static void main(String[] args) {
		

	}
	
	static void perm(int nthChoice, char [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				perm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	static void comb(int nthChoice, int StartIndex, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = StartIndex; i < src.length; i++) {
			comb(nthChoice+1, i+1, choosed);
		}
	}

}
