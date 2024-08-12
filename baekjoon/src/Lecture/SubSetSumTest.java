package Lecture;

import java.util.Scanner;

public class SubSetSumTest {

	static int N, TARGET, input[];
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		TARGET = sc.nextInt(); // 목표로 하는 SUM

		input = new int[N];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		generateSubset2(0, 0, 0);;
	}

	private static void generateSubset(int cnt) { // cnt : 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스

		if (cnt == N) { // 부분집합 완성

			// 부분 집합의 구성 원소의 합을 구하고 SUM과 비교
			int temp = 0, tCnt = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					temp += input[i];
					tCnt +=1;
				}
			}

			if (tCnt>0 && temp == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.print(input[i] + "\t"); // 원소가 true면 원소 주고 아니면 X
					}
				}
				System.out.println();
			}

			return;
		}

		// 부분집합에 넣는 경우
		isSelected[cnt] = true;
		generateSubset(cnt + 1);
		// 부분집합에 넣지 않는 경우
		isSelected[cnt] = false;
		generateSubset(cnt + 1);
	}
	
	private static void generateSubset2(int cnt, int sum, int selectedCount) { // cnt : 직전까지 고려된 원소의 개수, 현재 처리할 원소의 인덱스
																				// sum : 직전까지 선택된 원소들의 합
		if (cnt == N) { // 부분집합 완성

			if (selectedCount>0 && sum == TARGET) {
				for (int i = 0; i < N; i++) {
					if (isSelected[i]) {
						System.out.print(input[i] + "\t"); // 원소가 true면 원소 주고 아니면 X
					}
				}
				System.out.println();
			}

			return;
		}

		// 부분집합에 넣는 경우
		isSelected[cnt] = true;
		generateSubset2(cnt + 1, sum + input[cnt], selectedCount+1);	// 부분집합에 소속되니까 더해서 넘기기
		// 부분집합에 넣지 않는 경우
		isSelected[cnt] = false;
		generateSubset2(cnt + 1 ,sum, selectedCount);		// 부분집합에 소속되지 않으니까 sum만 넘기기 
	}

}
