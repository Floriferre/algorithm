package Lecture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class DiceTest {

	static int N, numbers[];
	static boolean[] isSelected;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 입력으로 주사위 던지는 횟수와, 주사위 던지기 모드(1, 2, 3, 4 모드)

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 주사위 던지는 횟수 (0<N<7)
		int M = sc.nextInt(); // 주사위 던지기 모드

		numbers = new int[N]; // 던져진 주사위 수
		switch (M) {

		case 1: // 중복 순열
			dice1(0);
			break;

		case 2: // 순열
			isSelected = new boolean[7]; // 인덱스 기반으로 쓸거라
			dice2(0);
			break;
		case 3: // 종복 조합
			dice3(0,1); 	// start가 현재 인덱스가 아니라 주사위 눈이므로
			break;
		case 4: // 조합
			dice4(0, 1);
			break;

		}
	}

	// 중복된 주사위 눈 가능 (중복 순열)
	private static void dice1(int cnt) { // cnt+1번째 주사위 던지기, cnt : 기존까지 던져진 주사위 횟수(인덱스 기반이라 +1 해줌)

		if (cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 한 번 던질 때 가능한 상황에 대한 시도(1,2,3,4,5,6 주사위 눈이 가능)
		for (int i = 1; i <= 6; i++) {
			// cnt번째 주사위~
			numbers[cnt] = i;
			// 현 주사위의 눈이 i로 결정된 상태로 다음 주사위 던지러 가기
			dice1(cnt + 1);

		}
	}

	private static void dice2(int cnt) { // cnt+1번째 주사위 던지기, cnt : 기존까지 던져진 주사위 횟수(인덱스 기반이라 +1 해줌)

		if (cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 한 번 던질 때 가능한 상황에 대한 시도(1,2,3,4,5,6 주사위 눈이 가능)
		for (int i = 1; i <= 6; i++) {
			// 기존 주사위의 눈과 중복되는지 체크
			if (isSelected[i])
				continue;
			// cnt번째 주사위~
			numbers[cnt] = i;
			isSelected[i] = true;
			// 현 주사위의 눈이 i로 결정된 상태로 다음 주사위 던지러 가기
			dice2(cnt + 1);
			// 현 주사위의 눈을 다른 선택지로 시도하기 위한 준비 (방문 처리를 빼줘야 함!)
			isSelected[i] = false;

		}
	}

	// 중복 조합
	private static void dice3(int cnt, int start) { // 현재 뽑는 수, 시작 인덱스
		
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}

		for (int i = start; i <= 6; i++) { // i : 뽑는 수
			numbers[cnt] = i;
			dice4(cnt + 1, i); // 하나 뽑았으니 다름 것을 뽑을 건데, 현재 뽑은 i 이후의 것을 뽑아야함
		}

	}

	// 조합
	private static void dice4(int cnt, int start) { // 현재 뽑는 수, 시작 인덱스
		
		if(cnt == N) {
			System.out.println(Arrays.toString(numbers));
			return;
		}
		
		for (int i = start; i <= 6; i++) { // i : 뽑는 수
			numbers[cnt] = i;
			dice4(cnt + 1, i + 1); // 하나 뽑았으니 다름 것을 뽑을 건데, 현재 뽑은 i 이후의 것을 뽑아야함
		}
	}

}
