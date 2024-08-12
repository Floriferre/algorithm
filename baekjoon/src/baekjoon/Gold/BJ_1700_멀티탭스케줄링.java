package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-12, 화, 14:40
@see https://www.acmicpc.net/problem/1700
@git
@youtube
@performance
@category #
@note 
	<문제>
	준규의 멀티탭.
	각 정기용품별로 사용순서 주어짐.
	플러그 빼내는 횟수 최소화.

	ex) 3구 멀티탭
	순서 : 키보드 > 헤어드라이기 > 핸드폰 충전기 > 디지털 카메라 충전기 > 키보드 > 헤어드라이기
	=> 키보드, 헤어드라이기, 핸드폰 꽂은 후, 핸드폰 아웃 디지털 카메라 인 (플러그 한 번만 빼기)

	<입력>
	첫째줄 : 멀티탭 구멍 개수 N (1<=N<=100) 전기용품 총 사용 횟수 K (1<=K<=100)
	둘쨰줄 : 전기용품 이름이 K이하의 자연수로 사용 순서대로 주어짐

	<출력>
	하나씩 플러그를 빼는 최소의 횟수 출력

	<풀이>
	풀이1) 모든 경우를 다 커버할 수 없음!
	가장 많이 사용하는 거를 오래 꽂아둘수록 좋다
	1. K개의 int 배열을 만들어 각 전기용품별 사용량 저장
	2. K개의 boolean 배열을 만들어 false로 초기화
	3. 멀티탭 
	3-1. 가장 처음부터 사용하면 int 에서 -1
	3-2. visited 배열 true false 값 변경하기
	3-3. 계속 반복하는데, 멀티탭이 다 찬 경우에는, 사용이 제일 적게 남은 걸 없애기

	풀이2)
	1. 전기용품 사용 순서에 따라 탐색을 시작한다
	2. set 자료구조를 이용하여 멀티탭에 꽂은 전기용품을 저장한다.
	   2-1. 현재 꽂아져있는 전기용품이면 해당 탐색은 패스한다.
	   2-2. 멀티탭 구멍 갯수(n)보다 꽂아져있는 전기용품 갯수가 작고 중복이 되지 않는다면
	   새롭게 추가하고 나머지 탐색을 패스한다
   	3. 새로운 전기용품을 꽂아야한다면 현재 꽂아져잇는 전기용품 중 가장 나중에 사용되는 전기용품을 빼준다.
   	4. 3번 로직이 이루어지는 수를 카운트하여 출력해준다.

*/
public class BJ_1700_멀티탭스케줄링 {

	static int N;
	static int K;

	static int[] appliance;
	static boolean[] multitap;

	static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		appliance = new int[K + 1];
		Arrays.fill(appliance, 0);
		multitap = new boolean[K + 1];
		Arrays.fill(multitap, false);

		// 전기용품 갯수 넣기
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int temp = Integer.parseInt(st.nextToken());
			list.add(temp);
			appliance[temp]++;
		}

		// 멀티탭
		Set<Integer> set = new HashSet<>();

		// 처음부터 하나씩 꽂으면서 카운트
		int cnt = 0;
		for (int i = 0; i < K; i++) {
			int num = list.get(i);
			// 이미 꽂혀져있으면 패스
			if (set.contains(num)) {
				continue;
			}
			// 멀티탭에 꽂을 수 있으면 꽂기
			if (set.size() < N && set.add(num)) {
				continue;
			}

			// 멀티탭에 자리가 없으면
			int max = -1;    // 가장 나중에 쓰일
			int idx = -1;    // 가장 나중에 쓰일 전기용품 번호
			for (int s : set) {
				int tmp = 0;
				List<Integer> sub = list.subList(i + 1, K);    // 현재 이후의 전기 사용 순서
				// 해당
				if (sub.contains(s)) {    // 멀티탭에 꽂혀있는 것 중 나중에 사용될 거면
					tmp = sub.indexOf(s) + 1;    // 인덱스 위치 (리스트라서 0부터 시작하므로 +1)
				} else {    // 멀티탭에 안 꽂혀있는 것이면
					tmp = K - i - 1;    //걍 해당 인덱스 번호
				}

				if (tmp > max) {    // 인덱스 위치가 더 뒤인 것으로 업데이트
					max = tmp;
					idx = s;
				}
			}
			set.remove(idx);
			set.add(num);
			cnt++;
		}

		// int cnt = 0;
		// for (Integer i : list) {
		// 	if (countMultitaps(multitap) < N | multitap[i]) {    // 아직 멀티탭 구멍이 남아있거나 사용중인 것이면
		// 		multitap[i] = true;
		// 		appliance[i] -= 1;
		// 	} else {    // 멀티탭 구멍이 다 찬 경우
		// 		// 전기 용품 중 가장 사용도가 적게 남은 걸 빼기
		// 		int nth = 0;
		// 		appliance[0] = Integer.MAX_VALUE;
		// 		boolean check = false;
		// 		for (int j = 1; j < K + 1; j++) {
		// 			if (multitap[j]) {    // 이미 사용중인 멀티탭 중
		// 				/********************사용 안 하는 거 있으면 그거 뽑고**********************/
		// 				// if (appliance[nth] > appliance[j] && appliance[j] >= 0) {    // 사용량이 적게 남은 것 업데이트
		// 				// 	nth = j;
		// 				// }
		// 				if (appliance[j] == 0) {
		// 					nth = j;
		// 					cnt++;    // 콘센트 빼기
		// 					multitap[nth] = false;    // 꽂혀있던 것 빼기
		// 					multitap[i] = true;    // 새로 꽂기
		// 					appliance[i] -= 1;    // 사용했으니 줄이기
		// 					check = true;
		// 					break;
		// 				}
		// 			}
		// 		}
		// 		/*********************사용 안 하는 거 없으면 제일 나중에 쓸 거 뽑기************************/
		// 		if (!check) {
		// 			cnt++;    // 콘센트 빼기
		// 			multitap[list.get(list.size() - 1)] = false;    // 꽂혀있던 것 빼기
		// 			multitap[i] = true;    // 새로 꽂기
		// 			appliance[i] -= 1;    // 사용했으니 줄이기
		// 		}
		// 	}
		// 	// System.out.println(Arrays.toString(multitap));
		// }
		System.out.println(cnt);

	}

	// 사용중인 멀티탭 개수 세기
	public static int countMultitaps(boolean[] multitaps) {
		int count = 0;

		for (boolean b : multitaps) {
			if (b) {
				count++;
			}
		}
		return count;
	}
}
