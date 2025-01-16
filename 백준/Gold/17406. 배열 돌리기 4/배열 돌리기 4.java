/*
@author 
@since 2025-01-16, 목, 11:8
@see https://www.acmicpc.net/problem/17406
@git
@youtube
@performance
@category #
@note
    <문제>
    NxM 배열 A
    배열 A의 값 : 각행에 있는 모든 수의 합 중 최솟값

    회전연산 (r, c, s) : (r-s, c-s) ~ (r+s, c+s) 정사각형을 시계방향으로 한칸씩 돌림

    어떤 회전을 먼저 하느냐에 따라 배열의 값이 달라짐
    
    배열A와 사용 가능한 회전연산이 주어졌을 때, 배열 A의 최솟값을 구하자
    회전 연산은 모두 한 번씩 사용해야하며, 순서는 임의로 정해도 된다

    <입력>
    첫째줄 : 배열 A의 크기 N, M, 회전 연산의 수 K
    N개의 줄 : 배열에 들어있는 수 A[i][j]
    K개의 줄 : 연산정보 r, c, s

       * 제한
        3 ≤ N, M ≤ 50
        1 ≤ K ≤ 6
        1 ≤ A[i][j] ≤ 100
        1 ≤ s
        1 ≤ r-s < r < r+s ≤ N
        1 ≤ c-s < c < c+s ≤ M

    <출력>
    배열 A의 최솟값 출력

    <풀이>
    연산 K의 개수가 최대 6밖에 안 되므로 브루트포스를 하기에 충분하다!

    1. 연산 순서 정하기 (순열)
    2. 연산하기


*/

import java.util.*;
import java.io.*;

public class Main {

    static int N, M, K;
    static int [][] array;
    static int [][] rotation;

    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            // 배열 A 입력 받기
            array = new int[N][M];
            for(int i=0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j < M; j++){
                    array[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 회전 방향 입력받기 r, c, s
            rotation = new int[K][3];
            for(int i=0; i <K; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j <3; j++){
                    if(j< 2){
                        rotation[i][j] = Integer.parseInt(st.nextToken())-1;
                    }else{
                        rotation[i][j] = Integer.parseInt(st.nextToken()) + 1;
                    }
                }
            }

            perm(0, new int [K], new boolean[K]);

            sb.append(result);
            System.out.println(sb);


    }

    // 회전 순서 구할 순열
    public static void perm(int nth, int [] choosed, boolean [] visited){
        // 종료조건 : K개를 다 뽑았을 때
        if(nth == K){
            // 배열 돌리기 수행
            int [][] temp = copyMap();

            for(int i=0; i < K; i++){   // 배열 회전 수행
                rotateArray(temp, rotation[choosed[i]][0], rotation[choosed[i]][1], rotation[choosed[i]][2]);
            }
            calculate(temp);    // 배열 값 계산해서 업데이트
        }



        // K개 뽑기
        for(int i=0; i < K; i++){
            if(!visited[i]){
                choosed[nth] = i;
                visited[i] = true;
                perm(nth+1, choosed, visited);
                visited[i] = false;
            }
        }
    }

    // 배열 값 계산하기
    public static void calculate(int [][] arr){
        for(int i=0; i <N; i++){
            int sum = 0;
            for(int j=0; j<M; j++){
                sum += arr[i][j];
            }
            result = Math.min(result, sum);
        }
    }

    // 배열 복사하기
    public static int[][] copyMap(){
        int [][] temp = new int[N][M];

        for(int i=0; i < N; i++){
            for(int j=0; j <M; j++){
                temp[i][j] = array[i][j];
            }
        }
        return temp;
    }

    // 배열 돌리기
    public static void rotateArray(int [][] arr, int r, int c, int s){
        while(s-->1){   // s 하나씩 줄이면서 시행

            // r-s, c-s 부터 위쪽 한 줄 옮기기
            int temp1= arr[r-s][c+s]; // 오른쪽 위 칸 <-
            for(int i=s; i > -s; i-- ){
                int temp_num = arr[r-s][c+i-1];
                arr[r-s][c+i] = temp_num;
            }

            // r+s, c+s 부터 위쪽으로 한칸씩 옮기기
            int temp2 = arr[r+s][c+s];  // 오른쪽 아래 값
            for(int i=s; i > -s+1; i-- ){
                int temp_num = arr[r+i-1][c+s];
                arr[r+i][c+s] = temp_num;
            }
            arr[r-s+1][c+s] = temp1;

            // r+s, c-s 부터 오른쪽으로 한칸씩 돌며 값 업데이트하기
            int temp3 = arr[r+s][c-s];  // 가장 왼쪽 값
            for(int i=-s; i < s; i++ ){
                int temp_num = arr[r+s][c+i+1];
                arr[r+s][c+i] = temp_num;
            }
            arr[r+s][c+s-1] = temp2;


            // r-s, c-s 부터 오른쪽으로 한칸씩 돌며 값 업데이트하기
            for(int i=-s; i < s; i++ ){
                int temp_num = arr[r+i+1][c-s];
                arr[r+i][c-s] = temp_num;
            }
            arr[r+s-1][c-s] = temp3;

        }
    }

}