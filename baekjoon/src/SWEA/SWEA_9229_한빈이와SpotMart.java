package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 8.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
@git
@youtube
@performance
@category # 투 포인터 
@note 
	과자 두 봉지, 각 과자는 aj 그램
	무게가 많이 나가는 과자 2개, M초과하면 무거워서 들고다닐 수 없음 
	과자의 최대 무게 합을 출력하라 
	조합 / 투포인터 / 이중 포문
*/

public class SWEA_9229_한빈이와SpotMart {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t < T+1; t++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int [] snacks = new int [N];
			for(int i = 0; i <N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(snacks);
			
			// 투 포인터 
			int l = 0;
			int r = 1;
			int sum = Integer.MIN_VALUE;
			int temp = snacks[l] + snacks[r];
			if(temp <= M) {
				sum = Math.max(sum, temp);					
			}
			while(l >=0 && l<r && r < N) {
				// 현재 배낭의 무게가 M이하일 때만 업데이트 
				if(temp <= M) {
					if(r < N-1) {
						temp -= snacks[r++] + snacks[l++];
						temp += snacks[l] + snacks[r];						
					}else break;
				}else {	// 과자 두 개의 무게가 M보다 크면 
					if(l >0) {
						temp -= snacks[l--];
						temp += snacks[l];						
					}else {
						break;
					}
				}
			}
			if(temp <= M) {
				sum = Math.max(sum, temp);					
			}
			if(sum == Integer.MIN_VALUE) {
				sb.append("#" + t + " " + -1 + "\n");				
			}else{
				sb.append("#" + t + " " + sum + "\n");
			}
		}
		
		System.out.println(sb);

	}

}
