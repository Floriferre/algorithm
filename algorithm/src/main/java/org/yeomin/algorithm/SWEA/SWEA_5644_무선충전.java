package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_5644_무선충전 {

	// 좌표 클래스
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public void move(int dir) {
			switch (dir) {
			case 1:
				y--;
				break; // 상
			case 2:
				x++;
				break; // 우
			case 3:
				y++;
				break; // 하
			case 4:
				x--;
				break; // 좌
			}
		}
	}

	// 배터리 클래스
	static class BC {
		Point point; // 촤표
		int C, P; // 충전 범위, 처리량

		public BC(Point point, int c, int p) {
			super();
			this.point = point;
			C = c;
			P = p;
		}

	}

	static int M, A, res; // 총 이동 시간, BC개수, 최대값 결과
	static int[] dirA, dirB; // A, B 이동 정보
	static BC[] BCs; // BC 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC 개수

			BCs = new BC[A];
			res = 0;

			// A 이동 정보 저장
			dirA = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dirA[i] = Integer.parseInt(st.nextToken());
			}

			// B 이동 정보 저장
			dirB = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				dirB[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				// BC 정보 저장
				BCs[i] = new BC(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			solution();

			sb.append("#" + t + " " + res + "\n");
		}
		System.out.println(sb);
	}

	private static void solution() {
		// 초기 좌표 입력
		Point userA = new Point(1, 1);
		Point userB = new Point(10, 10);

		// 처음 좌표에서 충전 가능한지 판단
		charge(userA, userB);

		for (int i = 0; i < M; i++) {
			// A와 B 움직인 후 충전 가능한지 판단
			userA.move(dirA[i]);
			userB.move(dirB[i]);
			charge(userA, userB);
		}
	}

	private static void charge(Point userA, Point userB) {
		// A와 B 위치의 접속 가능한 BC 리스트
		List<Integer> listA = new ArrayList<>();
		List<Integer> listB = new ArrayList<>();

		// BC 개수만큼 반복
		for (int i = 0; i < A; i++) {
			// A와 각 BC와의 거리가 접속 가능하다면 (충전범위 C >= 거리 D)
			if (BCs[i].C >= distance(BCs[i].point.x, BCs[i].point.y, userA.x, userA.y)) {
				listA.add(i);
			}
			// B와 각 BC와의 거리가 접속 가능하다면 (충전범위 C >= 거리 D)
			if (BCs[i].C >= distance(BCs[i].point.x, BCs[i].point.y, userB.x, userB.y)) {
				listB.add(i);
			}

		}

		int max = 0, temp = 0;
		// A와 B 모두 접속 가능한게 1개 이상이라면
		if (listA.size() > 0 && listB.size() > 0) {
			// 완전 탐색으로 가능한 조합을 모두 비교하여 최대 처리량 P 구하기
			for (int i : listA) {
				for (int j : listB) {
					temp = 0;
					if (i == j) { // 같은 BC인 경우 처리량 나눠가지므로 1개만 더하기
						temp = BCs[i].P;
					} else { // 같은 BC가 아닌 경우 각각 처리량 더하기
						temp += BCs[i].P;
						temp += BCs[j].P;
					}
					max = Math.max(max, temp);
				}
			}
		} else if (listA.size() > 0) { // A가 접속 가능한 BC가 1개 이상이라면
			// 접속 가능한 BC 중 최대 처리량 P 구하기
			for (int i : listA) {
				if (max < BCs[i].P)
					max = BCs[i].P;
			}
		} else if (listB.size() > 0) { // B가 접속 가능한 BC가 1개 이상이라면
			// 접속 가능한 BC 중 최대 처리량 P 구하기
			for (int i : listB) {
				if (max < BCs[i].P)
					max = BCs[i].P;
			}
		}

		res += max; // 결과 누적

	}

	public static int distance(int x, int y, int x2, int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}

}
