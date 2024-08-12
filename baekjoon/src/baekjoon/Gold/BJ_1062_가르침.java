package baekjoon.Gold;

/*
@author 정여민
@since 2024-03-11, 월, 15:6
@see https://www.acmicpc.net/problem/1062
@git
@youtube
@performance 16164KB 324ms
@category #브루트포스 알고리즘 #비트마스킹 #백트래킹 #DFS
@note 
	<문제>
	학생들이 되도록 많은 단어를 읽도록.
	K개의 글자를 가르칠 시간.
	K개의 글자로 이루어진 단어만을 읽을 수 있음.
	어떤 K개의 글자를 가르쳐야 학생들이 읽을 수 있는 단어가 최대가 될까?

	남극의 모든 언어는 anta 로 시작되고, tica로 끝난다.
	남극언어에 단어는 N개밖에 없다고 가정.

	<입력>
	첫째줄 : 단어의 개수 N, K  (1<= N <= 50, 0 <= K <=26)
	둘째줄 : N개의 줄에 남극 언어. 단어는 영어 소문자. 8 이상 15이하 길이. 중복 단어 X

	<출력>
	첫째줄 : K개의 글자를 가르칠 때, 학생들이 읽을 수 있는 단어 개수의 최댓값 출력

	<풀이>
	남극 언어는 anta tica -> a, c, n, t, i 최소 5개 이상의 단어로 이루어져야한다

	단어 리스트에서
	1) a c n t i 글자 전부 제거
	2) set으로 변경
	3) 남은 글자 개수가 나옴
	3-1) 글자 다 합쳐서 리스트 -> set 으로 변경
	3-2) 해당 set으로 선택지 조합 돌리기
	3-3) 조합이 완성되면 몇 개의 단어를 읽을 수 있는지 카운트
		 set에서 단어를 하나씩 제거하며 단어가 남은 게 있으면 전체 단어 갯수에서 빼서 제일 높은 단어 수 찾기
	
	K가 5보다 작을 때는 바로 0 출력하면 됨

	Sol 2) 알파벳 26개 중 K개 선택 (DFS)
	K개 선택이 끝나면 해당 알파벳으로 단어 만들 수 있는지
	알파벳 배열 visited[26]


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1062_가르침 {

	static int N;
	static int K;

	static String[] words;

	static int count = 0;

	static boolean[] visited = new boolean[26];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 주어진 단어
		words = new String[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String temp = st.nextToken();
			// // a n c t i 글자 제거
			temp = temp.replace("anta", "");
			temp = temp.replace("tica", "");
			words[i] = temp;
		}

		visited[('a' - 'a')] = true;
		visited[('n' - 'a')] = true;
		visited[('c' - 'a')] = true;
		visited[('t' - 'a')] = true;
		visited[('i' - 'a')] = true;

		solution(0, 0, visited);
		System.out.println(count);
	}

	// DFS 형태
	public static void solution(int nth, int alpha, boolean[] visited) {

		// K 개의 알파벳을 뽑았으면
		if (nth == K - 5) {
			// 단어별로 가능한지 체크
			int tempCnt = 0;
			for (int i = 0; i < words.length; i++) {
				boolean check = true; // 단어가 불가한지 체크
				for (int j = 0; j < words[i].length(); j++) {
					if (visited[words[i].charAt(j) - 'a']) {
						continue;
					} else {
						check = false;
						break;
					}
				}

				if (check) {    // 단어 생성이 가능하면
					tempCnt++;
				}
			}
			count = Math.max(tempCnt, count);
			return;
		}

		for (int i = alpha; i < 26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				solution(nth + 1, i + 1, visited);
				visited[i] = false;
			}
		}
	}

}
