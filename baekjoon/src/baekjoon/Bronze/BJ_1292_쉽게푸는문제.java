package baekjoon.Bronze;

/*
@author 정여민
@since 2024-03-5, 화, 13:12
@see https://www.acmicpc.net/problem/1292
@git
@youtube
@performance
@category #
@note
    <문제>
    동호의 수학문제 풀이.
    1을 1번, 2를 2번, 3을 3번, ... 수열
    일정한 구간을 주면 구간합 구하기

    <입력>
    첫째줄 : 구간의 시작과 끝 A, B (1 ≤ A ≤ B ≤ 1,000)
    수열에서 A번째 숫자 ~ B 번째 숫자의 합을 구하기

    <출력>
    구간에 속하는 숫자의 합 출력

    <풀이>
    1. B까지의 합 - A까지의 합
    1) 수열을 미리 만들어두자

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1292_쉽게푸는문제 {

    public static int [] sequence = new int [1001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Arrays.fill(sequence, 0);
        // 수열 만들기
        int num = 1;
        for (int i = 1; i < 1000; i+=num++ ) {

            for (int j = 0; j < num; j++) {
                if(i + j > 1000) {
                    break;
                }
                sequence[i+j] = num;
            }

        }
        System.out.println(Arrays.toString(sequence));
        int sum = 0;
        for (int i = A; i <= B; i++) {
            sum += sequence[i];
        }
        System.out.print(sum);
    }
}
