package Lecture;

import java.util.Scanner;

public class MakeSpaceTest {

	static int spaces[][];
	static int white, green;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		spaces = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				spaces[r][c] = sc.nextInt();
			}
		}

		makeSpace(0, 0, N);
		System.out.println(white);
		System.out.println(green);
	}

	private static void makeSpace(int sr, int sc, int size) { // 영역의 자상단 r,c, 영역의 크기 size

		int sum = 0;

		for (int r = sr; r < sr + size; r++) {
			for (int c = sc; c < sc + size; c++) {
				sum += spaces[r][c];
			}
		}

		// 크기 1 체크 안 하는 이유 : 기저조건으로 하얀색일때 초록색일 때 있으니까
		if (sum == 0) { // 모두 하얀색인 공간(기저조건)
			white++;
		} else if (sum == size * size) { // 모두 초록색인 공간(기저조건)
			green++;
		} else { // 두 색이 섞여있는 공간
			int half = size / 2;
			makeSpace(sr, sc, half); // 2 사분면
			makeSpace(sr, sc + half, half); // 1사분면
			makeSpace(sr + half, sc, half); // 3사분면
			makeSpace(sr + half, sc + half, half); // 4사분면

		}

	}

}
