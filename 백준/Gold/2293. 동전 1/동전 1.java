/*
 *  <문제>
 *  n가지 종류의 동전. 각 동전의 가치는 다름. 동전을 적당히 사용해서 가치의 합이 k원이 되게 하고 싶음.
 *  경우의 수를 구하시오
 *
 *  <입력>
 *  첫째줄 : n, k (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000)
 *  n개의 줄 : 각 동전의 가치
 *
 *  <출력>
 *  첫째줄 : 경우의 수
 *
 *  <풀이>
 *  그리디? DP로 채워나가는 방식인 것 같은데...
 *  DP 고고 해보자고~!
 *
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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

        dp[0] = 1;

        for(int i=0; i < n; i++){
            for(int j=1; j <= k; j++){
                if(j>=coins[i]){
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }
        }
//        System.out.println(Arrays.toString(dp));

        sb.append(dp[k]);
        System.out.println(sb);

    }
}