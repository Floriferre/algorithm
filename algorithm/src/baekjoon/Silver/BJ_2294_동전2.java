package baekjoon.Silver;

/*
 *   <문제>
 *    n종류의 동전, 가치의 합 k원, 동전의 갯수가 최소
 *
 *    <입력>
 *      첫째줄 : n, k (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
 *      n개의 줄 : 각각 동전의 가치
 *
 *   <출력>
 *   첫째줄 : 사용한 동전의 최소 개수, 불가능한 경우 -1 출력
 *
 *   <풀이>
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2294_동전2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [] coins = new int[n];  // coins : 주어진 동전들

        for(int i=0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            coins[i] = Integer.parseInt(st.nextToken());
        }

        int [] dp = new int [k+1];

        Arrays.fill(dp, Integer.MAX_VALUE-1);

        dp[0] = 0;

        for(int i=0; i < n; i++){
            for(int j=1; j <= k; j++){
                if(j>=coins[i]){

                        dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);

                }
            }
        }
//        System.out.println(Arrays.toString(dp));

        if(dp[k]==Integer.MAX_VALUE-1){
            sb.append(-1);
        }else{
            sb.append(dp[k]);
        }
        System.out.println(sb);
    }
}
