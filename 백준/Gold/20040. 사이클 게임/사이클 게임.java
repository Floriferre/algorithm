/*
@author 
@since 2025-01-23, 목, 8:58
@see https://www.acmicpc.net/problem/20040
@git
@youtube
@performance
@category #
@note
    <문제>
    사이클 게임 : 두 명의 플레이어가 번갈아 진행

    0 ~ n-1 까지 고유한 번호가 부여된 평면 상의 점 n개가 주어짐
    어느 세 점도 일직선 위에 놓이지 X
    매 차례마다 플레이어는 두 점을 선택해서 연결하는 선분을 긋기
        이전에 그린 선분을 다시 그을 수는 없으나, 이미 그린 다른 선분과 교차는 가능
    처음으로 사이클을 완성하는 순간 게임 종료

    사이클 C : C에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한번씩만 지나서 출발점으로 되돌아옴

    사이클이 몇번째 차례에서 완성되었는지 혹은 진행중인지 판단하는 프로그램

    <입력>
    첫째줄 : 점의 개수 n 진행된 차례의 수 m
    m개의 입력 : i번째 차례의 플레이어가 선택한 두 점의 번호

        제한사항
            3 <= n <= 500,000
            3<= m <= 1,000,000

    <출력>
    m번째 차례까지 게임을 진행한 상황에서 게임이 종료되었다면 사이클이 처음으로 만들어진 차례의 번호,
    종료되지 않으면 0 출력

    <풀이>
    사이클을 판단하기 위해 유니온-파인드를 사용한다
    이미 연결되어있는 곳이라면 union의 결과가 false,
    연결되어있지 않은 곳이라면 union이 가능하다



*/


import java.util.*;
import java.io.*;
public class Main {

    static int n, m;

    static int [] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        sb.append(0).append("\n");

        parent = new int[n+1];

        for(int i=1; i <n+1; i++){
            parent[i] = i;
        }

        for(int i=1; i <m+1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(!union(a, b)){
                sb.setLength(0);
                sb.append(i).append("\n");
                break;
            }
        }

        System.out.println(sb);


    }


    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB){
            return false;
        }
        if(rootA > rootB){
            parent[rootB] = rootA;
        }else{
            parent[rootA] = rootB;
        }
        return true;
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}