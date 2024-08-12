package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 22.
@see https://www.acmicpc.net/problem/1759
@git
@youtube
@performance 14232KB 132ms
@category #수학, 브루트포스, 조합론, 백트래킹
@note 
	사전순 -> 정렬 해서 쓰면 됨
	최소 한 개의 모음 -> 모음 중 1개 택
	최소 두 개의 자음 -> 자음 중 2개 택
	조합 문제다! 근데 겹치는 건 어떻게 하지? -> 결과를 hashSet에 담아버릴까? 그러면 중복인 거 안 나올 거 아냐. 



*/

public class BJ_1759_암호만들기 {

	HashSet<String> set = new HashSet<>();
	
	static StringBuilder sb = new StringBuilder();
	static char [] letters;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		letters = new char[C];
		int countV = 0;
		for(int i = 0; i < C; i++) {
			letters[i] = st.nextToken(" ").charAt(0);
		}
//		System.out.println(Arrays.toString(letters));
		Arrays.sort(letters);
//		System.out.println(Arrays.toString(letters));		

		combination(0, 0, new char [L]);
		System.out.println(sb);
		
	}
	
	private static void combination(int nth, int startIndex, char [] choosed) {
		if(nth == choosed.length) {
			int consonant=0;
			int vowel=0;
			for(int i = 0; i < choosed.length; i++) {
				if(choosed[i] == 'a' || choosed[i] == 'e' || choosed[i] == 'i' || choosed[i] == 'o' || choosed[i] == 'u' ) {
					vowel++;
				}else consonant++;
			}
			if(vowel>=1 && consonant >=2) {
				for(int i =0; i < choosed.length; i++) {
					sb.append(choosed[i]);
				}
//				System.out.println(Arrays.toString(choosed));		
				sb.append("\n");
				return;
			}
			
			return;
		}
		
		for(int i = startIndex; i < letters.length; i++) {
			choosed[nth] = letters[i];
			combination(nth+1, i+1, choosed);
		}
	}

}



















