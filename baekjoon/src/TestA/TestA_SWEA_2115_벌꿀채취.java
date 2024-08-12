package TestA;

import java.io.*;
import java.util.*;

/*
@author 정여민
@since 2023. 10. 11.
@see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	N*N개의 벌통 배치
*	각 칸의 숫자 : 벌통에 있는 꿀의 양
*	벌꿏 채취 과정
*		1) 	두 명의 일꾼, 채취할 수 있는 벌통의 수 M
*			가로로 연속된 M개의 벌통 선택 
*			벌통이 겹치면 안 되고, 선택한 벌통에서 꿀 채취 가능
*		2) 	하나의 벌통에 있는 모든 꿀 채취
*			두 일꾼이 채취할 수 있는 꿀의 최대 양 C
*		3) 꿀의 수익 : 각 벌통에 있는 꿀의양^2
*
*	벌통들의 크기 N과 벌통에 있는 꿀의 양에 대한 정보, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C가 주어진다.
*	수익의 합이 최대가 되는 경우, 최대 수익 출력
*
*	<제약사항>
*	벌통 크기 N 3<=N<=10
*	벌통 개수 M 1<=M<=5
*	꿀 채취량 C 10 <= C <= 30
*	하나의 벌통에서 채취 가능한 꿀의 양 1 <= <= 9
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : N, M, C 벌통크기, 선택 가능한 벌통 개수, 채취 가능한 양 C
*
*	<출력>
*	두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익
*
*	<풀이>
*	1. 	벌통의 수익 계산
*		벌통의 크기 N이 최대 10이므로 반복으로 탐색을 하며 각 포인트부터 시작했을 때 수익 최댓값 미리 구해놓기
*		이때 선택된 M개의 벌통 중 일부는 사용하고 일부는 사용하지 않으므로 부분집합 사용
*		
*		M개 벌통을 부분집합을 통해 최대 수익 계상
*		- 가지치기 : 현재까지 sum > C  return;
*		- if(nth == M) cost 최댓값 업데이트 return
*		- 해당 자리 선택하는 경우 / 비선택 하는 경우
*	2. 	일꾼 2개 선택
*		1) 첫 번째 일꾼 선택
*			for(r)
*				for(c)
*
* 			첫번째 일꾼의 수익 골라두기
* 		2)	두 번째 일꾼 선택
* 			2-1) 같은 행에서 선택
* 			2-2) 다른 행에서 선택
* 					: r+1번째 행부터 선택 	
*
*
*/
public class TestA_SWEA_2115_벌꿀채취 {

	static int result;
	static int N, M, C;
	
	static int [][] map;
	static int [][] costHoneyMap;
	static int honeyCost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=1; t<T+1; t++) {
			result = Integer.MIN_VALUE;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 벌통 크기
			M = Integer.parseInt(st.nextToken());	// 한 번에 선택 가능한 벌통 개수
			C = Integer.parseInt(st.nextToken());	// 한 번에 채취 가능한 벌꿀 최대량
			
			map = new int[N][N];
			for(int i=0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 벌꿀 수익 맵 만들기 
			costHoneyMap = new int[N][N];
			for(int [] i : costHoneyMap) {
				Arrays.fill(i, 0);
			}
			
			getCostHoney(costHoneyMap);
//			for(int [] i : costHoneyMap) {
//				System.out.println(Arrays.toString(i));
//			}
			
			MaxLabor();
			
			
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}
	
	// 일꾼 두 명 고르기
	private static void MaxLabor() {	
		
		// 1번 일꾼 먼저 선택
		for(int i=0; i < N; i++) {
			for(int j=0; j <N-M+1; j++) {
				int costA = costHoneyMap[i][j]; 
				
//				System.out.println(i + " : i번째");
				// 2번 일꾼 선택 
				// 2-1. 같은 행일 때 
				int costB = 0;
				for(int k=j+M; k<N-M+1; k++) {
					costB = costHoneyMap[i][k];
					result = Math.max(result, costA + costB);
				}
				// 2-2. 다른 행일 때 
				for(int k=i+1; k<N; k++) {
					for(int d=0; d<N-M+1; d++) {
						costB = costHoneyMap[k][d];
//						System.out.println("k,d : " + k + " : " + d);
//						if(costA+costB > result) {
//							System.out.println("A의 행과 열 : " + i + " :" + j);
//							System.out.println("B의 행과 열 : " + k + " :" + d);
//							System.out.println("수익 : " + (costA + costB));
//						}
						result = Math.max(result, costA + costB);
					}
				}
				
			}
		}
	}
	
	
	// 최대 수익 맵 미리 만들어놓기 
	private static void getCostHoney(int [][] costHoneyMap) {
		for(int i=0; i < N; i++) {
			for(int j=0; j< N-M + 1; j++ ) {
				honeyCost = Integer.MIN_VALUE;	// 최대 수익
				subset(0, 0, 0, i, j);
				costHoneyMap[i][j] = honeyCost;
			}
		}
	}
	
	private static void subset(int nth, int sum, int maxSum, int row, int col) {	// 부분집합
		if(sum > C) {	// 현재까지 선택된 벌꿀의 양이 최대량 넘으면 볼 필요 X
			return;
		}
		
		// 종료 조건
		if(nth == M) {	// M개의 벌통을 전부 선택했으면
			// honeyCost 업데이트
			honeyCost = Math.max(honeyCost, maxSum);	// 현재까지의 최대 수익과 새로 얻은 최대 수익 중 max값 업데이트
			return;
		}
		
		// 해당 칸을 선택했을 때
		subset(nth+1, sum + map[row][col], maxSum + map[row][col]*map[row][col], row, col+1);
		// 해당 칸을 선택하지 않았을 때
		subset(nth+1, sum, maxSum, row, col+1);
		
		
	}

}
