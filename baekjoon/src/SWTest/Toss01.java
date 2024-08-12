/*
    <문제>
    적당히 어려운 문제. 코테 문제 선정 역할
    '상위 25% 이내의 난이도를 가진 문제 중 가장 쉬운 문제'

    <입력>
    levels[N] : 문제 난이도
    1<=N<=10000
    1<= levels[i] <= 2147483647 
    levels[i] != levels[j]

    <출력>
    조건에 맞는 문제의 난이도 반환
    조건에 해당하는 문제가 없다면 -1 반환

    <풀이>
    1. 배열 정렬
    2. 25% -> N/4 한 거 N - N/4

*/

import java.util.*;

class Solution {
    public int solution(int[] levels) {
        int answer = 0;

        // 배열 정렬
        Arrays.sort(levels);

        try{
            int N = levels.length;
            int N25 = N/4;

            answer = levels[N - N25];
        }catch(Exception e){
            answer = -1;
        }

        return answer;
    }
}