package Lecture;

import java.util.Arrays;
import java.util.Scanner;

public class PermutationBitTest {

	static int N, R, input[], numbers[];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();

		input = new int[N];
		numbers = new int[R];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		
		long start = System.nanoTime();
		
		// 현재 아무것도 선택 X, Flag도 0
		permutation(0, 0);
		
		long end = System.nanoTime();
		
		System.out.println((end-start)/1_000_000_000.0);
		
		
	}

	private static void permutation(int cnt, int flag) { // cnt자리에 들어갈 수를 뽑기
		// 기저조건
		if (cnt == R) {
//			System.out.println(Arrays.toString(numbers));
			return;
		}

		for (int i = 0; i < N; i++) {
			// 중복체크
			if ((flag & 1 << i) != 0) { // 그 자리가 1이다
				continue; // 현재 그 수는 선택 중이므로 패스
			}
			// 수를 선택
			numbers[cnt] = input[i];
			// 다음 자리 수 뽑으러 가기 (현재 선택된 수를 마킹해서 보내야 함, |는 비트 Set!)
			permutation(cnt + 1, flag | 1 << i);
		}

	}

}
