/*
* 	<문제>
*	N개의 정수가 주어지면 최솟값과 최댓값을 구하는 프로그램을 작성하시오.
*
* 	<입력>
*	첫째줄 : 정수의 개수 N (1<=N<=1,000,000)
*	둘째줄 : N개의 정수가 공백으로 주어짐 (-1,000,000 <= 정수 <= 1,000,000)
*
* 	<출력>
*	첫째줄에 정수 N개의 최솟값, 최댓값 공백으로 구분해 출력
*
* 	<풀이>
*	List를 선언해 N개의 정수를 받는다
* 	List를 정렬한다
* 	가장 처음의 값과 마지막 값을 출력한다*
*
* */



package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_10818_최소최대 {

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		int N = Integer.parseInt(st.nextToken());
//
//		int min = Integer.MAX_VALUE;
//		int max = Integer.MIN_VALUE;
//
//		st = new StringTokenizer(br.readLine());
//		List<Integer> result = new ArrayList<>();
//		for(int i = 0; i < N; i++) {
////			int temp = Integer.parseInt(st.nextToken());
////			min = Math.min(min, temp);
////			max = Math.max(max, temp);
//			result.add(Integer.parseInt(st.nextToken()));
//		}
//
//		result.sort((o1, o2) -> o1-o2);
//
//		System.out.println(result.get(0) + " " + result.get(result.size()-1));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++){
			list.add(Integer.parseInt(st.nextToken()));
		}

		list.sort((o1, o2) -> o1-o2);

		sb.append(list.get(0)).append(" ").append(list.get(list.size()-1));
		System.out.println(sb);

	}

}
