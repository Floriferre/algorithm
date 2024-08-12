package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
@author 정여민
@since 2023. 8. 21.
@see https://www.acmicpc.net/problem/1062
@git
@youtube
@performance
@category #
@note 
*	K개의 글자만 가르칠 수 있음
*	남극의 모든 단어는 anta로 시작하고 tica로 끝난다 
*	학생이 읽을 수 있는 단어의 최댓값을 구하여라 
*
*	딕셔너리 활용? 아니면 set 활용?
*
*/

public class BJ_1062_가르침 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		HashMap<Character, Integer> hm = new HashMap<>();
		
		hm.put('a', 1);
		hm.put('n', 1);
		hm.put('t', 1);
		hm.put('i', 1);
		hm.put('c', 1);
		
		HashMap<Character, Integer> hm2 = hm;
		
		int count = 0; 
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			for(int j = 0; j < s.length(); j++) {
				if(hm2.containsKey(s.charAt(j))) {
					continue;
				}else {
					hm2.put(s.charAt(j), 1);
				}
			}
		}

	}

}
