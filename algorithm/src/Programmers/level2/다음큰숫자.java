package Programmers.level2;

public class 다음큰숫자 {
    /*
    1. n의 다음 큰 숫자는 n보다 큰 자연수
    2. n 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같다
    3. n 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수

    n이 매개변수로 주어질 때 n 다음 큰 숫자를 return하는 solution 함수 완성하기

    toBinaryString 써서 2진수로 변경 후 1의 개수 세기...?


*/

    class Solution {
        public int solution(int n) {
            int answer = 0;

            String nBinary = Integer.toBinaryString(n);
            int num1 = 0;
            for(int i=0; i < nBinary.length(); i++){
                if(nBinary.charAt(i) == '1'){
                    num1++;
                }
            }

            for(int i=n+1; ; i++){
                String n2Binary = Integer.toBinaryString(i);
                int num2 = 0;
                for(int j=0; j< n2Binary.length(); j++){
                    if(n2Binary.charAt(j)=='1'){
                        num2++;
                        if(num2 > num1){
                            continue;
                        }
                    }
                }
                if(num1 == num2){
                    answer = i;
                    break;
                }
            }

            return answer;
        }
    }
}
