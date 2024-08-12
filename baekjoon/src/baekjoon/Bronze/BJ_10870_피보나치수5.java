package baekjoon.Bronze;

/*
@author 정여민
@since 2024-02-29, 목, 9:37
@see https://www.acmicpc.net/problem/10870
@git
@youtube
@performance 14156KB 124ms
@category #
@note
    <문제>
    피보나치 수는 0과 1로 시작한다.
    n이 주어졌을 때 n번째 피보나치 수를 구하는 프로그램을 작성하시오.

    <입력>
    첫째 줄에 n이 주어진다. n은 20보다 작거나 같은 자연수 또는 0이다.

    <출력>
    첫 번째 줄에 n번째 피보나치 수를 출력한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10870_피보나치수5 {

    static int dp [] = new int[21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.println(dp[n]);


    }
}
