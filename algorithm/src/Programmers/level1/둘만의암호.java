package Programmers.level1;

import java.util.ArrayList;
import java.util.List;

/*
    문자열 만드는 규칙

    1. s의 각 알파벳을 index 만큼 뒤의 알파벳으로 바꿔주기
    2. index 만큼 뒤의 알파벳이 z를 넘어갈 경우 다시 a 로 돌아오기
    3. skip에 있는 알파벳은 제외하고 건너뛰기

    <입력>
    s , skip : 두 문자열
    index : 자연수

    <출력>
    문자열 s를 변환한 결과

    <풀이>
    해시맵을 쓸까 배열을 쓸까 -> 리스트 쓰자

    1. 리스트 0~25 저장해두고
    2. skip에 있는 것들 없애고
    3. s + index 계산하자
        범위 안 넘어가게 %26 해주기
*/
public class 둘만의암호 {
    public String solution(String s, String skip, int index) {
        String answer = "";

        char [] alpha = {'a', 'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        List<Character> list = new ArrayList<>();

        for(char c : alpha){
            list.add(c);
        }

        // skip에 해당하는 문자들 제거
        for(int i=0; i <skip.length(); i++){
            list.remove(list.indexOf(skip.charAt(i)));
        }

        for(int i=0; i < s.length(); i++){
            answer += list.get((list.indexOf(s.charAt(i)) + index)%list.size()) + "";
        }



        return answer;
    }
}
