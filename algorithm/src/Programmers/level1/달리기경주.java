package Programmers.level1;
/*
    <문제>
    달리기 경주 대회
    선수가 추월할 때 바로 앞 선수의 이름을 부름
    경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 배열에 담아 return

    <입력>
    players : 선수들의 이름이 1등부터 담긴 문자열 배열
    callings : 해설진이 부른 이름

    <출력>
    경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 출력

    <풀이>
    걍... 문자열 탐색과 swap인듯?
    -> 시간초과가 난다

    등수를 관리하는 해시맵을 만들어서 사용하자!

    HashMap<String, Integer> hash : 이름, 현재 등수

*/
import java.util.*;
public class 달리기경주 {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};

        answer = new String[players.length];


        // 1. 등수를 관리할 HashMap 만들기
        HashMap<String, Integer> hash = new HashMap<String, Integer>();

        int index = 0;
        for(String s : players){
            hash.put(s, index);
            index++;
        }

        // for(String s : callings){
        //     for(int i=1; i < players.length; i++){
        //         if(s.equals(players[i])){   // 이름 불린 선수 찾았으면 바로 앞 선수와 변경
        //             String temp = players[i-1];
        //             players[i-1] = players[i];
        //             players[i] = temp;
        //             break;
        //         }
        //     }
        // }

        // 2. 등수 변경
        for(String s : callings){
            int currentPlayer = hash.get(s);
            String beforePlayer = players[currentPlayer - 1];

            // players 배열 갱신
            players[currentPlayer-1] = s;
            players[currentPlayer] = beforePlayer;

            hash.put(s, currentPlayer -1);
            hash.put(beforePlayer, currentPlayer);
        }


        answer = players;

        return answer;
    }
}
