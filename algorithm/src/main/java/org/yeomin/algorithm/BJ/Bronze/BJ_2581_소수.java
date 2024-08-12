package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-5, 화, 15:30
@see https://www.acmicpc.net/problem/2581
@git
@youtube
@performance
@category #
@note 
    <문제>
    자연수 M과 N이 주어질 때, M이상 N이하의 자연수 중 소수인 것을 모두 골라 소수의 합과 최솟값을 찾는 프로그램을 작성하시오.

    <입력>
    첫째줄 : M
    둘째줄 : N
    1 <= M <= N <= 10,000

    <출력>
    첫째줄 : 소수의 합
    둘째줄 : 최솟값
    
    없는 경우 : -1 출력

    <풀이>
    에라토스테네스의 체 사용해보자.

*/
public class BJ_2581_소수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        boolean [] prime = new boolean[N+1];

        prime = eratosthenes(N);

        int num = 0; // 소수의 합
        int who = -1;

        boolean check = false;
        for (int i = M; i <= N; i++) {
            if(prime[i]){
                num+=i;
                if(!check){
                    who = i;
                    check = true;
                }
            }
        }

        if(who != -1){
            System.out.println(num);
        }
        System.out.println(who);

    }



    public static boolean[] eratosthenes(int n){

        boolean [] prime = new boolean[n+1];
        Arrays.fill(prime, true);

        prime[0] = false;
        prime[1] = false;

        for(int i = 2; i <= Math.sqrt(n); i++){

            if(prime[i] == false){
                continue;
            }

            // 배수는 소수가 아님!
            for (int j = i*i; j < n+1; j+=i) {
                prime[j] = false;
            }
        }


        return prime;
    }


}
