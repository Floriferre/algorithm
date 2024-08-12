package baekjoon.Bronze;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BJ_2754_학점계산 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner (System.in);
	
		String s = sc.next();
		
		Map<String, String> score = new HashMap<>();
		
		score.put("A+", "4.3");
		score.put("A0", "4.0");
		score.put("A-", "3.7");
		score.put("B+", "3.3");
		score.put("B0", "3.0");
		score.put("B-", "2.7");
		score.put("C+", "2.3");
		score.put("C0", "2.0");
		score.put("C-", "1.7");
		score.put("D+", "1.3");
		score.put("D0", "1.0");
		score.put("D-", "0.7");
		score.put("F", "0.0");
		
		System.out.println(score.get(s));
		
	}

}
