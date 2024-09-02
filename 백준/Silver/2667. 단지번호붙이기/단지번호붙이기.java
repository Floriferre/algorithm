/*
@author 
@since 2024-09-2, 월, 14:59
@see    https://www.acmicpc.net/problem/2667
@git
@youtube
@performance
@category #
@note
    <문제>
    정사각형 지도.
    1 : 집이 있는 곳
    0 : 집이 없는 곳
    연결된 집의 모임 = 단지, 번호를 붙이려고 함
    사방으로 연결되어야 함! 대각선은 연결 X
    단지수를 출력하고, 각 단지에 속하는 집의 수 오름차순으로 정렬하려 출력

    <입력>
    첫째줄 : N (지도의 크기 5<=N<=25)
    N개의 줄 : 각각 N개의 자료

    <출력>
    첫번째줄 : 총 단지수
    단지네 집의 수 오름차순으로 정렬하여 한 줄에 하나씩 출력

    <풀이>
    BFS를 쓰는 문제 같음.
    사방으로 탐색 진행.
    탐색 가능한 곳까지 가서 번호 count
    visited 배열 선언해서 not visited인 곳만 탐색할 것!
    List에 단지 개수 넣고 나중에 정렬하기

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;   // 지도 크기
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static boolean visited[][];
    static int count;   // 단지 번호

    static int map[][];

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static List<Point> houses = new ArrayList<>();  // 집 목록 저장해둘 리스트
    static List<Integer> countHouse = new ArrayList<>();    // 집 개수 저장해둘 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];    // 지도 선언



        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for(int c=0; c<N; c++){
                map[r][c] = line.charAt(c) - '0';
                if(map[r][c] == 1){ // 집 위치 저장
                    houses.add(new Point(r, c));
                }
            }
        }

        visited = new boolean[N][N];    // 방문 여부 체크할 배열

        for(Point p : houses){
            count = 0;
            if(!visited[p.x][p.y]){  // 방문한 적 없으면 BFS 돌리기
                count = BFS(p, 0);
                countHouse.add(count);
            }
        }

        countHouse.sort((o1, o2) -> o1-o2);

        sb.append(countHouse.size()).append("\n");
        for(Integer i : countHouse){
            sb.append(i).append("\n");
        }

        System.out.println(sb);

    }

    // 단지 세기!
    public static int BFS(Point p, int count){
        Queue<Point> q = new ArrayDeque<Point>();
        q.add(p);
        visited[p.x][p.y] = true;
        count++;

        while(!q.isEmpty()){
            Point current = q.poll();

            for(int d = 0 ; d < 4; d++){
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];
                if(isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1){
                    q.add(new Point(nx, ny));
                    visited[nx][ny] = true;
                    count++;
                }
            }
        }
        return count;

    }

    // 범위 안에 있는지 체크
    public static boolean isIn(int nx, int ny){
        return nx >=0 && nx < N && ny>=0 && ny<N;
    }

}