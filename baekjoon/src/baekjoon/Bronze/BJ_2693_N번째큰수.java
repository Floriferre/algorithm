package baekjoon.Bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-4, 월, 15:30
@see https://www.acmicpc.net/problem/2693
@git
@youtube
@performance #자체 sort : 176ms, #selection sort :
@category #
@note
    <문제>
    배열 A가 주어졌을 때, N번째 큰 값을 출력하는 프로그램을 작성하시오.
    배열 A의 크기는 항상 10이고, 자연수만 가지고 있다. N은 항상 3이다.

    <입력>
    첫째줄 : 테스트 케이스 개수 T(1 ≤ T ≤ 1,000)
    TC1 : 배열 A의 10개 원소가 공백으로 구분되어 주어짐

    <출력>
    각 테이스 케이스에 대해 배열 A에서 3번째로 큰 값을 출력

    <풀이>
    배열을 리스트로 선언해서 받아서 sort 하면 될 것 같다.
    comparator 쓰기 좋을 듯.
    혹은 저장한 배열을 sort 구현해서 써도 좋을 것 같은데,
    병합 정렬 써보자.

*/
public class BJ_2693_N번째큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            List<Integer> arrayA = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 10; i++) {
                 arrayA.add(Integer.parseInt(st.nextToken()));
            }

            arrayA.sort((o1, o2) -> o2-o1);

            sb.append(arrayA.get(2)).append("\n");
        }
        System.out.println(sb);

    }




}
