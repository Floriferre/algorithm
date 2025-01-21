/*
@author 
@since 2025-01-21, 화, 12:37
@see
@git
@youtube
@performance
@category #
@note

    <문제>
    초기에 n+1개의 집합 {0}, {1}, {2}, ..., {n}이 있음
    합집합 연산과, 두 원소가 같은 집합에 포함되어있는지 확인하는 연산 수행
    집합을 표현하는 프로그램을 작성하여라

    <입력>
    첫째줄 : n m (m : 입력으로 주어지는 연산의 개수)
    m개의 줄 : 각각의 연산

        합집합 : 0 a b (a가 포함된 집합과 b가 포함된 집합을 합쳐라)
        두 원소가 같은 집합인지 확인 : 1 a b

        제한
        1 <= n <= 1,000,000
        1 <= m <= 100,000
        0 <= a, b <= n
        a, b는 정수
        a b는 같을 수도 있다

    <출력>
    같은 집합에 포함되어있으면 yes, 아니면 no

    <풀이>
    처음에 리스트로 풀까 했는데 이러면 집합 n개를 만들 때 메모리 초과가 날 것 같다

    유니온 파인드를 해보자.

    합집합 연산 -> 유니온 연산

*/


import java.util.*;
import java.io.*;
public class Main {
    static int n, m;
    static int [] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        // parent 초기화
        for(int i=0; i <n+1; i++){
            parent[i] = i;
        }

        for(int i=0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int mark = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(mark == 0){  // 합집합 연산
                union(a, b);

            }else{  // 같은 집합인지 확인
                if(find(a) == find(b)){
                    sb.append("yes").append("\n");
                }else{
                    sb.append("no").append("\n");
                }
            }

        }

        System.out.println(sb);
    }
    
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        
        // 이미 같은 그룹이면
        if(rootA == rootB){
            return false;
        }
        
        // 더 큰 거 밑으로 들어가기
        if(rootA > rootB){
            parent[rootA] = parent[rootB];
        }else{
            parent[rootB] = parent[rootA];
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