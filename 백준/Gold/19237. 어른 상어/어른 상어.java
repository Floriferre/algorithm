/*
@author 
@since 2025-02-17, 월, 11:8
@see https://www.acmicpc.net/problem/19237
@git
@youtube
@performance
@category #
@note
    <문제>
    청소년 상어 -> 어른 상어
    상어 1~M 자연수 번호가 있음
    1번 상어가 가장 강해서 다른 상어를 쫓아냄

    NxN 격자 중 M개의 칸에 상어가 한마리씩 들어있음
    맨 처음에 상어가 자기 냄새를 뿌림
    1초마다 모든 상어가 동시에 상하좌우 인접한 칸 중 하나로 이동하고, 자신의 냄새를 그 칸에 뿌림
    냄새는 상어가 k번 이동하고나면 사라짐

    1. 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 이동.
    2. 전부 냄새가 난다면 자기 냄새가 남은 칸으로 이동
    3. 가능한 칸이 여러개면 특정 우선순위를 따른다

    모든 상어가 이동한 후 한 칸에 여러마리 상어가 있으면 가장 작은 번호를 가진 상어를 제외하고 모두 쫓아내기

    <입력>
    첫째줄 : N M K  (2 ≤ N ≤ 20, 2 ≤ M ≤ N2, 1 ≤ k ≤ 1,000)
    N개의 줄 : 격자
    각 상어의 방향
    M*4줄 : 상어의 방향 우선순위

    <출력>
    1번 상어만 격자에 남게 되기까지 걸리는 시간
    단, 1,000초가 넘어도 다른 상어가 격자에 남아있으면 -1 출력

    <풀이>
    승자독식 모노폴리와 같은 문제인데 내가 뭔가 잘못 생각하는 것 같아서 다시 풀기.

    필요한 함수
    1) isIn : 격자 범위 안에 있는지 체크
    2) 다른 상어가 있는지 체크 : 종료조건 체크하기 위해
    3) 상어 움직이기
        3-1) 인접한 칸 중 아무 냄새가 없는 칸으로 이동 : 이때, temp 배열 사용해서 자기 칸으로 돌아가는 것인지 아니면 빈칸으로 이동한 것인지 판별할 것
        3-2) 인접한 칸 중 빈칸이 없으면 자기 칸으로 돌아가기
    4) 상어 영향력 줄이기

*/




import java.util.*;
import java.io.*;
public class Main {


    static class Shark{
        int num; // 상어 번호
        int x;  // 상어 위치
        int y;
        boolean isAlive;
        int direction;  // 상어 방향
        int [][] move;  // 상어 이동 우선순위

        public Shark(int num, int x, int y, boolean isAlive){
            this.num = num;
            this.x = x;
            this.y = y;
            this.isAlive = isAlive;
        }
    }

    static int n, m, k;
    static int [][][] map;  // x, y 좌표, 플레이어번호, 남은 계약수

    static ArrayList<Shark> sharks = new ArrayList();  // 플레이어 목록

    // 위 아래 왼 오
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // 격자 정보
        map = new int [n][n][2];

