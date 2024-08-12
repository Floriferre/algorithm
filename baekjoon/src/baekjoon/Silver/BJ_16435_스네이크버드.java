package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 11.
@see https://www.acmicpc.net/problem/16435
@git
@youtube
@performance 14400KB 132ms
@category #그리디알고리즘 정렬
@note 
	스네이크버드 -> 귀여움!! 진짜 뱀의 목 같음 근데 새임 
	과일을 하나 먹으면 길이가 1만큼 늘어남
	자신의 길이보다 작거나 같은 높이에 있는 과일만 먹을 수 있음
	처음 길이가 L일 때 과일을 먹어 늘릴 수 있는 최대 길이는?
*/

public class BJ_16435_스네이크버드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int [] fruits = new int [N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(fruits);
		
		
		for(int i = 0; i < N; i++) {
			if(fruits[i] <= L) {
				L++;
			}else {
				break;
			}
		}
			
		System.out.println(L);
	}

}
