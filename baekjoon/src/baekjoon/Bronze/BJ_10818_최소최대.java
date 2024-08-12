package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_10818_최소최대 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		List<Integer> result = new ArrayList<>();
		for(int i = 0; i < N; i++) {
//			int temp = Integer.parseInt(st.nextToken());
//			min = Math.min(min, temp);
//			max = Math.max(max, temp);
			result.add(Integer.parseInt(st.nextToken()));
		}

		result.sort((o1, o2) -> o1-o2);
		
		System.out.println(result.get(0) + " " + result.get(result.size()-1));
	}

}
