/*
    <문제>
    문자열 s : 공백으로 구분된 숫자 저장
    최솟값과 최댓값 찾아 반환
    
    <입력>
    공백으로 구분된 숫자가 있는 문자열 s
    
    <출력>
    최솟값 최댓값
    
    <풀이>
    잘 잘라서 최소 비교
*/


class Solution {
    public String solution(String s) {
        String answer = "";
        
        String temp [] = s.split(" ");
        
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;
        for(String t : temp){
            maxNum = Math.max(maxNum, Integer.parseInt(t));
            minNum = Math.min(minNum, Integer.parseInt(t));
        }
        
        answer = minNum + " " + maxNum;
        
        return answer;
    }
}