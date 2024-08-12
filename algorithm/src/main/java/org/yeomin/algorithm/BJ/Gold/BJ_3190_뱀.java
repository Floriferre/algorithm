package baekjoon.Gold;

import java.util.*;
import java.io.*;

/*
@author 정여민
@since 2023. 8. 31.
@see https://www.acmicpc.net/problem/3190
@git
@youtube
@performance
@category #
@note 
*	<문제>
*	뱀 이동
*	1. 몸 길이를 늘려 다음 칸에 위치
*	2. 벽이나 자기 자신의 몸과 부딪히면 게임 끝
*	3. 이동한 칸에 사과가 있으면 사과가 없어짐. (몸 길이 +1)
*	4. 사과가 없으면 꼬리 한칸 땡기기 (몸 길이 그대로)
*	
*	사과의 위치와 뱀의 이동 경로가 주어질 때 게임은 몇 초에 끝나는가?
*	
*	<입력>
*	첫째줄 : 보드 크기 N	(2<=N<=100)
*	둘째줄 : 사과의 개수 K (0<=K<=100)
*	K개 줄 : 사과 위치
*	다음 줄 : 뱀의 방향 변환 횟수 L (1<=L<=100)
*	L개의 줄 : 뱀의 방향 전환 정보
*		X C : 게임 시작 시간으로부터 X초가 끝난 후에 왼쪽(L) or 오른쪽(D)로 90도 방향 회전
*		1 <= X <= 10,000, 시간 순으로 정보가 주어짐 
*	
*	<출력>
*	게임이 몇 초에 끝나는가?
*
*	<풀이>
*	뱀의 이동방향은 오른쪽을 향하므로 -> dx, dy 배열 구성 시 우 > 하 > 좌 > 상 으로 구성하여 +1 -1을 통해 조절하자!
*	isNotOk : 종료 조건 확인
*		1) 벽에 닿았는디
*		2) 뱀 몸통에 닿았는지
*	play	: 게임 진행 
*		1) time : 몇 초동안 진행하는지 재기
*		2) 뱀 계속 진행시키며 
*			2-1) 위치 바꿔야하면 바꾸고
*			2-2) 사과 먹을 수 있으면 먹고 몸 길이 바꾸기
*			2-3) 범위 체크하기*
*
*/

public class BJ_3190_뱀 {

	static int[][] map; 	// 맵 정보 사과가 있으면 1, 없으면 0
	static List<int[]> snake = new ArrayList<>();	// 뱀의 위치(몸통이랑 머리랑 꼬리가 어디에 있는지 기록), 꼬리랑 머리 편하게 더하고 빼려고 리스트로 선언
	static int N, K, L;
	static HashMap<Integer, String> snakeMove = new HashMap<>();	// 뱀 움직임 저장
	
	// 우 > 하 > 좌 > 상 방향 
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 }; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());;
		N = Integer.parseInt(st.nextToken());	// 보드의 크기 
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());	// 사과의 개수

		map = new int[N][N];	// 보드 선언
		for (int i = 0; i < K; i++) {	// 사과 개수 입력받기
			st = new StringTokenizer(br.readLine());
			// 0 base 인덱스를 사용할 것이므로 1을 빼줌! 
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;	// 사과 있는 곳은 1로 표기
		}

		L = Integer.parseInt(br.readLine());	// 뱀의 방향 변환 횟수 

		for (int i = 0; i < L; i++) {	// 뱀 방향 변환 
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			String c = st.nextToken();
			snakeMove.put(x, c);	// 뱀 움직임 저장 (초, 방향)
		}

		play();

	}

	public static void play() {
		int currentX = 0, currentY = 0;	// 현재 뱀의 위치 
		int time = 0;	// 몇 초가 걸렸을까? 
		int d = 0;	// 뱀의 이동 방향을 결정지을 변수
		snake.add(new int[] { 0, 0 });	// 뱀의 처음 위치(꼬리), 뒤쪽으로 계속 머리 위치 추가할거니까 0번째가 꼬리!

		while (true) {	// 계속 반복
			// 1. 시간재기
			time++;

			// 2. 뱀 이동하기 : 처음에는 오른쪽 방향부터 
			int nx = currentX + dx[d];
			int ny = currentY + dy[d];

			// 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
			if (isNotOK(nx, ny))	// true이면 끝남! 
				break;

			// 4. 사과가 있을 때 없을 때 처리
			if (map[nx][ny] == 1) {	// 사과가 있으면
				map[nx][ny] = 0;	// 사과 먹어 치웠으니까 없애고
				snake.add(new int[] { nx, ny });	// 뱀 머리 위치 추가하기

			} else {
				snake.add(new int[] { nx, ny });	// 사과 없으면 머리는 더해주고
				snake.remove(0);	// 꼬리는 제거해준다
			}

			// 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
			if (snakeMove.containsKey(time)) { 	// 해당 초가 되면(뱀 움직임에 있는지 보고 있으면)
				if (snakeMove.get(time).equals("D")) {	// 해당 초의 움직임 전환 가져오는데, D면 우측으로 트니까
					d += 1;	
					if (d == 4)	// 0~4 인덱스 맞춰주기	
						d = 0;
				} else {
					d -= 1;
					if (d == -1)	// 인덱스 맞춰주기
						d = 3;
				}
			}

			// 6. 현재값 업데이트
			currentX = nx;
			currentY = ny;
		}

		System.out.println(time);
	}

	// 범위 밖을 벗어나거나 뱀의 몸에 닿으면 true 반환! 
	public static boolean isNotOK(int nx, int ny) {
		// 벽에 닿지 않으면 true
		if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
			return true;
		}
		
		// 뱀 위치에 속하면 true반환
		for (int i = 0; i < snake.size(); i++) {
			int[] t = snake.get(i);
			if (nx == t[0] && ny == t[1])
				return true;
		}
		// 다음으로 진행할 수 있으면 false
		return false;
	}

}
