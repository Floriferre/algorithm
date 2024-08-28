package baekjoon.Bronze;

/*
@author 정여민
@since 2024-02-29, 목, 9:48
@see    https://www.acmicpc.net/problem/2309
@git
@youtube
@performance 14244KB 124ms
@category #브루트포스 알고리즘, 정렬, 조합
@note
    <문제>
    7명의 난쟁이의 키의 합은 100이다.
    거짓말쟁이 두 명이 섞인 9명의 난쟁이 중에 실제 백설공주의 난쟁이를 구하여라.

    <입력>
    아홉개의 줄에 걸쳐 난쟁이들의 키가 주어진다.
    키 : 100 이하 자연수
    아홉 난쟁이의 키는 모두 다르고, 정답이 여러개인 경우 아무거나 출력

    <출력>
    일곱 난쟁이의 키를 오름차순으로 출력한다.

    <풀이>
    시간을 2초나 준다. 순열을 하기에 괜찮은 것 같다. 9P7 밖에 안 나오니까.
    근데 7개 고르지 말고 9명 다 더하고 2명만 골라서
    조합 가보자고

*/


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2309_일곱난쟁이 {

    static List<Integer> heights = new ArrayList();
    static List<Integer> result = new ArrayList();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int i =0; i < 9; i++){
            st = new StringTokenizer(br.readLine());
            heights.add(Integer.parseInt(st.nextToken()));
        }

        // 오름차순 정렬
        heights.sort((o1, o2) -> o1-o2);
        
        makeCombination(0, 0, new int [7]);

        for(Integer i : result){
            System.out.println(i);
        }
    }


    public static void makeCombination(final int nth, final int startIndex, int[] choosed ){
        
        // 7개 다 뽑았으면
        if(nth == choosed.length){

           int sum = 0;
           for(int height : choosed){
                sum += height;
           }

            if(sum == 100){
                result.clear();
                for(int i =0; i < 7; i++){
                    result.add(choosed[i]);
                }
                return;
            }

            return;
        }

        for(int i = startIndex; i < heights.size(); i++){
            choosed[nth] = heights.get(i);
            makeCombination(nth+1, i+1, choosed);
        }
    }

}
