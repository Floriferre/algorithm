package baekjoon.Bronze;

/*
@author 정여민
@since 2024-03-4, 월, 10:50
@see https://www.acmicpc.net/problem/2609
@git
@youtube
@performance
@category #
@note
    <문제>
    두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

    <입력>
    첫째줄 : 두 개의 자연수 10,000이하

    <출력>
    첫째줄 : 최대공약수
    둘째줄 : 최소 공배수

    <풀이>
    최대공약수와 최소공배수는 유클리드 호제법을 통해 풀 수 있다.
    유클리드 호제법이란, 최대공약수를 구하기 위한 방법으로,

    두 수 a, b ∈ ℤ 이고, r = a mod b 이라고 한다. (r 은 a에 b를 나눈 나머지를 의미)
    이 때 r은 (0 ≤ r < b) 이고, a ≥ b 이다.

    이 때 a 와 b의 최대공약수를 (a, b)라고 할 때 (a, b)의 최대공약수는 (b, r)의 최대공약수와 같다.
    GCD(a, b) = GCD(b, r)






*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2609_최대공약수와최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int gcd = gcd(a, b);

        sb.append(gcd).append("\n").append(a*b/gcd);

        System.out.print(sb);

    }

    public static int gcd(int a, int b){
        if(a < b){
            int tmp = a;
            a = b;
            b = tmp;
        }

        while(b>0){
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }


}
