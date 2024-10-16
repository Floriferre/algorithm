/*
    배운 점 : int [] 는 Comparator 불가한 primitive 
    
    int : 자료형(primitive type)

        산술 연산 가능함
        null로 초기화 불가
        
    Integer : 래퍼 클래스 (Wrapper class)

        Unboxing하지 않을 시 산술 연산 불가능함
        null값 처리 가능

*/

import java.util.*;
class Solution
{
    public int solution(int []A, int []B)
    {
        int answer = 0;
        
        Integer [] B2 = new Integer[B.length];

        Arrays.sort(A);
        
        for(int i=0; i < B.length; i++){
            B2[i] = B[i];
        }
        
        
        Arrays.sort(B2, (o1, o2)->o2-o1);
        
        for(int i=0; i<A.length; i++){
            answer += A[i]*B2[i];
        }

        return answer;
    }
}