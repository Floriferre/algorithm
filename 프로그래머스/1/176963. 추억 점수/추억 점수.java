/*
    <문제>
    사진별 추억 점수 : 사진속에 나오는 인물의 그리움 점수 합산한 값
    
    <입력>
    name : 그리워하는 사람의 이름을 담은 문자열 배열
    yearning : 각 사람별 그리움 점수를 담은 정수 배열
    photo : 각 사진에 찍힌 인물의 이름을 담은 이차원 문자 배열
    
    <출력>
    사진들의 추억 점수를 photo에 주어진 순서대로 배열에 담아 리턴
    
    <풀이>
    HashMap을 쓸 차례인가...!
    HashMap<String, Integer> hash : 이름, 그리움 점수
    
    photo별로 그리움 점수 계산하기

*/

import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = {};
        answer = new int[photo.length];
        
        HashMap <String, Integer> hash = new HashMap<String, Integer>();
        
        for(int i=0; i < name.length; i++){
            hash.put(name[i], yearning[i]);
        }
        
        int index = 0;
        for(String [] human : photo){
            int score = 0;
            for(String s : human){
                // try{
                //    score += hash.get(s); 
                // }catch(Exception e){
                //     continue;
                // }
                
                if(hash.containsKey(s)){
                    score += hash.get(s);
                }
                
            }
            answer[index] = score;
            index++;
        }
        
        
        
        return answer;
    }
}