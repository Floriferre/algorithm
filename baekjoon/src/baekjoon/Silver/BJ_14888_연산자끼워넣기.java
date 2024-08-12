package baekjoon.Silver;

/*
@author 정여민
@since 2024-03-6, 수, 9:16
@see https://www.acmicpc.net/problem/14888
@git
@youtube
@performance 16656KB 140ms
@category #
@note 
    <문제>
    N개의 수로 이루어진 수열 A1, A2, ... , AN이 주어진다.
    수와 수 사이게 끼워넣을 수 있는 N-1개의 연산자
    연산자 : +, -, *, /
    수 + 연산자로 수식 만들기, 단 수의 순서 바꾸면 X
    연산자 우선 순위 무시하고 앞에서부터 진행

    N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성

    <입력>
    첫째줄 : 수의 개수 N (2<= N <=11)
    둘째줄 : 수
    셋째줄 : +, -, *, / 의 개수

    <출력>
    첫째줄 : 만들 수 있는 식의 결과의 최댓값
    둘째줄 : 최솟값

    <풀이>
    재귀로 풀면 될 것 같은데, 시간 초과가 나지 않게 주의할 것!
    수식을 완성하면 최댓값과 최솟값을 업데이트 하자.

*/

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14888_연산자끼워넣기 {

    static int N;
    static int [] nums;

    static int [] operator = new int[4];

    static int maxNum = Integer.MIN_VALUE;
    static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 숫자 개수
        N = Integer.parseInt(st.nextToken());

        // 숫자 입력 받기
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 개수 입력 받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        solve(0, operator[0], operator[1], operator[2], operator[3], new char[N-1]);

        sb.append(maxNum).append("\n").append(minNum);

        System.out.println(sb);
    }

    // 몇 번째인지, +, -, *, / 갯수 카운트, 선택된 것 배열에 넣기
    public static void solve(int nthChoice, int plus, int minus, int mulyiply, int divide, char[] choosed){
        // 기저 조건 : 연산자 다 썼으면 값 계산해서 최댓값 최솟값 업데이트
        if(nthChoice == N-1){
            int result = nums[0];
            for (int i = 0; i < choosed.length; i++) {
                switch (choosed[i]){
                    case '+':
                        result += nums[i+1];
                        break;
                    case '-':
                        result -= nums[i+1];
                        break;
                    case '*':
                        result *= nums[i+1];
                        break;
                    case '/':
                        result /= nums[i+1];
                        break;
                }
            }

            maxNum = Math.max(maxNum, result);
            minNum = Math.min(minNum, result);

            return;
        }

        // 재귀 처리
        if (plus > 0){
            choosed[nthChoice] = '+';
            solve(nthChoice+1, plus-1, minus, mulyiply, divide, choosed);
        }
        if (minus > 0){
            choosed[nthChoice] = '-';
            solve(nthChoice+1, plus, minus-1, mulyiply, divide, choosed);
        }
        if (mulyiply > 0){
            choosed[nthChoice] = '*';
            solve(nthChoice+1, plus, minus, mulyiply-1, divide, choosed);
        }
        if (divide > 0){
            choosed[nthChoice] = '/';
            solve(nthChoice+1, plus, minus, mulyiply, divide-1, choosed);
        }




    }

}
