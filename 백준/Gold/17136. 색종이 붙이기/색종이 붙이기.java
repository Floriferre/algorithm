/*
@author 
@since 2025-01-13, 월, 10:35
@see
@git
@youtube
@performance
@category #
@note
    1x1, 2x2, 3x3, 4x4, 5x5 색종이 5개씩

    10x10 색종이 위에 붙이기
    각 칸은 0 아니면 1

    1. 1이 적힌 칸은 전부 색종이으로 덮어져야 함
    2. 종이 경계 밖으로 나가면 안 되고, 겹쳐서도 안됨.
    3. 칸의 경계와 일치하게 붙여야함
    4. 0이 적힌 칸에는 색종이가 있으면 안 됨

    종이가 주어졌을 때, 1이 적힌 모든 칸을 붙이는데 필요한 색종이의 최소 개수

    색종이를 붙인 경우 / 뗀 경우 나누어서
*/

import java.util.*;
import java.io.*;
public class Main {
    static int [][] map;

    static int result = Integer.MAX_VALUE;

    static int [] color_paper = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        map = new int[10][10];

        for(int i=0; i < 10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < 10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, 0);

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }


    // 참고 풀이
    public static void solve(int r, int c, int count){

        if(count >= result){    // 붙인 색종이 수가 더 큰 건 볼 필요 없음
            return;
        }
        
        // 다 탐색했으면 종료
        if(r==10){
            result = Math.min(result, count);
            return;
        }

        // 한 행 다 탐색하면 다음줄로
        if(c==10){
            solve(r+1, 0, count);
            return;
        }

        if(map[r][c] == 1){ // 색종이 붙이기
            for(int size = 5; size >=1; size--){
                if(canAttach(r,c, size)){
                    attach(r, c, size, 2);
                    color_paper[size]--;
                    solve(r, c+1, count+1);
                    attach(r, c, size, 1);
                    color_paper[size]++;
                }
            }
        }else{  // 0이라서 색종이 못 붙이는 경우 다음 칸으로 이동
            solve(r, c+1, count);
        }
    }

    // 색종이 붙일 수 있는지 확인
    public static boolean canAttach(int r, int c, int size){
        if(color_paper[size] == 0){ // 색종이 다 썼으면 못 함
            return false;
        }
    

        for(int i=r; i < r+size; i++){  // 만약 1이 아닌 곳이 있으면 못붙임
            for(int j=c; j<c+size; j++){
                if(!isIn(i,j) || map[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }

    // 색종이 붙이기, 떼기
    public static void attach(int r, int c, int size, int value){
        for(int i=r; i < r+size; i++){  // 만약 1이 아닌 곳이 있으면 못붙임
            for(int j=c; j<c+size; j++){
                map[i][j] = value;
            }
        }
    }



    // 풀이
    // n: 색종이 크기, r: 시작x좌표, c: 시작y좌표, currentMap: 현재 맵 상태
    public static void solution(int n, int r, int c, int [][] currentMap){
        if(n == 0){
            if(!checkOneExist(currentMap)){
                result = -1;
            }
            return;
        }

        for(int i=0; i <10; i++){
            for(int j=0; j < 10; j++){
                if(color_paper[n] >0 && checkAttachColor(currentMap, n, i, j)){  // 붙일 수 있으면
                    currentMap = attachColor(currentMap, n, i, j, 2);   // 색종이 붙이기
                    color_paper[n]--;
                    result++;
                }
            }
        }

        solution(n-1, r, c, currentMap);

    }



    // 아직 남은 1이 있는지 판단
    public static boolean checkOneExist(int [][] map){
        for(int [] i : map){
            for(int j : i){
                if(j==1){
                    return false;
                }
            }
        }
        return true;
    }

    // 색종이 붙일 수 있는지 판단하기
    public static boolean checkAttachColor(int [][] map, int n, int r, int c){
        for(int i=r; i < r+n; i++){
            for(int j=c; j<c+n; j++){
                if(!isIn(i,j) || map[i][j] != 1){
                    return false;
                }
            }
        }
        return true;
    }


    // 색종이 붙이기 n: 색종이크기, r: 시작x좌표, c: 시작y좌표, value: 붙이면 2, 떼면 1
    public static int [][] attachColor(int [][] map, int n, int r, int c, int value){
        int [][] temp = copyMap(map);

        for(int i=r; i<r+n; i++){
            for(int j=c; j<c+n; j++){
                temp[i][j] = value;
            }
        }
        return temp;
    }

    // 배열 복사하기
    public static int[][] copyMap(int [][] map){
        int [][] temp = new int [10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    // 범위 안에 있는지 파악하는 함수
    public static boolean isIn(int nx, int ny){
        return nx>=0 && nx < 10 && ny >= 0 && ny <10;
    }
}