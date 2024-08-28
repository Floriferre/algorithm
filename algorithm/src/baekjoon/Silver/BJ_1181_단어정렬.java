package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class BJ_1181_단어정렬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> list = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if(!list.containsKey(s)) {
				list.put(s, s.length());
			};
		}
		
		List<Map.Entry<String, Integer>> temp = new ArrayList<>(list.entrySet());
		
		temp.sort(new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {			
				if(o1.getValue() == o2.getValue()) {
					return o1.getKey().compareTo(o2.getKey());
				}
				
				
				return o1.getValue() - o2.getValue();
			}
		
		});
		
		for (Entry<String, Integer> entry : temp) {
			sb.append(entry.getKey()+"\n");
		}
	
		System.out.println(sb);
	}

}
