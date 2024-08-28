package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

@author 정여민
@since 2023. 8. 10.
@see https://www.acmicpc.net/problem/17406
@git
@youtube
@performance
@category #
@note 
	배열 돌리기 2를 응용한 문제. 
	회전의 순서에 따라 결과가 달라지므로 -> 회전 순서를 순열로 구한 후 사용할 것! 
	계속 쓰이는 것들은 메소드로 분류해서 쓰자 
*
*
*/

public class BJ_17406_배열돌리기 {

	static int N, M, K;

	static int[][] board;
	static int[][] cycle;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        board = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
            	board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        cycle = new int[K][3];

        for(int k = 0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            cycle[k][0] = Integer.parseInt(st.nextToken()) - 1;
            cycle[k][1] = Integer.parseInt(st.nextToken()) - 1;
            cycle[k][2] = Integer.parseInt(st.nextToken());
        }
        
        
        Permutation(0, new int[K], new boolean[K]);

        System.out.printf("%d", min);
        
        
        
        

	}

	// 회전도 순서가 있음!
	public static void Permutation(int nthChoice, int[] choosed, boolean[] visited) {
		if (nthChoice == choosed.length) {
			doCycle(choosed);
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				choosed[nthChoice] = i;
				Permutation(nthChoice + 1, choosed, visited);
				visited[i] = false;
			}
		}
	}

	// 원본 배열이 변하면 안되니까 복사로 새로운 배열 만들기
	public static int[][] copyMap() {
		int[][] temp = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = board[i][j];
			}
		}
		return temp;
	}

	// 네 방향으로 나누어서 회전 진행 
	public static void doCycle(int[] choosed) {

		int[][] temp = copyMap();

		for (int k = 0; k < K; k++) {
			int r = cycle[choosed[k]][0];
			int c = cycle[choosed[k]][1];
			int S = cycle[choosed[k]][2];

			for (int s = 1; s <= S; s++) {
				// 위쪽
				int upTemp = temp[r - s][c + s];
				for (int y = c + s; y > c - s; y--) {
					temp[r - s][y] = temp[r - s][y - 1];
				}

				// 오른쪽
				int rightTmp = temp[r + s][c + s];
				for (int x = r + s; x > r - s; x--) {
					temp[x][c + s] = temp[x - 1][c + s];
				}
				temp[r - s + 1][c + s] = upTemp;
				// 아래
				int leftTmp = temp[r + s][c - s];
				for (int y = c - s; y < c + s; y++) {
					temp[r + s][y] = temp[r + s][y + 1];
				}
				temp[r + s][c + s - 1] = rightTmp;
				// 왼쪽
				for (int x = r - s; x < r + s; x++) {
					temp[x][c - s] = temp[x + 1][c - s];
				}
				temp[r + s - 1][c - s] = leftTmp;
			}

		}
		getAnswer(temp);

	}
	
	
	// 최솟값 계산하는 것 
	public static void getAnswer(int [][] temp) {
		for(int i=0; i<N; i++) {
	        int sum = 0;
	        for(int j=0; j<M; j++) {
	            sum += temp[i][j];
	        }
	        if(min > sum) min = sum;
	    }
	}
	
	
	

}
