package Programmers.level1;
/*
    <문제>
    1~n 개인정보
    약관 정보에 따라 유효기간 O

    모든 달은 28일까지 있다고 가정
    파기해야하는 개인정보 번호는?

    <입력>
    today : 오늘 날짜 YYYY.MM.DD
    terms : 약관 유효기간 담은 1차원 문자열 배열 (약관종류 유효기간)
    privacies : 수집된 개인정보의 정보를 담은 1차원 문자열 배열 (날짜 약관종류)

    <출력>
    파기해야하는 개인정보 번호 오름차순으로 1차원 배열에 담기

    <풀이>
    해시맵과 리스트를 잘 쓰자
    1. 해시맵 : terms 담아둘 것. 키 : 약관 종류, value : 개월수
    2. 리스트 : 파기해야할 개인 정보 번호 담기
    3. 개인정보 탐색
        날짜 년도, 월, 일로 나누어 비교.
*/

import java.util.*;
public class KakaoBlindRecruitment2023_개인정보수집유효기간 {

    public int[] solution(String today, String[] terms, String[] privacies) {
        int [] answer = {};

        // 현재 년, 월, 일
        int currentY = Integer.parseInt(today.split("\\.")[0]);
        int currentM = Integer.parseInt(today.split("\\.")[1]);
        int currentD = Integer.parseInt(today.split("\\.")[2]);

        HashMap<String, Integer> hash = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        // 약관 정보 해시맵에 저장하기
        for(String s : terms){
            hash.put(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]));
        }

        // 개인 정보 탐색
        int index = 1;
        for(String s : privacies){
            String [] temp = s.split(" ");

            int year = Integer.parseInt(temp[0].split("\\.")[0]);
            int month = Integer.parseInt(temp[0].split("\\.")[1]);
            int day = Integer.parseInt(temp[0].split("\\.")[2]);

            int diff = 0;
            diff += (currentY - year)*12;
            diff += currentM;
            diff -= month;
            if(currentD < day){
                diff -= 1;
            }

            if(diff >= hash.get(temp[1])){
                list.add(index);
            }
            index++;

        }

        answer = new int[list.size()];

        for(int i= 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }


        return answer;
    }

}
