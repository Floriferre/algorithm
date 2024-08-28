package TestA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 10. 10.
@see https://swexpertacademy.com/main/talk/solvingClub/problemView.do?solveclubId=AYlH3z4K78kDFAVR&contestProbId=AWngfZVa9XwDFAQU&probBoxId=AYsWdiua8IADFAV6&type=PROBLEM&problemBoxTitle=1010%EC%A3%BC&problemBoxCnt=2
@git
@youtube
@performance
@category #
@note 
*
*	<문제>
*	창용마을에 N명이 살고 있다
*	1~N까지 번호 있음
*	두 사람은 서로 알고 있을 수도 아닐 수도
*	서로 연관되어있으면 하나의 무리라고 봄!
*	몇 개의 무리가 존재하는가?
*
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 창용마을에 사는 사람의 수 N, 서로를 알고 있는 사람 관계의 수 M
*	M개의 줄 : 서로를 알고 있는 두 사람의 번호
*
*	<출력>
*	테케마다 무리의 개수
*
*	<풀이>
*	두 가지 방법으로 가능
*	1. 유니온 파인드
*		1-1. 연관된 애들끼리 묶어주기 
*		1-2. 부모 개수 세기 
*	2. BFS 탐색 
*
*
*
*/

public class TestA_SWEA_7465_창용마을무리의개수 {

	// 유니온 파인드 풀어보자!
	static int[] parent;
	static int N, M;

	private static void make() { // 초기화 과정, 자기 자신을 부모로!
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
	}

	private static int find(int x) { // 입력 받은 애의 부모를 찾아보자
		if (parent[x] == x)
			return x;

		return parent[x] = find(parent[x]); // 가는 길에 전부 부모 업데이트
	}

	private static boolean union(int x, int y) {
		int parentX = find(x);
		int parentY = find(y);

		if (parentX == parentY) {
			return false;			
		}
		parent[parentX] = parentY; // 부모 값 업데이트해서 붙임
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t < T + 1; t++) {
			int result = 0;

			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			make();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				union(a, b);
			}

			List<Integer> lst = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				// 부모 다른 거 있는지 체크
				if (!lst.contains(find(i))) {
					lst.add(find(i));
				}
			}
			result = lst.size();
			
//			HashSet<Integer> hash = new HashSet<>();
//			for(Integer i: parent) {
//				hash.add(find(i));
//			}
//			
//			result = hash.size();

			sb.append("#" + t + " " + result + "\n");
		}
		
		System.out.println(sb);

	}

}
