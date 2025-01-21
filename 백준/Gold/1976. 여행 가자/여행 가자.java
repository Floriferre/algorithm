/*
@author 
@since 2025-01-21, 화, 15:34
@see https://www.acmicpc.net/problem/1976
@git
@youtube
@performance
@category #
@note
    <문제>
    도시 N개. 각 도시 사이에 길이 있을 수도, 없을 수도.

    도시들의 개수와 연결여부, 여행계획에 속한 도시들이 순서대로 주어졌을 때,
    여행계획이 가능한지

    같은 도시를 여러번 방문해도 OK

    <입력>
    첫째줄 : 도시의 수 N (N<=200)
    둘째줄 : 여행계획에 속한 도시들의 수 M
    N개의 줄 : N개의 정수 (i번째 도시와 j번 도시의 연결정보)
            A와 B가 연결되어있으면 B와 A도 연결되어있음
    마지막줄 : 여행계획

    <출력>
    가능하면 YES, 아니면 NO

    <풀이>
    유니온 파인드.

    연결되는 도시들끼리 유니온.

*/

import java.util.*;
import java.io.*;
public class Main {

    static int N, M;
    static int [] parents;

    static int [][] relationship;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        // 도시별 연결관계
        relationship = new int[N+1][N+1];

        for(int i=1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j <N+1; j++){
                int destination = Integer.parseInt(st.nextToken());
                relationship[i][j] = destination;
                relationship[j][i] = destination;
            }
        }

        // union
        parents = new int[N+1];
        for(int i=1; i < N+1; i++){
            parents[i] = i;
        }

        for(int i=1; i < N+1; i++){
            for(int j=i+1; j < N+1; j++){
                if(relationship[i][j] == 1){
                    union(i,j);
                }
            }
        }

        // 여행 계획
        String [] route = br.readLine().split(" ");

        for(int k=1; k < route.length; k++){
            if(find(Integer.parseInt(route[k-1])) != find(Integer.parseInt(route[k]))){
                sb.append("NO").append("\n");
                break;
            }
        }

        if(sb.length() == 0){
            sb.append("YES").append("\n");
        }

        System.out.println(sb);


    }

    static int find(int a){
        if(parents[a] == a){
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB){
            return false;
        }

        if(rootA > rootB){
            parents[rootA] = rootB;
        }else{
            parents[rootB] = rootA;
        }
        return true;
    }


}