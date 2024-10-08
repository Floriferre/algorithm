/*
    <문제>
    휴대폰 자판 -> 컴퓨터 키보드와 달리 하나의 키에 여러개의 문자가 할당 가능 
    여러번 누르면 다른 문자로 변함
    
    휴대폰 자판 1~100까지 있음 
    문자 무작위 배열, 같은 문자가 여러번 할당되기도, 키 하나에 같은 문자가 여러번 할당되기도.
    할당X 경우도. 
    
    특정 문자열을 작성할 때 키를 최소 몇 번 눌러야 그 문자열을 작성할 수 있는지 
    
    <입력>
    keymap : 1번 키부터 차례대로 할당된 문자들이 순서대로 담긴 문자열 배열
    targets : 입력하려는 문자열들이 담긴 문자열 배열
    
    <출력>
    각 문자열을 작성하기 위해 키를 최소 몇 번 눌러야하는지 순서대로 배열에 담아 return
    문자열 작성 불가할 때는 -1
    
    <풀이>
    해시맵 쓰면 될 것 같음.
    문자열 입력 받을 때 새로운 문자면 입력 횟수 등록
    이미 있는 문자면 더 작은 값으로 업데이트


*/


import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = {};
        answer = new int [targets.length];
        
        
        // 문자와 최소 횟수 저장할 해시맵
        HashMap<Character, Integer> hash = new HashMap<>();
        
        for(String s : keymap){
            for(int i=0; i < s.length(); i++){
                if(hash.containsKey(s.charAt(i))){ // 이미 해시맵에 해당 문자가 있으면
                    if(hash.get(s.charAt(i)) > i+1){
                        hash.put(s.charAt(i), i+1);
                    }
                    continue;
                }
                hash.put(s.charAt(i), i+1);
            }
        }
        
        
        int index = 0;
        for(String s : targets){
            int count = 0;
            for(int i=0; i < s.length(); i++){
                if(hash.containsKey(s.charAt(i))){
                    count += hash.get(s.charAt(i));
                }else{
                    count = -1;
                    break;
                }
            }
            answer[index] = count;
            index++;
        }
        
        
        
        return answer;
    }
}