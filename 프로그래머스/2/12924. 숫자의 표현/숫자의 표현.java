/*
    Finn의 수학 공부
    자연수 n을 연속한 자연수로 표현하는 방법 
    
    <입력>
    n : 자연수
    
    <출력>
    연속된 자연수들로 n을 표현하는 방법의 수

    <풀이>
    투포인터로 해볼까? 
    
    left = 0 
    right = 0
    
    if(num < n) : 
        right++
    else if(num > n):
        left++
    else:
        answer++;

*/


class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int left = 1;
        int right = 2;
        int sum = left+right;
        
        while(right < n){
            if(sum < n){
                right++;
                sum += right;
            }else if(sum > n){
                sum -= left;
                left++;
            }else{
                answer++;      
                right++;
                sum += right;
            }
        }
        
        answer++;   // 자기 자신인 경우
        
        
        return answer;
    }
}