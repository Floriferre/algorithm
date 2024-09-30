/*
    <문제>
    선물 주고받기
    1. 선물 기록 O, 차이 O -> 더 많이 준 사람이 선물 받기
    2. 선물 기록 X || 차이 X -> 선물지수가 더 큰 사람이 선물 받기
        -> 선물지수도 같다면 안 받기
    
    선물지수 : 준 선물 - 받은 선물
    
    선물을 가장 많이 받을 친구가 받은 선물의 수
    
    <입력>
    friends : 친구들의 이름
    gifts : 이번달까지 친구들이 주고받은 선물 기록
    
    <출력>
    다음달에 가장 많은 선물을 받는 친구가 받은 선물의 수
    
    <풀이>
    1. 해시 : 배열에서 이름 위치 기록해둘 것
    2. 2차원 배열 만들기 -> 나, 친구 기록해둘 것
    3. gift 돌면서 친구끼리 주고받은 선물 기록 (2차원 배열)
    4. 애들 한명씩 비교해서 가장 많은 선물 받은 거 찾기
*/

import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
    
        // 1. 해시맵에 친구 저장해두기 
        HashMap<String, Integer> hash = new HashMap<String, Integer>();
    
        int countFriend = 0;
        for(String s : friends){
            hash.put(s, countFriend);
            countFriend++;
        }
        
        // 2. 2차원 배열 만들기
        int[][] current_month = new int[hash.size()][hash.size()];
        
        // 3. 2차원 배열에 선물 기록 넣기
        for(String s : gifts){
            current_month[hash.get(s.split(" ")[0])][hash.get(s.split(" ")[1])] += 1;
        }
        
        for(int i=0; i <current_month.length; i++){
            int tempCount = 0; // i번째 친구가 받을 선물 개수
            for(int j=0; j<current_month.length; j++){
                if(current_month[i][j] != current_month[j][i]){ // 둘이 준 선물 개수가 다르면
                    if(current_month[i][j] > current_month[j][i]){  // i가 더 많이 줬으면 i는 이번에 받음
                        tempCount++;
                    }
                }else{ // 선물 기록 X || 차이 X -> 선물지수가 더 큰 사람이 선물 받기
                    // 선물지수 구하기
                    // i와 j의 선물 지수 
                    int countI = 0;
                    int countJ = 0;
                    for(int r=0; r<current_month.length; r++){
                        countI += current_month[i][r];
                        countI -= current_month[r][i];
                        countJ += current_month[j][r];
                        countJ -= current_month[r][j];
                    }
                    
                    if(countI > countJ){
                        tempCount++;
                    }
                }
            }
            answer = Math.max(answer, tempCount);
        }
        
        
        
        return answer;
    }
}