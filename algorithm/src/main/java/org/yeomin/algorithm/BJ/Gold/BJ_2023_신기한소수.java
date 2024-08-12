package baekjoon.Gold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BJ_2023_신기한소수 {

		static int N;
		static StringBuilder sb;
		static int[] pn = {2,3,5,7};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
		N = sc.nextInt();
		sb = new StringBuilder();
	
		for(int i=0; i<pn.length; i++) {
			check(pn[i],1);
		}
		
		System.out.println(sb);
		
		
		
		
		// 좋은 방법이 아니었다.... 걍 배열 터졌음 
//		// 에라토스테네스의 체 원리를 쓰는 게 제일 좋을 것 같다 -> O(n log log n)
//		
//		int num = (int) Math.pow(10, N);
//		// 소수를 넣어둘 리스트 (소수면 값이 있고 없으면 0)
////		List<Integer> lst = new ArrayList<>();
//		
//		boolean [] visited = new boolean[num+1];
//		// false : 소수 
//		
//		// 1~N자리수까지 자릿수 생성
////		for(int i = 0; i < num+1; i++) {
////			lst.add(i);
////		}
////		lst.set(1, 0);
//		for(int i = 2; i < num+1; i++) {
//			// 이미 지워진 수라면 건너 뛰기
//			if(visited[i]) continue;
//			
////			if (lst.get(i) == 0) {
////				continue;
////			}
////			
//			// 아직 숫자가 남아있으면 그 배수부터 출발해서 가능한 모든 배수 지우기
//			for(int j = 2*i; j<num+1; j += i) {
////				lst.set(j, 0);
//				visited[j] = true;
//			}
//		}
//		
//		visited[0] = visited[1] = true;
//		
//		// 신기한 소수 구하기
//		boolean check = false;
//		for(int i = (int)Math.pow(10, N-1); i < Math.pow(10, N); i++) {
//			
//			if((!visited[i])) {
//				// true이면 소수가 아님
//				check = false;
//				int temp = i;
//				for(int j = 0; j < N; j++) {
//					if(!visited[temp]) {
//						temp /= 10;
//					}else {
//						check = true;
//						break;
//					}
//				}
//				if(!check) {
//					sb.append(i + "\n");
//				}
//				
//			}
//			// 신기한 소수 저장 
////			if(!check && lst.get(i)!=0) {
////				sb.append(lst.get(i) + "\n");				
////			}
//		}
//		
//		System.out.println(sb);
		
	}
	
	// 소수 num, num의 자릿수 count
	static void check(int num, int len) {
		// n자릿수라면
		if(len == N) {
			sb.append(num + "\n");
			return;
		}
		
		// 1~9까지 체크 (0은 생갹해도 됨 -> 0으로 끝나면 5의 배수로 무조건 소수가 불가능)
		for(int i = 1; i < 10; i++) {
			// 인자로 받은 수를 다른 수를 결합하여 
			int temp = 10*num + i;
			// 결합한 수가 소수라면 다음 재귀 호출
			if(isPrime(temp)) {
				check(temp, len+1);
			}
		}
	}
	
	// 소수 판별기
	static boolean isPrime(int num) {
		if(num<2) return false;
		
		for(int i =2 ; i <= Math.sqrt(num); i++) {
			if(num %i == 0) return false;
		}
		return true;
	}

}
