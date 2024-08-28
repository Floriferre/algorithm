package baekjoon.Bronze;

/*
@author 정여민
@since 2024-02-28, 수, 10:55
@see https://www.acmicpc.net/problem/3460
@git
@youtube
@performance
@category #
@note
*
    <문제>
    양의 정수 n이 주어졌을 때, 이를 이진수로 나타냈을 때 1의 위치를 모두 찾는 프로그램을 작성

    <압력>
    첫째줄 : 테스트 케이스 개수 T 1 ≤ T ≤ 10
    TC1 : n 1 ≤ n ≤ 106

    <출력>
    각 테케에 대해 1의 위치를 공백으로 구분해서 줄 하나에 출력 (위치가 낮은 것부터 출력)
*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_3460_이진수 {

    public static int n;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        // 테스트 케이스 개수 T
//        int T = Integer.parseInt(st.nextToken());
//
//        for(int t = 1; t <= T; t++){
//            st = new StringTokenizer(br.readLine());
//            n = Integer.parseInt(st.nextToken());   // n 입력 받기
//
//            List<Integer> result = new ArrayList<>();
//            for(int j = 0; ; j++){
//                if(n%2 == 1){
//                    result.add(j);
//                }
//                n /= 2;
//                if(n ==1){
//                    result.add(j+1);
//                    break;
//                }else if(n == 0){
//                    break;
//                }
//            }
//
//            for(Integer i : result){
//                System.out.print(i + " ");
//            }
//        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        Integer T = Integer.parseInt(st.nextToken());
        for(int t=0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            Integer num = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            int index = 0;
            while(num > 1){

                if(num%2 == 1){
                    list.add(index);
                }

                num /= 2;
                index++;
            }
            if(num == 1){
                list.add(index);
            }

            for(Integer i : list){
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);




    }
}
