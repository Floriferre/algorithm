package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 22.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWBJKA6qr2oDFAWr&probBoxId=AYoaJoMabgADFAU6&type=PROBLEM&problemBoxTitle=0821%EC%A3%BC&problemBoxCnt=1
@git
@youtube
@performance
@category #
@note 
*	1, 2, 3, 4, ... , n 이 n개의 집합을 이루고 있음 
*	합집합 연산, 두 원소가 같은 집합에 포함되어있는지 확인하려는 연산 수행
*
*	<sol>
*	Union-Find를 사용해보자~ 
*
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : n(1<=n<=1,000,000), 연산의 개수 m(1<=m<=1,000,000)	
*	m개의 줄 :각각의 연산
*	합집합 : 0 a b 
*	같은 집합인지 확인 : 1 a b
*
*/

public class SWEA_3289_서로소집합 {
	
	static int n, m;
	static int parents[];
	
	private static void make() {
		parents = new int[n+1];
		for(int i = 0; i < n+1; i++) {
			parents[i] = i;
		}
	}
	
	
	private static int find(int a) {
		if(a==parents[a]) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 테스트 케이스 갯수
		int T = Integer.parseInt(st.nextToken());

		for(int t =1 ; t < T+1 ; t++){
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			make();
			
			sb.append("#" + t + " ");
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int check = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(check == 0) {	// 합집합 연산
					union(a, b);
				}else {	// 같은 집합인지 확인
					if(find(a) == find(b)) {
						sb.append(1);
					}else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
