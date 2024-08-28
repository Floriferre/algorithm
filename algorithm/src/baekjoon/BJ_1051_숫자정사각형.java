package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-22, 금, 16:14
@see https://www.acmicpc.net/problem/1051
@git
@youtube
@performance
@category #
@note
	<문제>
	N x M 크기의 직사각형
	각 칸에 숫자 적혀있음
	꼭짓점에 쓰여있는 수가 모두 같은 가장 큰 정사각현을 찾는 프로그램 작성
	정사각형은 행 또는 열에 평행해야만 한다

	<입력>
	첫째줄 : N M (1<= N, M <=50)
	둘째줄 : N개의 줄에 수가 주어진다
	
	<출력>
	첫째줄 : 정사각형의 크기 출력

	<풀이>
	1. 한 칸씩 진행하며 같은 숫자가 나올 때까지 + 맨 끝에 도달할때까지 한 변의 길이 증가시킨다
	2. 한 변이 정해지면 꼭짓점 4개 체크하기

*/
public class BJ_1051_숫자정사각형 {

	static int N, M;

	static int[][] square;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		square = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
		}
	}
}
