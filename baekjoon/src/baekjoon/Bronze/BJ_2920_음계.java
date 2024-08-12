package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2920_음계 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		int [] tone = new int[8];
		for(int i = 0; i < 8; i++) {
			tone[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean asc = true;
		boolean des = true;
		
		for(int i = 1; i < 8; i++) {
			if(tone[i] > tone[i-1]) {
				des = false;
			}else asc = false;
		}
		
		if(asc) System.out.println("ascending");
		else if(des) System.out.println("descending");
		else System.out.println("mixed");
		
	}
}
