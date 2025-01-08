package baekjoon.SamSung;

/*
@author 정여민
@since 2025-01-7, 화, 9:26
@see https://www.acmicpc.net/problem/17070
@git
@youtube
@performance 384696KB 888ms(BFS) 14304KB 108ms (BFS)
@category #
@note
    <문제>
    새 집 : N x N, 1x1 정사각형 판으로 나누어져 있음
    각각의 칸은 (r, c)로 나타낼 수 있다
    r : 행 / c : 열. 각각 1부터 시작

    집 수리를 위해 파이프 하나를 옮기기.
    -- \ | 중 한 방향이 가능

    파이프는 빈 칸에만 가능. 파이프를 밀 수 있는 방향은 3가지. -> \ |
    파이프는 밀면서 회전 가능. 회전은 45도만 가능.

    파이프는 처음에 (1,1)과 (1,2)를 차지하고 있음. 방향은 가로.

    파이프의 한쪽 끝을 (N, N)로 이동시키는 방법의 개수

    <입력>
    첫째줄 : 집의 크기 N (3<=N<=16)
    N개의 줄 : 집의 상태
                빈칸 0
                벽   1

    <출력>
    파이프의 한쪽 끝을 (N, N)으로 이동시키는 방법의 수 출력
    이동시킬 수 없는 경우엔 0 출력

    방법의 수는 항상 1,000,000보다 작거나 같음

    <풀이>
    파이프를 이동할 수 있는 방향
    오른쪽
    아래
    오른쪽 대각선 아래

    파이프가 회전할 수 있는 것
    오른쪽 2 : 직선, 대각선
    세로 2 : 아래, 대각선
    오른쪽 대각선 아래 3 : 오른쪽, 세로, 대각선

    N의 최대 크기가 16이니까 DFS를 하면 터질 것 같고.
    BFS를 써야할 것 같다.

    세 방향으로 움직여보고, 가능하면 큐에 넣기
    파이프 한쪽 끝이 N,N에 닿으면 count 증가





*/

import java.util.*;
import java.io.*;

public class BJ_17070_파이프옮기기1 {

    static int result = 0;  // 결과
    static int N;   // 집의 크기
    static int[][] map; // 집

    public static class Point {
        int x;
        int y;
        int direction; // 방향 저장
        // 0 : 가로 / 1: 세로 / 2: 대각선

        public Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        // 집 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        Point start = new Point(0, 0, 0);  // 시작점
//
//        BFS(start);
//
//        sb.append(result);
//
//        System.out.println(sb);

        // DP로 푸는 것 0 가로, 1 세로, 2 대각선
        int [][][]dp = new int[N][N][3];
        dp[0][1][0] = 1;    // 초기값

        for(int i=0; i < N; i++){
            for(int j=2; j<N; j++){ // 왼쪽으로는 못 가니까

                if(map[i][j] == 0){ // 빈 칸이면
                    // 가로로 이동
                    // 이전 좌표에서 가로 or 대각선이었어야 함
                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];


                    // 세로로 이동
                    // 이전 좌표에서 세로 or 대각선
                    if(i-1 >= 0){ // 세로나 대각선으로 움직이려면 이전 좌표가 있어야 함
                        dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];

                        // 대각선으로 이동
                        // 이전 좌표의 방향이 어떻든간에 상관 X
                        if(map[i-1][j] == 0 && map[i][j-1]==0){
                            dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                        }
                    }
                }

            }
        }

        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);

    }

    public static void BFS(Point start) {
        ArrayDeque<Point> que = new ArrayDeque();
        que.offer(start);

        while (!que.isEmpty()) {
            Point current = que.poll(); // 다음 좌표 꺼내기

            switch (current.direction) {
                case 0: // 가로

                    if (isIn(current.x, current.y + 2) && map[current.x][current.y + 2] == 0) {   // 가로로 움직일 수 있으면
                        if (current.x == N - 1 && current.y + 2 == N - 1) {   // 만약 끝이 N-1, N-1에 닿았다면
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x, current.y + 1, 0));

                    }
                    if (isIn(current.x, current.y + 2) && map[current.x][current.y + 2] == 0 &&
                            isIn(current.x + 1, current.y + 1) && map[current.x + 1][current.y + 1] == 0 &&
                            isIn(current.x + 1, current.y + 2) && map[current.x + 1][current.y + 2] == 0) {    // 오른쪽 + 대각선
                        if (current.x + 1 == N - 1 && current.y + 2 == N - 1) {
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x, current.y + 1, 2));


                    }
                    break;
                case 1: // 세로
                    if (isIn(current.x + 2, current.y) && map[current.x + 2][current.y] == 0) {   // 세로로 움직일 수 있으면
                        if (current.x + 2 == N - 1 && current.y == N - 1) {
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x + 1, current.y, 1));

                    }
                    if (isIn(current.x + 2, current.y) && map[current.x + 2][current.y] == 0 &&
                            isIn(current.x + 1, current.y + 1) && map[current.x + 1][current.y + 1] == 0 &&
                            isIn(current.x + 2, current.y + 1) && map[current.x + 2][current.y + 1] == 0) {
                        if (current.x + 2 == N - 1 && current.y + 1 == N - 1) {
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x + 1, current.y, 2));

                    }
                    break;
                case 2: // 대각선
                    if (isIn(current.x + 1, current.y + 2) && map[current.x + 1][current.y + 2] == 0) {   // 가로로 움직이기
                        if (current.x + 1 == N - 1 && current.y + 2 == N - 1) {
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x + 1, current.y + 1, 0));
                    }

                    if (isIn(current.x + 2, current.y + 1) && map[current.x + 2][current.y + 1] == 0) {   // 세로로 움직이기
                        if(current.x+2==N-1 && current.y+1==N-1){
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x + 1, current.y + 1, 1));
                    }

                    if (isIn(current.x + 1, current.y + 2) && map[current.x + 1][current.y + 2] == 0 &&
                            isIn(current.x + 2, current.y + 1) && map[current.x + 2][current.y + 1] == 0 &&
                            isIn(current.x + 2, current.y + 2) && map[current.x + 2][current.y + 2] == 0) {  // 대각선 움직이기
                        if(current.x+2==N-1 && current.y+2==N-1){
                            result++;
                            continue;
                        }
                        que.add(new Point(current.x + 1, current.y + 1, 2));
                    }
                    break;
            }


        }
    }

    public static boolean isIn(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

}
