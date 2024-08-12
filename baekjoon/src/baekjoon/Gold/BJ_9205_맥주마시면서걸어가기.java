package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 9. 18.
@see https://www.acmicpc.net/problem/9205
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	락페스티벌, 한 박스 맥주 20개. 50미터에 한 병씩 마시려고 함(50m가러면 그 직전에 맥주 한 병 마심)
*	편의점에 가면 빈 병은 버리고 새 맥주 사기 -> 맥주 20병 넘을 수 X
*	편의점에서 나섰을 때도 50미터 가기 전에 맥주 한 병 마시기
*
*	<입력>
*	첫째줄 : 테스트 케이스 개수 t
*	TC1 : 맥주 파는 편의점 개수 n
*	n+2개의 줄 : 상근이네 집, 현의점, 락 페스티벌 좌표
*	좌표 사이의 거리 = x좌표 차이 + y좌표의 차이
*
*	<풀이>
*	편의점에 들르면 무조건 맥주 사기
*	50m마다 맥주 먹기
*	BFS로 풀어보자! -> 편의점 위치를 리스트에 넣기
*	1. 현재 위치(시작점)와 페스티벌 위치 비교해서 <1000이면 종료
*	2. 아니면 현재위치와 편의점 위치 비교 -> 1000아래면 현재 위치를 편의점으로 업데이트
*	3. 같은 과정 반복
*	
*
*
*
*
*/

public class BJ_9205_맥주마시면서걸어가기 {

	private static class Point {
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

	}

	static Point house, festiv;
	static Queue<Point> convs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 편의점 개수

			// 집 좌표
			st = new StringTokenizer(br.readLine());
			house = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// 편의점 좌표
			convs = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				convs.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			st = new StringTokenizer(br.readLine());
			festiv = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			if (bfs(house)) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}

		}
		System.out.println(sb);

	}

	private static boolean bfs(Point start) {
		Queue<Point> que = new ArrayDeque<>();
		que.offer(start);

		while (!que.isEmpty()) {
			Point current = que.poll();

			if (distance(current.x, current.y, festiv.x, festiv.y) <= 1000) {
				return true;
			}

			int size = convs.size();
			while (size-- > 0) {
				Point conv = convs.poll();
				if (distance(current.x, current.y, conv.x, conv.y) <= 1000) {
					que.offer(conv);
				} else {
					convs.offer(conv);
				}
			}

		}
		return false;
	}

	// 맨하튼 거리
	private static int distance(int x, int y, int x2, int y2) {
		return Math.abs((x - x2)) + Math.abs((y - y2));
	}

}
