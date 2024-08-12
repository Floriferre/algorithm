package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10250_ACM호텔 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(st.nextToken());
		for(int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());	// 층 수
			int W = Integer.parseInt(st.nextToken());	// 방 수
			int N = Integer.parseInt(st.nextToken());	// 몇 번째 손님?
		
			// 층수는 N%H
			int floor, room;
			
			if(N%H==0) {
				floor = H;
				room = N/H;
			}
			else {
				floor = N%H;
				room = N/H + 1;
			}
			System.out.println(room<10? floor+"0"+room : floor+""+room);
		}
		

	}

}
