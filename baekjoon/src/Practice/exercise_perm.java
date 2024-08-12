package Practice;

import java.util.Arrays;

public class exercise_perm {
	static char [] src = {'a', 'b', 'c', 'd'};

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	static void perm(int nthChoice, char [] choosed, boolean [] visited) {
		
		// 종료 조건
		if(nthChoice == choosed.length){
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		
		// 재귀 처리
		
		for(int i = 0; i < src.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				perm(nthChoice+1, choosed, visited);
				visited[i] = false;
			}
		}
	}
	
	static void Dupperm(int nthChoice, char [] choosed) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length; i++) {
			choosed[nthChoice] = src[i];
			Dupperm(nthChoice+1, choosed);
		}
	}

}
