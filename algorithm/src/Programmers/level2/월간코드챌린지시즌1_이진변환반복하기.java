package Programmers.level2;
/*
    <문제>
    이진변환
    1. 모든 0을 제거
    2. x의 길이를 2진법으로 표현한 문자열로 바꾸기

    <입력>
    s : 0과 1로 이루어진 문자열

    <출력>
    s가 1이 될 때까지 계속해서 2진 변환을 가했을 때
    이진 변환의 횟수와 변환 과정에서 제거된 모든 0의 개수를 각각 배열에 담아 리턴

    <풀이>
    num : 이진 변환의 횟수
    numZero : 제거된 모든 0의 개수

    while (s!=1)
    1. replace : numZero += 기존 길이 - replace 후의 길이
    2. s.length() -> 이진수로 변환  toBinaryString(int i) : num++
*/
public class 월간코드챌린지시즌1_이진변환반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];

        int num = 0;
        int numZero = 0;

        while(!s.equals("1")){
            int len = s.length();
            s = s.replace("0","");
            numZero += len - s.length();

            s = Integer.toBinaryString(s.length());
            num++;
        }

        answer[0] = num;
        answer[1] = numZero;

        return answer;
    }
}
