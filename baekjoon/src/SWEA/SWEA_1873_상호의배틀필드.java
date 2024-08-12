package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 17.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
 * @git
 * @youtube
 * @performance
 * @category #
 * @note
 * 
 * 		문자 의미 . 평지(전차가 들어갈 수 있다.) 벽돌로 만들어진 벽 # 강철로 만들어진 벽 - 물(전차는 들어갈 수 없다.) ^
 *       위쪽을 바라보는 전차(아래는 평지이다.) v 아래쪽을 바라보는 전차(아래는 평지이다.) < 왼쪽을 바라보는 전차(아래는
 *       평지이다.) > 오른쪽을 바라보는 전차(아래는 평지이다.)
 * 
 * 
 *       문자 동작 U Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다. D Down
 *       : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다. L Left : 전차가 바라보는
 *       방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다. R Right : 전차가 바라보는 방향을 오른쪽으로
 *       바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다. S Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을
 *       발사한다.
 * 
 *       포탄 발사 -> 직진 벽 : 포탄이 소멸 벽돌로 만들어진 벽 : 벽 파괴 강철 벽 : 아무일 x
 * 
 * 
 */
public class SWEA_1873_상호의배틀필드 {

	static char[][] map;

	static int d; // 전차의 방향을 저장할 변수 0 위쪽 1 아래쪽 2 왼쪽 3 오른쪽

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	// 전차의 위치
	static int tankX;
	static int tankY;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
//		System.out.println(T);

		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			// 맵 정보 입력받기
			map = new char[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				String temp = st.nextToken(); // 스트링으로 받고 하나씩 쪼개서 배열에 넣기
				for (int w = 0; w < W; w++) {
					map[h][w] = temp.charAt(w);
					// 전차가 바라보는 상황과 위치
					switch (map[h][w]) {
					case '^':
						d = 0;
						tankX = h;
						tankY = w;
						break;
					case 'v':
						d = 1;
						tankX = h;
						tankY = w;
						break;
					case '<':
						d = 2;
						tankX = h;
						tankY = w;
						break;
					case '>':
						d = 3;
						tankX = h;
						tankY = w;
						break;
					}
				}
			}

//			for(char cs [] : map) {
//				for(char c : cs) {
//					System.out.print(c);
//				}
//				System.out.println();
//			}
//			
//			System.out.println(tankX + " : " + tankY);
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
//			System.out.println(N);

			// 탱크 행동 받음
			st = new StringTokenizer(br.readLine());
			char[] op = st.nextToken().toCharArray();

//			System.out.println(Arrays.toString(op));

			// 여기에서부터 로직 시작!

			for (int o = 0; o < N; o++) {
//				for (char cs[] : map) {
//					for (char c : cs) {
//						System.out.print(c);
//					}
//					System.out.println();
//				}
//				System.out.println();
//
//				System.out.println("전차의 현재 위치 " + tankX + " : " + tankY);
				// 얼만큼 멀리 쏠지
				int l = 0;
				switch (op[o]) {

				case 'U':
					d = 0;
					map[tankX][tankY] = '^';
					if (tankX > 0 && map[tankX - 1][tankY] == '.') {
						map[tankX - 1][tankY] = map[tankX][tankY];
						map[tankX][tankY] = '.';
						tankX -= 1;
					}
					break;
				case 'D':
					d = 1;
					map[tankX][tankY] = 'v';
					if (tankX < H - 1 && map[tankX + 1][tankY] == '.') {
						map[tankX + 1][tankY] = map[tankX][tankY];
						map[tankX][tankY] = '.';
						tankX += 1;
					}
					break;
				case 'L':
					d = 2;
					map[tankX][tankY] = '<';
					if (tankY > 0 && map[tankX][tankY - 1] == '.') {
						map[tankX][tankY - 1] = map[tankX][tankY];
						map[tankX][tankY] = '.';
						tankY -= 1;
					}

					break;
				case 'R':
					d = 3;
					map[tankX][tankY] = '>';
					if (tankY < W - 1 && map[tankX][tankY + 1] == '.') {
						map[tankX][tankY + 1] = map[tankX][tankY];
						map[tankX][tankY] = '.';
						tankY += 1;
					}

					break;
				case 'S':
					// 포탑 쏘기
					switch (d) {
					// 위로 쏘기
					case 0:
						l = tankX;
						while (true) {
							if (l - 1 < 0 || map[l - 1][tankY] == '#')
								break; // 강철 벽 만나거나 범위 밖이면 끝
							if (map[l - 1][tankY] == '*') {
								map[l - 1][tankY] = '.';
								break;
							}
							l--;
						}
						break;
					// 이래로 쏘기
					case 1:
						l = tankX;
						while (true) {
							if (l + 1 >= H || map[l + 1][tankY] == '#')
								break; // 강철 벽 만나거나 범위 밖이면 끝
							if (map[l + 1][tankY] == '*') {
								map[l + 1][tankY] = '.';
								break;
							}
							l++;
						}
						break;
					// 왼쪽으로 쏘기
					case 2:
						l = tankY;
						while (true) {
							if (l - 1 < 0 || map[tankX][l - 1] == '#')
								break; // 강철 벽 만나거나 범위 밖이면 끝
							if (map[tankX][l - 1] == '*') {
								map[tankX][l - 1] = '.';
								break;
							}
							l--;
						}
						break;
					// 오른쪽으로 쏘기
					case 3:
						l = tankY;
						while (true) {
							if (l + 1 >= W || map[tankX][l + 1] == '#')
								break; // 강철 벽 만나거나 범위 밖이면 끝
							if (map[tankX][l + 1] == '*') {
								map[tankX][l + 1] = '.';
								break;
							}
							l++;
						}
						break;
					}

				}
			}

//			sb.append("#" + t + " ");
//			for (char cs[] : map) {
//				for (char c : cs) {
//					sb.append(c);
//				}
//				sb.append("\n");
//			}
			
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
		}
		System.out.println(sb);
	}

}