        for(int i=0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < n; j++){
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if(map[i][j][0] == 0){  // 빈칸이면 독점 X
                    map[i][j][1] = 0;
                }else{  // 빈칸이 아니면 독점 숫자 넣기
                    map[i][j][1] = k;
                    sharks.add(new Shark(map[i][j][0],  i, j, true));
                }
            }
        }

        sharks.sort((o1, o2) -> o1.num - o2.num);   // 상어 번호순으로 오름차순 정렬

        // 상어의 초기방향 d
        st = new StringTokenizer(br.readLine());
        for(Shark s : sharks){
            s.direction = Integer.parseInt(st.nextToken()) - 1; // 인덱스 0부터로 맞춰주기
        }

        // 상어의 방향에 따른 이동 우선순위
        for(Shark s : sharks){
            s.move = new int [4][4];    // 위 아래 왼 오
            for(int i=0; i < 4; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j < 4; j++){
                    s.move[i][j] = Integer.parseInt(st.nextToken()) - 1;    // 인덱스 0부터로 맞춰주기
                }
            }
        }

        int second = 0;  // 초 셀 변수
        while(true){

            
            if(second > 1000){  // 1000초가 넘었으면 끝내기
                second = -1;
                break;
            }
            if(!checkOtherSharkLife() ){ // 1번 상어 말고 다른 상어가 전부 죽었으면 끝내기
                break;
            }

            // 상어 움직이기
            moveSharks();

            // 상어 냄새 줄이기
            reduceSmell();

            second++;
        }

        sb.append(second);
        System.out.println(sb);

    }

    // 상어 움직이기
    static void moveSharks(){
//        3) 상어 움직이기
//        3-1) 인접한 칸 중 아무 냄새가 없는 칸으로 이동 : 이때, temp 배열 사용해서 자기 칸으로 돌아가는 것인지 아니면 빈칸으로 이동한 것인지 판별할 것
//        3-2) 인접한 칸 중 빈칸이 없으면 자기 칸으로 돌아가기

        // 이번 회차 움직임 담아둘 변수
        int [][][] temp = new int [n][n][2];

        // 1. 인접한 칸 중 아무 냄새가 없는 칸으로 이동
        for(Shark s : sharks){

            boolean isMoved = false;

            // 죽은 상어는 패스
            if(!s.isAlive){
                continue;
            }

            for(int d=0; d<4; d++){
                // 다음 칸
                int nx = s.x + dx[s.move[s.direction][d]];
                int ny = s.y + dy[s.move[s.direction][d]];

                if(isIn(nx, ny) && map[nx][ny][0] == 0){    // 다음 칸이 빈칸이면
                    // 근데 이미 누군가 있으면 사라지기
                    if(temp[nx][ny][0] != 0){
                        s.isAlive = false;
                        break;
                    }
                    // 빈칸으로 이동 후 다음 상어로
                    temp[nx][ny][0] = s.num;
                    temp[nx][ny][1] = k+1;
                    s.x = nx;
                    s.y = ny;
                    s.direction = s.move[s.direction][d];
                    isMoved = true;
                    break;
                }
            }
            // 빈 곳이 없고, 아직 상어가 살아있으면
            if(!isMoved && s.isAlive){
                for(int d=0; d<4; d++){
                    // 다음 칸
                    int nx = s.x + dx[s.move[s.direction][d]];
                    int ny = s.y + dy[s.move[s.direction][d]];

                    if(isIn(nx, ny) && map[nx][ny][0] == s.num){    // 다음 칸이 내가 독점한 곳이면
                        // 독점한 곳으로 이동 후 다음 상어로
                        temp[nx][ny][0] = s.num;
                        temp[nx][ny][1] = k+1;
                        s.x = nx;
                        s.y = ny;
                        s.direction = s.move[s.direction][d];
                        break;
                    }
                }
            }
        }

        // 원본 map 에 움직임 업데이트
        for(int i=0; i < n; i++){
            for(int j=0; j < n; j++){
                if(temp[i][j][0] != 0){ // 칸에 변화가 있으면
                    map[i][j][0] = temp[i][j][0];
                    map[i][j][1] = temp[i][j][1];
                }
            }
        }


//        System.out.println("**********************");
//        for(int i=0; i < n; i++){
//            for(int j=0; j < n; j++){
//                System.out.print(map[i][j][0]);
//            }
//            System.out.println();
//        }

    }


    // 상어 냄새 줄이기
    static void reduceSmell(){
        for(int i=0; i <n; i++){
            for(int j=0; j <n; j++){
                if(map[i][j][1] > 0){   // 냄새가 남아있으면 영향력 줄이기
                    map[i][j][1]--;
                    if(map[i][j][1] == 0){  // 냄새가 사라졌으면 독점 풀기
                        map[i][j][0] = 0;
                    }
                }
            }
        }
    }


    // 1번 상어 말고 다른 상어가 있는지 체크 : 살아 있으면 true, 아니면 false
    static boolean checkOtherSharkLife(){
        for(int i=1; i < sharks.size(); i++){
            if(sharks.get(i).isAlive){
                return true;
            }
        }
        return false;
    }


    // 격자 안에 있는지 체크
    static boolean isIn(int x, int y){
        return x >=0 && x < n && y>=0 && y<n;
    }

}