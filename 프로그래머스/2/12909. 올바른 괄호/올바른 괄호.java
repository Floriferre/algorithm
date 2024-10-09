/*
    <문제>
    괄호가 바르게 짝지어짐 -> ( )
    
    문자열이 올바른 괄호면 true 리턴, 이니면 false 리턴
    
    <입력>
    문자열 s : ()로만 이루어진 문자열



*/


import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque <Character> t = new ArrayDeque<Character>();
        
        for(int i=0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                t.push('(');
            }else{
                if(t.size() > 0){
                    t.pop();
                }else{
                    answer = false;
                    break;
                }
                
            }
        }
        
        if(t.size()>0){
            answer = false;
        }
        
        return answer;
    }
}