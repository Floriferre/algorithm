package baekjoon.SamSung;

/*
@author 정여민
@since 2025-01-8, 수, 10:42
@see
@git https://www.acmicpc.net/problem/17135
@youtube
@performance 38948KB 376ms
@category #
@note
    <문제>
    성을 향해 몰려오는 적을 잡는 턴 방식의 게임
    N x M 격자판, 격자는 1x1크기.
    각 칸에 포함된 적의 수는 최대 1.
    격자판의 N번행 바로 아래 (N+1번행)의 모든 칸에는 성이 있음

    성에 궁수 3명 배치. 한 칸에는 최대 1명의 궁수만 가능.
    1. 각 턴마다 궁수는 적 하나 공격 가능, 모든 궁수는 동시에 적 공격.

        궁수가 공격하는 적 : 거리가 D 이하인 적 중 가장 가까운 적.
                          그런 적이 여러명일 경우 가장 왼쪽에 있는 적 공격
                          같은 적이 여러 궁수에게 공격당할 수 있음.
    2. 공격받은 적은 게임에서 제외.

    3. 공격이 끝나면 적이 이동: 아래로 한 칸 이동.
    4. 성이 있는 칸으로 이동하면 게임에서 제외.
    5. 모든 적이 격자판에서 제외되면 게임이 끝남.

    격자판 상태가 주어졌을 때 궁수의 공격으로 제거할 수 있는 적의 최대 수

    D = |r2-r1| + |c2-c1| (맨하탄 거리)

    <입력>
    첫째줄 : N M D
            3 <= N, M <= 15 , 1 <= D <= 10
    N개의 줄 : 격자판 상태

    <출력>
    궁수의 공격으로 제거할 수 있는 적의 최대 수

    <풀이>
    흐름을 잘 파악해두자

    0. 몬스터 위치도 큐에 넣어둘 것.
        몬스터 - 인덱스 (나중에 죽여야할 몬스터 파악하기 위해)

    1. 궁수 위치 정하기 : 조합
        - M칸 중에 D개를 골라야함
        - 최대 15C10 나올 듯
        - 궁수의 위치를 큐에 담아서 쓸 것

    2. 궁수의 공격 : 궁수와 몬스터 멘하탄 거리 비교.
        - 궁수 1부터 가장 가까운 놈 죽이기.
            - 인덱스 파악해서 업데이트 해둘 것.
            - 죽은 놈은 live = false로 바꾸기
            - 같은 놈을 죽일 수 있음

    3. 살아남은 녀석들 이동
        - 죽은 놈을 큐에서 빼기
        - 위치 x좌표 + 1
            만약 x좌표 = N+1 이면 걔도 큐에서 빼기
    4. 계속 반복

*/

import java.util.*;
import java.io.*;

public class BJ_17135_캐슬디펜스 {
    static int [][] map;
    static int N, M, D; // 가로, 세로, 궁수 명수

    static int kill = Integer.MIN_VALUE;


    static ArrayDeque<Monster> monsters;




    // 몬스터 저장
    static class Monster{
        int index;
        int x;
        int y;
        boolean live;   // 살아있으면 true, 아니면 false

        public Monster(int index, int x, int y, boolean live){
            this.index = index;
            this.x = x;
            this.y = y;
            this.live = live;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        monsters = new ArrayDeque<>();

        map = new int [N+1][M];

        int index = 0;
        for(int i=0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j <M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){ // 몬스터 저장
                    monsters.add(new Monster(index, i, j, true));
                    index++;
                }
            }
        }

        solution(0, 0, new boolean[M]);

        System.out.println(kill);

    }

    // 솔루션. 궁수 배치하기 : 조합
    // nth : 몇번째인지
    // startIndex : 몇번부터 확인하는지
    // 여태까지 kill한 몬스터의 수
    public static void solution(int nth, int startIndex,  boolean [] archer){

        // 종료 조건 : 궁수 다 뽑았으면
        if(nth == 3){

            // 몬스터 복제해서 넣어주기 안그러면 클난다...

            ArrayDeque<Monster> monst = new ArrayDeque<>();

            for(Monster m : monsters){
                monst.add(new Monster(m.index, m.x, m.y, m.live));
            }

            
            /// ************* 반복 
            int death = 0;
            // 2. 궁수 공격
            while(!monst.isEmpty()){
                // 죽일 몬스터 담아둘 것 (최대 D마리 죽일 수 있으니까)


                for(int i = 0; i < M; i++){
                    int tempY = Integer.MAX_VALUE;  // 현재까지 가장 왼쪽인 거
                    int tempD = Integer.MAX_VALUE;  // 현재까지 가장 작은 거리
                    int index = -1; // 죽일 적의 인덱스

                    if(archer[i]){  // 궁수가 있으면
                        // monster 큐 돌면서 가장 가까운 몬스터 구하기
                        for(Monster m : monst){
                            int dist = distance(N, i, m.x, m.y);

                            if(check(dist)){  // 궁수의 사정거리 안인지 체크

                                if(dist <= tempD){  // 거리가 더 작으면 죽일 적 업데이트

                                    if(dist == tempD){  // 거리가 같은 경우
                                        if(m.y < tempY){    // 더 왼쪽에 있는 것으로 업데이트
                                            tempY= m.y;
                                            index = m.index;
                                        }
                                    }else{
                                        tempD = dist;
                                        tempY = m.y;
                                        index = m.index;
                                    }
                                }
                            }
                        }
                        // 특정 궁수가 죽일 몬스터 구했으면
                        for(Monster m : monst){
                            if(m.index == index){

                                m.live = false;
                            }
                        }
                    }
                }



                // 2. 이제 궁수가 죽인 녀석들을 없애자!
                int qsize = monst.size();

                while(qsize-->0){
                    Monster current = monst.poll();
                    if(current.live == true){
                        if(current.x+1 < N){  // 4. 살아남은 애들은 다시 큐에 넣기
                            monst.add(new Monster(current.index, current.x + 1, current.y, current.live));
                        }
                        // 3. 근데 몬스터가 성까지 도달했으면 없애기
                    }else{

                        death++;
                    }
                }
            }

            kill = Math.max(kill, death);

            /// ********** 반복
            
            
            return;
        }


        // 1. 궁수 뽑기
        for(int i = startIndex; i < M; i++){
            archer[i] = true;
            solution(nth + 1, i+1, archer);
            archer[i] = false;
        }


    }


    // 거리 구하기
    public static int distance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

    // 궁수의 범위 안에 들어있는지
    public static boolean check(int distance){
        return distance <= D;
    }
}
