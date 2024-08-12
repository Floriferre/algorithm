package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산유효성검사_notTree{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int countN = 0;
		int countD = 0;
		
		for(int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			countN = 0;
			countD = 0;
			
			for(int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine());
//				
				String s [] = br.readLine().split(" ");
//				System.out.println(s[1]);
				if(s[1].equals("+") || s[1].equals("-") ||s[1].equals("*") ||s[1].equals("/")) {
					countD++;
				}else {
					countN++;
				}
			}
//			System.out.println("D:" + countD + " N:" + countN);
			
			if((countD-countN) == -1) {
				sb.append("#" + t + " " + "1" + "\n");				
			}else {
				sb.append("#" + t + " " + "0" + "\n");
			}
			
		
		}

		System.out.println(sb);
	}

}
