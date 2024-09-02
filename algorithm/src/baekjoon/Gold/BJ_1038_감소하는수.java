package baekjoon.Gold;

/*
@author 정여민
@since 2024-09-2, 월, 18:5
@see https://www.acmicpc.net/problem/1038
@git
@youtube
@performance
@category #
@note
    <문제>
    음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소 -> 감소하는 수
    N번째 감소하는 수를 출력하는 프로그램 작성
    N번째 감소하는 수가 없다면 -1을 출력

    <입력>
    첫째줄 : N (0<=N<=1,000,000)

    <출력>
    첫째줄 : N번째 감소하는 수 출력

    <풀이>
    완전탐색으로 풀면 시간초과가 난다 (이전에 뽑은 수 -1)

    1. 제일 처음에 뽑은 수를 기준으로 감소하는 수를 만들어서 list에 저장
    2. list를 정렬
    3. n번째 수를 반환

    ex) 처음에 뽑는 수    다음에 올 수 있는 수    만들어지는 수
        1               0                       10
        2               0, 1                    21, 20, 210
        3               0, 1, 2                 32, 31, 321, 320, 310

    현재 뽑은 수 n -> n%10 보다 작은 값만 다음에 올 수 있다
    n*10 + i 가 만들 수 있는 번호


*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1038_감소하는수 {
    static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());


        // N이 10이하일 때는 바로 리턴시키기
        if(N<=10){
            sb.append(N);
        }else if(N > 1022){ // 가능한 숫자가 없으면 -1 (만들 수 있는 최대 감소하는 수 9876543210이 1022번째)
            sb.append(-1);
        }
        else{  // 1~9로 시작하는 숫자들
            for(int i=0; i < 10; i++){
                solve(i, 1);
            }
            Collections.sort(list); // 정렬
            sb.append(list.get(N)); // N번째 수 찾기
        }
        System.out.println(sb);
        
    }

    public static void solve(long num, int idx){
        if(idx > 10) {  //이 이상은 감소하는 수가 될 수 없다
            return;
        }
        list.add(num);

        for(int i=0; i < num%10; i++){  // 다음 감소하는 수로 보내기
            solve((num*10)+i, idx + 1);
        }

    }
}
