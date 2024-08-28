package baekjoon.Bronze;

/*
@author 정여민
@since 2024-02-27, 화, 16:48
@see  https://www.acmicpc.net/problem/2501
@git
@youtube
@performance
@category #수학, #브루트포스 알고리즘
@note 
*   <문제>
*   어떤 자연수 p와 q가 있을 때, 만일 p를 q로 나누었을 때 나머지가 0이면 q는 p의 약수이다.
*   두 개의 자연구 N, K가 주어졌을 때 N의 약수들 중 K번째로 작은 수를 출력하는 프로그램을 작성하시오
*
*   <입력>
*   첫째 줄에 N과 K가 빈칸을 사이에 두고 주어진다.
    1 <= N <= 10,000
    1 <= K <= N
*
*   <출력>
*   첫째 줄에 N의 약수들 중 K번째로 작은 수를 출력한다.
    만일 N의 약수의 개수가 K개보다 적어서 K번째 약수가 존재하지 않을 경우에는 0을 출력하시오.
*
*   <풀이>
*   1부터 브루트 포스로 가면 시간 초과가 나지 않을까? 함 해보자
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2501_약수구하기 {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//
//        int n = 0;
//        int index = 0;
//        for (int i = 1; i <= N ; i++){
//            if(N%i == 0){
//                n++;
//                if(n == K){
//                    index = i;
//                     break;
//                }
//            }
//        }
//        if(n == K){
//            System.out.println(index);
//        }else{
//            System.out.println(0);
//        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        Integer N = Integer.parseInt(st.nextToken());
        Integer K = Integer.parseInt(st.nextToken());

        int count = 0;
        int num = 0;
        for(int i=1; i<=N; i++){
            if(N%i==0){
                count++;
                if(count == K){
                    num = i;
                    break;
                }
            }
        }

        if(count == K){
            System.out.println(num);
        }else{
            System.out.println(0);
        }

    }
}
