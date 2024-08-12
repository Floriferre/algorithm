package baekjoon.Bronze;

import java.util.HashMap;
import java.util.Scanner;

public class BJ_3052_나머지 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for(int i = 0; i < 10; i++) {
			map.put(sc.nextInt()%42, 1);
		}
		System.out.println(map.size());
	}

}
