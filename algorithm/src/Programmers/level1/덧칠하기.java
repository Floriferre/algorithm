package Programmers.level1;
/*
    <문제>
    n미터 벽. 페인트가 벗겨진 벽에 페인트 덧칠하기
    일부 페인트만 새로 칠하기

    벽을 n개로 나누고 각각 1~n 번호 붙임

    페인트 칠하는 롤러 길이 m미터

    규칙
    1. 롤러가 벽에서 벗어나면 안 됨
    2. 구역의 일부분만 포함되도록 칠하면 안 됨

    빈 부분은 적어도 한 번 칠해야함

    예산을 아끼기 위해 페인트칠하는 횟수 최소화 하려고 할 때 몇번일까?

    <입력>
    n, m : 정수
    section : 다시 페인트를 칠하기로 정한 구역들의 번호

    <출력>
    페인트칠해야하는 최소 횟수

    <풀이>
    그리디 같은데.

    1. 롤러 제일 끝에서부터 칠하기.
    2. m칸 전부터 비교해서 비어있는 거 칠하기

    앞에서부터 칠해볼까?


*/
public class 덧칠하기 {
    public int solution(int n, int m, int[] section) {
        int answer = 0;

        int currentPaint = section[0] + m;
        int currentStart = section[0];
        answer++;
        for(int i=1; i < section.length; i++){
            if(section[i] >= currentStart && section[i] < currentPaint){
                continue;
            }
            currentPaint = section[i] + m;
            currentStart = section[i];
            answer++;

        }
        return answer;
    }
}
