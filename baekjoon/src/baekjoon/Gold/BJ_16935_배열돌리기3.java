package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 9.
 * @see https://www.acmicpc.net/problem/16935
 * @git
 * @youtube
 * @performance
 * @category #
 * @note N x M 배열, 배열에 연산을 R번 적용 1번 연산 : 상하 반전 -> 2중 포문 돌면서 상하 반전 시킬 것! 2번 연산 :
 *       좌우 반전 -> 2중 포문 돌면서 좌우 반전 시킬 것! 3번 연산 : 오른쪽으로 90도 회전 -> 다 2중 포문 4번 연산 :
 *       왼쪽으로 90도 회전 5번 연산 : 구역 4개 나누어서 시계방향 회전 6번 연산 : 구역 4개 나누어서 시계 반대방향 회전 =>
 *       전체적으로 2중 포문 돌면 될 것 같아서 시간 복잡도는 O(N^2) N, M이 짝수이고 100까지므로 해봤자 O(10^8)
 *       2초? 충분타
 * 
 *       <풀이> 첫째 줄 : 배열의 크기 N, M, 수행해야하는 연산의 수 R 둘째줄 ~ N개의 줄 : 배열의 원소 마지막 줄 : 연산
 *
 *
 */

public class BJ_16935_배열돌리기3 {
	static int N;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		// 배열 저장해둘 것
		int[][] board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 연산의 종류를 저장해둘 배열!
//		int [] numR = new int [R];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < R; i++) {
//			numR[i] = Integer.parseInt(st.nextToken());
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				board = op1(board);
				break;
			case 2:
				board = op2(board);
				break;
			case 3:
				board = op3(board);
				break;
			case 4:
				board = op4(board);
				break;
			case 5:
				board = op5(board);
				break;
			case 6:
				board = op6(board);
				break;
			}
		}
		for(int [] a : board) {
			for(int b : a) {
				sb.append(b + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	// 연산 1번 : 상하 반전
	public static int[][] op1(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = arr[N - 1 - i][j];
			}
		}

//		for(int [] a : temp) {
//			System.out.println(Arrays.toString(a));			
//		}
		return temp;
	}

	// 연산 2번 : 좌우 회전
	public static int[][] op2(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = arr[i][M - 1 - j];
			}
		}
//		for(int [] a : temp) {
//		System.out.println(Arrays.toString(a));			
//	}

		return temp;

	}

	// 연산 3번 : 오른쪽으로 90도 회전
	public static int[][] op3(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[M][N];

		for (int j = 0, r = 0; j < M; j++, r++) {
			for (int i = N - 1, c = 0; i >= 0; i--, c++) {
				temp[r][c] = arr[i][j];
			}
		}
//		for(int [] a : temp) {
//		System.out.println(Arrays.toString(a));			
//	}

		return temp;

	}

	// 연산 4번 : 왼쪽으로 90도 회전
	public static int[][] op4(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[M][N];

		for (int j = M - 1, r = 0; j >= 0; j--, r++) {
			for (int i = 0, c = 0; i < N; i++, c++) {
				temp[r][c] = arr[i][j];
			}
		}
//		for(int [] a : temp) {
//		System.out.println(Arrays.toString(a));			
//	}

		return temp;

	}

	// 연산 5번 : 4구열으로 잘라서 시계방향으로 회전 시켜서 넣기
	public static int[][] op5(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[N][M];

		// 2사분면 -> 1사분면
		for (int i = 0, r = 0; i < N / 2; i++, r++) {
			for (int j = 0, c=M/2; j < M / 2; j++,c++) {
				temp[r][c] = arr[i][j];
			}
		}
		// 1사분면 -> 4사분면
		for (int i = 0, r=N/2; i < N/2; i++, r++) {
			for (int j = M/2, c = M/2; j < M; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}

		// 4사분면 -> 3사분면
		for (int i = N / 2, r = N/2; i < N; i++, r++) {
			for (int j = M / 2, c=0; j < M; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}

		// 3사분면 -> 1사분면
		for (int i = N / 2, r = 0; i < N; i++, r++) {
			for (int j = 0, c=0; j < M/2; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}
		
//		for(int [] a : temp) {
//		System.out.println(Arrays.toString(a));			
//	}

		return temp;

	}

	// 연산 6번
	public static int[][] op6(int[][] arr) {
		int N = arr.length;
		int M = arr[0].length;
		int[][] temp = new int[N][M];
		
		// 2사분면 -> 3사분면
		for (int i = 0, r = N/2; i < N / 2; i++, r++) {
			for (int j = 0, c=0; j < M / 2; j++,c++) {
				temp[r][c] = arr[i][j];
			}
		}
		// 1사분면 -> 2사분면
		for (int i = 0, r=0; i < N/2; i++, r++) {
			for (int j = M/2, c = 0; j < M; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}

		// 4사분면 -> 1사분면
		for (int i = N / 2, r = 0; i < N; i++, r++) {
			for (int j = M / 2, c=M/2; j < M; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}

		// 3사분면 -> 4사분면
		for (int i = N / 2, r = N/2; i < N; i++, r++) {
			for (int j = 0, c=M/2; j < M/2; j++, c++) {
				temp[r][c] = arr[i][j];
			}
		}
		
//		for(int [] a : temp) {
//		System.out.println(Arrays.toString(a));			
//	}

		return temp;

	}

}
