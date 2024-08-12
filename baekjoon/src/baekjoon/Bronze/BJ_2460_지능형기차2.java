package baekjoon.Bronze;

/*
@author 정여민
@since 2024-02-29, 목, 9:20
@see https://www.acmicpc.net/problem/2460
@git
@youtube
@performance
@category #
@note 
    <문제>
    기차의 역은 1~10.
    사람이 타거나 내림.
    1. 기차는 역 번호 순서대로 운행
    2. 출발역에서 내린 사람 수와 종착역에서 탄 사람의 수는 0
    3. 각 역에서 현재 기차에 있는 사람보다 더 많은 사람이 내리지 X
    4. 기차의 정원은 10,000명, 정원을 초과하여 타는 경우 X
    10개의 역에 대해 기차에 탄 사람과 내린 사람 수가 주어졌을 때, 기차에 사람이 가장 많을 때의 사람수 계산하는 프로그램 작성

    <입력>
    각 역에서 내린 사람 수 탄 사람 수가 1~10개 역 순서대로 주어짐

    <출력>
    첫째 줄에 최대 사람 수 출력

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2460_지능형기차2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int out = Integer.parseInt(st.nextToken());
        int in = Integer.parseInt(st.nextToken());

        int current = in;
        int max = in;
        for(int i = 0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            out = Integer.parseInt(st.nextToken());
            in = Integer.parseInt(st.nextToken());

            current = current - out + in;

            max = Math.max(max, current);
        }
        System.out.println(max);
    }
}
