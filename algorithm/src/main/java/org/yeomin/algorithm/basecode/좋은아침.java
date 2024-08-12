package basecode;

import java.util.Arrays;

/*
@author 정여민
@since ${date}
@see
@git
@youtube
@performance
@category #
@note 
*/

// 매일 아침에 와서 다시 짜보기! 
public class 좋은아침 {
	static char[] src = { 'a', 'b', 'c', 'd' };

	public static void main(String[] args) {
//		// 4P3을 구하는 코드를 작성해보자 	
//		makePermutation(0, new char [3] , new boolean [src.length]);
//		makeDupPermutation(0, new char [3]);
		// 4C3을 구하는 코드
//		makeCombination(0, 0, new char[3]);
//		makeCombinationDup(0, 0, new char[3]);

		// 부분집합을 구해보자
//		makeSubset1(0, new boolean[4]);
//		makeSubset2();
		
		// nextPermutation으로 순열 구하기
		permutationByNP();

	}

	// 시나리오를 생각하면서 코드를 외우자!
	// 매개변수(결정요인) : 몇 번째 선택이냐
	// choosed : 선택된 녀석들을 담아둘 배열
	// visited : 한 번 쓴 애들 담아둘 변수
	// nthChoice : 얘는 결정 요인이므로 바뀌면 안 되니까.
	static void makePermutation(final int nthChoice, char[] choosed, boolean[] visited) {
		// 기저 조건
		if (nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}

		// 재귀 처리 (inductive part)
		for (int i = 0; i < src.length; i++) {
			// 중복체크
			if (!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = src[i];
				// nthChoice++ << 이건 nthChoice에 직접적으로 연관을 줘버리니까 안 됨! +1로 쓰기!
				makePermutation(nthChoice + 1, choosed, visited);
				visited[i] = false;
			}
		}
	}

	// 중복 순열 : 순열에서 중복 체크하려고 만든 부분만 다 없애며 됨!

	static void makeDupPermutation(final int nthChoice, char[] choosed) {
		// 기저 조건
		if (nthChoice == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}

		// 재귀 처리 (inductive part)
		for (int i = 0; i < src.length; i++) {
			// 중복체크

			choosed[nthChoice] = src[i];
			// nthChoice++ << 이건 nthChoice에 직접적으로 연관을 줘버리니까 안 됨! +1로 쓰기!
			makeDupPermutation(nthChoice + 1, choosed);

		}
	}

	// 부분집합은 중복 순열과 같다 -> 대신 선택을 했냐, 안 했냐로 나뉘는 true false 일 뿐!
	static void makeSubSet1(final int nthCheck, boolean[] status) {
		// 기저 조건
		if (nthCheck == status.length) {
//			System.out.println(Arrays.toString(choosed));
			printsubset(status);
			return;
		}

		// 재귀 처리 (inductive part)

		status[nthCheck] = true;
		makeSubSet1(nthCheck + 1, status);
		status[nthCheck] = false;
		makeSubSet1(nthCheck + 1, status);

	}

	// 바이너리 카운팅을 사용한 중복순열
	// 0과 1로 구성된 중복순열~ 요소가 있는지 판단할 때 1을 shift시켜서 &연산을 하면 그 요소가 있는지 없는지 구할 수 있음
	static void makeSubset2() {
		// 한 칸씩 왼쪽으로 민다
		for (int i = 0; i < 1 << src.length; i++) {
			System.out.print("[");
//			System.out.printf("%2d : %14s\n", i, Integer.toBinaryString(i));
			for (int j = 0; j < src.length; j++) {
				if ((i & (1 << j)) > 0) {
					System.out.print(src[j]);
				}
			}
			System.out.println("]");
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

	static void makeCombination(final int nth, final int startIndex, char[] choosed) {
		// 결정 요인은 무엇인가? -> 2개 : 몇 번째 선택, 시작 인덱스(몇번째 인덱스부터 고를 것인가?)
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}

		for (int i = startIndex; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombination(nth + 1, i + 1, choosed); // i+1인 것 잊지 말자!

		}

	}

	static void makeCombinationDup(final int nth, final int startIndex, char[] choosed) {
		// 결정 요인은 무엇인가? -> 2개 : 몇 번째 선택, 시작 인덱스(몇번째 인덱스부터 고를 것인가?)
		if (nth == choosed.length) {
			System.out.println(Arrays.toString(choosed));
			return;
		}

		for (int i = startIndex; i < src.length; i++) {
			choosed[nth] = src[i];
			makeCombinationDup(nth + 1, i, choosed); // i+1인 것 잊지 말자!

		}
	}
	
	
	static void permutationByNP() {
		// 1. 요소는 정렬되어있을 것!
		Arrays.sort(src);
		// 2. Do while로 np 사용하기
		do {	// 첫 녀석은 정렬되어있는 거니까
			System.out.println(Arrays.toString(src));
		}while(nextPermutation());
	}


	// 넥퍼는 무조건 뒤쪽부터 찾는다, 재귀 없이 짜는 순열 (근데 얘는 다음 순열을 구하는 거지, 순열을 구하는 것은 아님!)
	// 순열을 구하려면 반복분 돌려야 함! 
	static boolean nextPermutation() {
		// 1. 최고 정점 찾기
		int lastPeak = src.length - 1;

		// 최고 정점이 아니었으면 앞으로 움직이면서 찾기 (배열의 범위 안 벗어날 동안)
		while (lastPeak > 0 && src[lastPeak - 1] >= src[lastPeak]) {
			lastPeak--;
		}
		// 낭떠러지인 상황
		if (lastPeak == 0) {
			return false;
		}

		// 2. 새 지도자 찾아오기
		int nextBoss = src.length - 1;
		while (src[lastPeak - 1] >= src[nextBoss]) {
			nextBoss--;
		}
		
		// 3. 지도자의 세대교체
		swap(src, nextBoss, lastPeak-1);
		
		// 4. 새로운 조직의 시작!!! 뒤쪽의 정렬
		// 역졍렬 -> 정렬 은 퀵정렬 등 보다 swap이 더 빠르다
		for (int left = lastPeak, right = src.length-1; left < right; left++, right--) {
			swap(src, left, right);
		}
		return true;
	}
	
	// 넥퍼로 조합 만드려면 a,b,c,d -> 0, 1, 1, 1 로 치환하여 구하면 됨! 
	// 0 1 1 1 / 1 0 1 1 / 1 1 0 1 / 1 1 1 0

	static void swap(char [] src, int i, int j) {
		char temp = src[i];
		src[i] = src[j];
		src[j] = temp;
	}

}
