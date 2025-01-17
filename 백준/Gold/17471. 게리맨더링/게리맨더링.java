/*
@author 
@since 2025-01-17, 금, 14:32
@see https://www.acmicpc.net/problem/17471
@git
@youtube
@performance
@category #
@note

    <문제>
    선거구 확정.
    N개의 구역 (1~N)
    구역을 두 선거구로 나누어야하고, 각 구역은 두 선거구 중 하나에 포함되어야함
    한 선거구에 포함된 구역은 모두 연결되어야한다

    공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구 차이를 최소화하려고 한다

    <입력>
    첫째줄 : 구역의 개수 N (2<= N <= 10)
    둘째줄 : 1~N번 구역의 인구 (1<=구역의 인구수<=100)
    N개의 줄 : 각 구역과 인접한 구역의 정보 (인접한 구역의 수, 인접 구역의 번호들)
                구역 A가 B와 인접하면 B와 A도 인접하다

    <출력>
    두 선거구의 인구 차이 최솟값
    두 선거구로 나눌 수 없는 경우에는 -1 출력

    <풀이>
    N이 최대 10이니까 재귀를 써도 될 것 같다

    1. 선거구 2개로 나누고
    2. 이어진 선거구 구하고
    3. 인구수 차이 구해서 업데이트 하기


*/


import java.io.*;
import java.util.*;

public class Main {

    static int N;   // 구역 개수
    static int [] people;   // 구역별 인구수

    static boolean [][] area; // 구역별 연결 정보

    static int result = Integer.MAX_VALUE;

    static class Point{
        int index; // 구역 번호
        int numPeople;  // 현재까지의 인구수 세기

        public Point(int index, int numPeople){
            this.index = index;
            this.numPeople = numPeople;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());

        // 구역별 인구수 입력받기
        people = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i < N; i++){
            people[i] = Integer.parseInt(st.nextToken());
        }

        // 구역 연결정보 입력받기
        area = new boolean[N][N];
        for(int i=0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());   // 몇 개 구역이 연결되었는지
            for(int j=0; j <n; j++){
                int destination = Integer.parseInt(st.nextToken()) - 1;
                area[i][destination] = true;
                area[destination][i] = true;
            }
        }
        divideArea(0, new boolean[N]);

        if(result == Integer.MAX_VALUE){
            sb.append(-1);
        }else {
            sb.append(result);
        }
        System.out.println(sb);

    }

    // BFS 탐색해서 구역의 인구수 반환
    public static int search(int i, boolean [] visited, boolean [] choosed){
        int count = 0;  // 인구수 셀 것

        boolean check = choosed[i]; // 늘 첫번째 거 기준

        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(new Point(i, people[i]));
        visited[i] = true;
        count += people[i];

        while(!q.isEmpty()){
            Point current = q.poll();

            for(int j=0; j <N; j++){
                // 방문한적이 없고, 처음에 설정한 것과 구역이 같고, 연결되어있으면
                if(!visited[j] && choosed[j]==check && area[current.index][j]){
                    q.add(new Point(j, current.numPeople + people[j]));
                    count += people[j];
                    visited[j] = true;
                }
            }
        }

        return count;
    }

    // 선거구 두 개로 나누는 과정
    public static void divideArea(int nth, boolean [] choosed){

        // 종료 조건
        if(nth == N){   // chooses true는 1구역, false는 2구역
            // 연결이 되어있는지 확인 : BFS 탐색


            boolean [] visited = new boolean[N];    // 방문여부 저장할 것

            ArrayList<Integer> list = new ArrayList<>();    // 인구수 저장해둘 것
            int count = 0;

            // 구역 연결되어있는지 확인
            for(int i=0; i < N; i++){
                if(!visited[i]){    // 방문한 적이 없으면 탐색 실행
                    list.add(search(i, visited, choosed));
                    count++;    // 구역 수 증가
                }
            }
            // 구역이 2개면 인구수 저장해둔 거 비교
            if(count == 2){
                result = Math.min(result, Math.abs(list.get(0) - list.get(1)));
            }
            return;
        }


        choosed[nth] = true;
        divideArea(nth+1, choosed);
        choosed[nth] = false;
        divideArea(nth+1, choosed);
    }


}