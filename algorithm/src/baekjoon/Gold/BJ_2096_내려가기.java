package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 4.
@see https://www.acmicpc.net/problem/2096
@git
@youtube
@performance
@category #DP, 슬라이딩 윈도우
@note 
*	DP 문제인데, 메모리 제한이 있다 -> 슬라이딩 윈도우를 사용! 
*	dpMax, dpMin을 크기 3짜리로 선언해서 쓰자! 
*
*
*/

public class BJ_2096_내려가기 {
	
	static int [] dpMax = new int[3];
	static int [] dpMin = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		dpMax[0] = dpMin[0] = Integer.parseInt(st.nextToken());
		dpMax[1] = dpMin[1] = Integer.parseInt(st.nextToken());
		dpMax[2] = dpMin[2] = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int third = Integer.parseInt(st.nextToken());
			
			// 최대
			int maxLeft = dpMax[0]; int maxRight = dpMax[2];
			dpMax[0] = Math.max(dpMax[0], dpMax[1]) + first;
			dpMax[2] = Math.max(dpMax[1], dpMax[2]) + third;
			dpMax[1] = Math.max(Math.max(maxLeft, dpMax[1]), maxRight) + second;
			
			
			
			//최소
			int minLeft = dpMin[0]; int minRight = dpMin[2];
			dpMin[0] = Math.min(dpMin[0], dpMin[1]) + first;
			dpMin[2] = Math.min(dpMin[1], dpMin[2]) + third;
			dpMin[1] = Math.min(Math.min(minLeft, dpMin[1]), minRight) + second;
		}
		
		System.out.println(Math.max(Math.max(dpMax[0], dpMax[1]), dpMax[2]) + " " + Math.min(Math.min(dpMin[0], dpMin[1]), dpMin[2]));
		
	}

}
