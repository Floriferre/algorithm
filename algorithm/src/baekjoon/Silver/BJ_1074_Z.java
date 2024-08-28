package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 14.
@see https://www.acmicpc.net/problem/1074
@git
@youtube
@performance
@category #
@note 
	크기가 2^n * 2^n 배열 Z모양 탐색
	한 변의 길이가 2가 될 때까지 잘라서 내려가기? 
	2 -> 1 -> 3 -> 4 사분면 순으로 방문
	N이 주어졌을 때 r행 c열을 몇 번째로 방문하는가? 


*/

public class BJ_1074_Z {

	static int N, R, C;
	
	static int [][] map;
	
	static int cnt = 0;
	static int answer = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int l = (int)Math.pow(2, N);
		
//		map = new int [l][l];
		
//		int temp = 0;
//		// 2 사분면
//		if(R < l && C < l) {
//			temp = 0;
//			search(0, 0, l);
//		}else if(R < l && C > 1) {
//			temp += l*l;
//			search(0, l, l);
//		}else if(R > l && C < l) {
//			temp += l*l*2;
//			search(l, 0, l);
//		}else {
//			temp += l*l*3;
//			search(l, l, l);
//		}
		
		search(R, C, l);
		System.out.println(cnt);
		
	}
	
	private static void search(int r, int c, int size) {
		if(size == 1) {

			return;
		}
		
		// 재귀 4개는 2 -> 1-> 3->4 순으로 진행할 것
		int half = size/2;
		if(r<half && c<half) {
			search(r, c, half);			
		}
		else if(r <half && c >= half) {
			cnt += size*size/4;
			search(r, c - half, half);	
		}
		else if(r >= half && c < half) {
			cnt += (size*size/4)*2;
			search(r - half, c, half);			
		}else {
			cnt += (size*size/4)*3;
			search(r - half, c - half, half);
		}
	
	}

}
