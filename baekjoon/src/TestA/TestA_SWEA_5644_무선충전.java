package TestA;

import java.util.*;
import java.io.*;

/*
@author 정여민
@since 2023. 10. 12.
@see
@git
@youtube
@performance
@category #
@note 
*
*	<무선충전> 
*	BC의 충전 범위가 C일 때, BC와의 거리가 C이하면 BC에 접속 가능
*	거리 D = |x1 -x2| + |y1-y2| : 멘하탄 거리
*	BC 여러개에 접속 가능하면 둘 중 하나에서 가져옴 
*	같은 BC에 여러명 접속하면 1/n으로 가져감 
*	사용자는 1,1과 10,10 지점에서 출발 
*	사용자의 행동 궤적이 주어졌을 때 모든 사용자가 충전한 양의 합의 최댓값을 구하자
*
*	<제약사항>
*	N = 10
*	user는 2명, (1,1) (10,10)에서 출발 => x,y가 거꾸로 되어있다 
*	총 이동시간  (20 ≤ M ≤ 100)
*	BC개수 (1 ≤ A ≤ 8)
*	충전범위 (1 ≤ C ≤ 4)
*	성능P (10 ≤ P ≤ 500)
*	초기 위치부터 충전 가능
*	같은 위치에 2개 이상의 BC설치 
*	
*	<입력>
*	첫째줄 : 테스트케이스 개수 T
*	TC1 : 총 이동시간 M, BC개수 A
*	2개의 줄 : 사용자 A와 B의 이동 정보
*			0 이동하지 X, 1 상, 2 우, 3 하, 4 좌
*	A개의 줄 : BC정보
*
* 	<출력>
* 	모든 사용자의 충전량 합의 최대값
* 
* 	<풀이>
* 	1. 좌표, 움직임 담당하는 함수 
* 		- 상하좌우 좌표 계산 잘 할 것, 지금 주어진게 평소에 쓰던 것과 달리 x, y가 바뀌어 있다
* 	2. BC 클래스 : 좌표, C, P
* 	3. 해당 포인트에서 충전이 가능한지 검사하는 메서드
* 		- 유저 두 개의 리스트를 생성(배터리 중 어떤 것을 충전할 수 있는지 체크)
* 		- 유저별로 충전 가능하면 리스트에 배터리 넣기
* 		- 리스트 둘 다 크기가 0보다 크면 둘 다 충전 가능	
* 			- 리스트 2개 중첩 for문으로 돌면서 충전 가능한 거 최대 찾기
* 		- 하나만 0보다 크면 개 중 가장 큰 거 찾기
* 		- result에 더하기 
* 	4. 한칸씩 움직이면서 충전 가능한지 검사
*
*/

public class TestA_SWEA_5644_무선충전 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}

		public void move(int dir) {
			switch (dir) {
			case 1:
				x--; // 상
				break;
			case 2:
				y++; // 우
				break;
			case 3:
				x++; // 하
				break;
			case 4:
				y--; // 좌
				break;
			default:
				break;
			}
		}

	}

	static class BC {
		Point point;
		int C, P;

		public BC(Point point, int c, int p) {
			super();
			this.point = point;
			C = c;
			P = p;
		}

		@Override
		public String toString() {
			return "BC [point=" + point + ", C=" + C + ", P=" + P + "]";
		}
	}

	static int result = 0;
	static int M, A;
	static int dirA[];
	static int dirB[];
	static BC[] BCs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동량
			A = Integer.parseInt(st.nextToken()); // 배터리 개수

			// 사용자 A의 이동 정보
			st = new StringTokenizer(br.readLine());
			dirA = new int[M];
			for (int i = 0; i < M; i++) {
				dirA[i] = Integer.parseInt(st.nextToken());
			}

			// 사용자 B의 이동 정보
			st = new StringTokenizer(br.readLine());
			dirB = new int[M];
			for (int i = 0; i < M; i++) {
				dirB[i] = Integer.parseInt(st.nextToken());
			}

			// 충전기 정보 받기
			BCs = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				BCs[i] = new BC(new Point(x, y), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			solution();
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb);
	}

	private static void solution() {
		Point userA = new Point(1, 1); // 유저A의 좌표
		Point userB = new Point(10, 10); // 유저 B의 좌표

		charge(userA, userB); // 현재 위치에서 유저들이 충전이 가능한지 보기

		for (int i = 0; i < M; i++) {
			userA.move(dirA[i]);
			userB.move(dirB[i]);
			charge(userA, userB);
		}
	}

	private static void charge(Point userA, Point userB) {
		List<Integer> listA = new ArrayList<>(); // 충전 가능한 배터리 목록 저장할 리스트
		List<Integer> listB = new ArrayList<>();

		for (int i = 0; i < A; i++) {
			// A가 닿을 수 있는 범위면
			if (distance(userA.x, userA.y, BCs[i].point.x, BCs[i].point.y) <= BCs[i].C) {
				listA.add(i);
			}
			// B가 닿을 수 있는 범위면
			if (distance(userB.x, userB.y, BCs[i].point.x, BCs[i].point.y) <= BCs[i].C) {
				listB.add(i);
			}
		}
		
		// 어떤 걸 충전해야 max인지 구해보자 
		int max = 0;
		if(listA.size() > 0 && listB.size() > 0) {	// A와 B 모두 충전 가능한 애가 있을 때
			// 가능한 모든 조합 돌면서 최댓값 구하기
			for(int i : listA) {
				for(int j : listB) {
					int temp = 0;
					if(i==j) { // 같은 충전기면 
						temp = BCs[i].P;
					}else {
						temp += BCs[i].P;
						temp += BCs[j].P;
					}
					max = Math.max(max, temp);
				}
			}
		}else if(listA.size()>0) {	// A만 있을 때
			for(int i : listA) {
				if(max < BCs[i].P) {
					max = BCs[i].P;
				}
			}
		}else if(listB.size()>0) {	// A만 있을 때
			for(int i : listB) {
				if(max < BCs[i].P) {
					max = BCs[i].P;
				}
			}
		}
		
		result += max; 	// max 값 업데이트 
	}

	// 멘하탄 거리
	private static int distance(int x, int y, int x2, int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}

}
