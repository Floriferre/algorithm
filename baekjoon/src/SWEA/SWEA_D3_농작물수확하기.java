package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class SWEA_D3_농작물수확하기 {
	
	static int [][] field;
	static int size;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();	
		
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			
			field = new int[size][size];
			
			// 필드 초기화
			for(int i = 0; i < size; i++) {
				String s = br.readLine();
				for(int j =0; j<size; j++) {		
					field[i][j] = s.charAt(j) -'0';
				}
			}
			
			int result = 0;
			// 시작과 끝 인덱스
			int start = size/2;
			int end = size/2;
			
			for(int i = 0; i<size; i++) {
				for(int j = start; j<=end; j++) {
					result += field[i][j];
				}
			
				// 윗쪽 절반일 때
				if(i<size/2) {
					start -= 1;
					end += 1;
				}
				// 아래쪽 절반일 때
				else {
					start += 1;
					end -= 1;
				}
			
			}
			sb.append("#").append(t+1).append(" ").append(result).append("\n");
		
		}
		System.out.println(sb);
		
	}

}
