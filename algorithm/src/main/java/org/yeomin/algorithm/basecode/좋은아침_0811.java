package basecode;

import java.util.Arrays;

public class 좋은아침_0811 {
	static char[] src = { 'a', 'b', 'c', 'd' };
	public static void main(String[] args) {
		

	}
	
	static void makePermutation(int nthChoice, char [] choosed, boolean [] visited) {
		if(nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = 0; i < src.length ; i++) {
			if(!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				makePermutation(nthChoice + 1, choosed, visited);
				visited[i] = false;
			}
		}
	}

	
	static void makeCombination(int nth, int StartIndex, char [] choosed) {
		if(nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}
		
		for(int i = StartIndex; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombination(nth+1, i, choosed);
		}
	}
	
	static void printsubset(boolean[] status) {
		System.out.print("[");
		for (int i = 0; i < status.length; i++) {
			if (status[i]) {
				System.out.print(src[i]);
			}
		}
		System.out.println("]");
	}
	
	
	static void makeSubSet1(int nthCheck, boolean [] status) {
		if(nthCheck == status.length) {
			printsubset(status);
			return;
		}
		
		status[nthCheck] = true;
		makeSubSet1(nthCheck+1, status);
		status[nthCheck] = false;
		makeSubSet1(nthCheck+1, status);
	}
	
	// 비트
	static void makeSubSet2() {
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
	
	static void permutationByNP() {
		// 1. 요소는 정렬해야 함
		Arrays.sort(src);
		
		// 2. Do while로 도리기
		do {
			System.out.println(Arrays.toString(src));
		}while(nextPermutation());
	}
	
	// 넥퍼 구현
	static boolean nextPermutation() {
		// 1. 최고 정점 찾기
		int lastPeak = src.length -1;
		
		// 최고 정점이 아니면 앞으로 움직이면서 찾기
		while(lastPeak > 0 && src[lastPeak -1] >= src[lastPeak]) {
			lastPeak--;
		}
		
		// 낭떠러지인 상황
		if(lastPeak == 0) {
			return false;
		}
		
		// 2. 새 지도자 찾기
		int nextBoss = src.length -1;
		while(src[lastPeak -1] >= src[nextBoss]) {
			nextBoss--;
		}
		
		// 지도자의 세대 교체
		swap(src, nextBoss, lastPeak -1 );
		
		// 4. 새로운 조직의 시작!! 뒤쪽의 정렬
		for(int left = lastPeak, right = src.length-1; left<right; left++, right--) {
			swap(src, left, right);
		}
		
		return true;
	}
	
	static void swap(char [] src, int i, int j) {
		char temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}
	
}




















