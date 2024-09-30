package Programmers.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
    /*
    <문제>
    데이터 정렬 업무
    데이터 : 코드번호, 제조일, 최대수량, 현재수량

    data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후, sort_by에 해당하는 값을 기준으로 오름차순 정렬

    <입력>
    data : 정렬한 데이터들이 담긴 이차원 정수 리스트
    ext : 어떤 정보를 기준으로 데이터를 뽑아낼지 의미하는 문자열
    val_ext : 뽑아낼 정보의 기준값
    sort_by : 정보를 정렬할 기준이 되는 문자열

    <출력>
    오름차순 정렬

    <풀이>
    1. ext에 따라 데이터 뽑아서 리스트에 넣기
    code, date, maximum, remain을 해시맵으로 구성해두기
    2. sort_by에 따라 정렬하기

*/


import java.util.*;
public class PCCE_10_데이터분석 {

    class Solution {
        public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
            int[][] answer = {};

            HashMap<String, Integer> hash = new HashMap<String, Integer>();
            hash.put("code", 0);
            hash.put("date", 1);
            hash.put("maximum", 2);
            hash.put("remain", 3);

            List<int []> list = new ArrayList<>();

            for(int [] d: data){

                if(d[hash.get(ext)] < val_ext){
                    list.add(d);
                }
            }


            answer = new int[list.size()][4];
            for(int i=0; i < list.size(); i++){
                answer[i] = list.get(i);
            }


            // 정렬
            Arrays.sort(answer, (o1, o2) -> o1[hash.get(sort_by)] - o2[hash.get(sort_by)]);


            return answer;
        }
    }
}
