package Programmers.level1;
/*
<문제>
지폐 반 접어 넣기

1. 지폐를 접을 때 항상 길이가 긴쪽을 반으로 접기
2. 접기 전 길이가 홀수였다면 접은 후 소수점 이하 버리기
3. 접힌 지폐를 90도 돌려서 지갑에 넣을 수 있다면 그만 접기

<입력>
wallet : 지갑의 가로, 세로 크기 담은 정수 리스트
bill : 지폐의 가로, 세로 크기 담은 정수 리스트

<출력>
지갑에 넣기 위해 지폐를 최소 몇 번 접어야 하는가?

<풀이>
1. 지폐 비교하기
지폐 가로세로와 지갑 가로세로 | 지폐 세로가로와 지갑가로세로
2. 지폐 큰 부분 반으로 접기
3. 만족할 때까지 계속하기
*/
public class PCCE_9_지폐접기 {


    class Solution {
        public int solution(int[] wallet, int[] bill) {
            int answer = 0;

            while(true){
                // 지갑에 들어가는 크기면
                if((wallet[0] >= bill[0]&& wallet[1] >= bill[1]) || (wallet[1] >= bill[0]&& wallet[0] >= bill[1])){
                    break;
                }
                if(bill[0] > bill[1]){
                    bill[0] /= 2;
                }else{
                    bill[1] /= 2;
                }
                answer++;
            }


            return answer;
        }
    }
}
