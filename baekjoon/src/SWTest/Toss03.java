package SWTest;

public class Toss03 {



    /*
    <문제>
    가짜 영수증찾기
    금액이 옳지 않게 적혀 있음
    옳은 금액
    1) 0~9 숫자 + 구분자 ,
    2) 금액이 0원이 아니고서야 가장 왼쪽 숫자가 0일 수 없다
    3) 금액은 세 자리 구분자 포함 혹은 X
    4) 세자리 구분자는 오른쪽 숫자부터 시작해 왼쪽방향으로 매 3개의 숫자마다 1개의 구분자가 있음
    5) 가장 왼쪽 끝이나 오른쪽 끝에는 구분자를 두지 X

    <입력>
    영수증 금액

    <출력>
    옳은 금액이면 true
    아니면 false

    <풀이>
    조건문 잘 쓰자
    1) 구분자 사용한 경우 -> 각각 나눠서 String 배열에 넣기
        1-1) [0]번째를 제외하고 길이가 3이 아닌 거 있으면 전부 아웃 (false)
        1-2) 각 배열마다 돌면서 숫자 아닌거 있으면 out
        1-3) [0]번째에서 맨 왼쪽이 길이가 1이 아닌데 0으로 시작하면 out
*/


    class Solution {
        public boolean solution(String amountText) {
            boolean answer = true;

            String [] amount = amountText.split(",");

            if(amount.length == 1){ // 한 칸짜리 배열일 때
                if(amount[0].charAt(0) == '0' && amount[0].length() != 1){  // 0원이 아닌 경우에 0으로 시작하면
                    answer = false;
                }else{
                    for(int i=0; i < amount[0].length(); i++){  // 숫자 아니면 false
                        if((amount[0].charAt(i)-'0') >= 0 && (amount[0].charAt(i)-'0') <= 9){
                            continue;
                        }else{
                            answer = false;
                            break;
                        }
                    }
                }
            }else{  // 배열이 여러칸일 때
                for(int i=1; i < amount.length; i++){
                    if(amount[i].length() != 3){    // 3자리가 아니면 out
                        answer = false;
                        break;
                    }

                    for(int j=0; j < amount[i].length(); j++){  // 숫자 아니면 false
                        if((amount[i].charAt(j)-'0') >= 0 && (amount[i].charAt(j)-'0') <= 9){
                            continue;
                        }else{
                            answer = false;
                            break;
                        }
                    }
                }
            }
            return answer;
        }
    }
}
