package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 10.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWgv9va6HnkDFAW0&probBoxId=AYnN7JhqMPoDFAUe&type=PROBLEM&problemBoxTitle=0807%EC%A3%BC&problemBoxCnt=7
@git
@youtube
@performance
@category # 순열, 브루트포스 
@note 
	규영이와 인영이의 카드게임♡ : 
		1~18까지의 카드를 각각 9장씩 나눠갖기
			규영이의 카드 순서 고정
				차례대로 한 장의 카드 크기 비교
					크기가 더 큰 사람이 두 카드의 합을 점수로 가져감 
		9개의 카드의 비교가 끝났을 때 점수가 더 많은 사람이 승!
	규영이가 이길 수 있는 가짓수와 지는 가짓수 구하기 
	NextPermutation을 써보자! <- nPn을 구할 때 유리
	 
	재귀를 써서 구하기엔 난... 가지치기 같은거 못 한다... 일단 다 구하고 생각해보자 
	100개를 6초안에 돌리면 되니까 될 것이다 아마도..? 

*/

public class SWEA_6808_규영이와인영이의카드게임 {

	static int [] lst;
	static int [] kyu;
	static int [] base;
	
	static int scoreKyu = 0;
	static int scoreIn = 0;
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 1; t <=T; t++) {
			scoreKyu = 0;
			scoreIn = 0;
			base = new int[19];
			kyu = new int [9];
			lst = new int [9];
			st = new StringTokenizer(br.readLine());			
			for (int i = 0; i < 9; i++) {
				kyu[i] = Integer.parseInt(st.nextToken());
				base[kyu[i]] = 1;
			}
			// lst : 인영이가 내는 카드 
			for(int i = 1, j = 0; i < 19; i++) {
				if(base[i] == 0) {
					lst[j++] = i;
				}
			}
			
			permutationByNP();
			
			sb.append("#" + t + " " + scoreKyu + " " + scoreIn + "\n");
			
		}
		System.out.println(sb);
	}
	
	static void permutationByNP() {
		// 1. 요소는 정렬되어 있을 것! 
		Arrays.sort(lst);
		
		// 2. do while문으로 NP 사용하기
		do {
			// 이제 여기에서 각 조합 별... 승패
			int tempKyu = 0;
			int tempIn = 0;
			
			// 규영이와 인영이의 각 라운드별 승패에 따른 점수 구하기 
			for(int i = 0; i < 9; i++) {
				if(kyu[i] > lst[i]) {
					tempKyu += kyu[i] + lst[i];
				}else{
					tempIn += kyu[i] + lst[i];
				}
			}
			
			// 이긴 사람의 횟수 + 1
			if(tempKyu > tempIn) {
				scoreKyu++;
			}
			else if(tempKyu < tempIn) {
				scoreIn++;
			}
			
//			System.out.println(Arrays.toString(lst));
		}while(nextPermutation());
	
	}
	
	
	
	static boolean nextPermutation() {
		// 1. 최고 정점 찾기 
		int lastPeak = lst.length-1;
		
		// 최고 정점이 아니었으면 앞으로 움직이면서 찾기(배열의 범위를 안 벗어나는 동안)
		while(lastPeak > 0 && lst[lastPeak -1] >= lst[lastPeak]) {
			lastPeak--;
		}
		
		// 낭떠러지인 상황
		if(lastPeak == 0) return false;
		
		// 2. 새 지도자 찾아오기 
		int nextBoss = lst.length -1;
		while(lst[lastPeak - 1] >= lst[nextBoss]) {
			nextBoss--;
		}
		
		// 3. 지도자의 세대 교체
		swap(lst, nextBoss, lastPeak-1);
		
		// 4. 새로운 조직의 시작!!! 뒤쪽의 정렬
		for(int left = lastPeak, right = lst.length-1; left<right; left++, right--) {
			swap(lst, left, right);
		}
		
		return true;
		
		
	}

	
	static void swap(int [] lst, int i, int j) {
		int temp = lst[i];
		lst[i] = lst[j];
		lst[j] = temp;
	}
}
