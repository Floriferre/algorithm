package Programmers.level1;
/*
<문제>
붕대감기 : t초동안 붕대를 감으면서 1초마다 x만큼 체력 회복
if t초 다 감으면 -> y만큼 체력 추가회복, max 이상으로 회복은 X

기술 쓰는 도중 공격 당하면 기술 취소, 공격을 당하는 순간에는 체력회복 X
기술 끝나면 붕대감기 사용, 연속 성공시간 0으로 초기화

공격 받으면 피해량만큼 체력 줄어들기
체력 <= 0 -> 사망, 더이상 회복 불가

내 캐릭터는 과연 생존할 수 있을까? 두근두근

<입력>
bandage : 붕대감기의 시전 시간, 1초당 회복량 x , 추가 회복량 y
health : 최대 체력
attacks : 몬스터의 공격시간과 피해량

<출력>
남아있는 체력
만약 죽었다면 -1 출력

<풀이>
1. 공격 일어나기 전 붕대감기 check, 연속 시간 체크해서 추가 회복량 더하기 (최대 체력 넘지 않게!)
2. 공격 일어난 후 체력 깎기, 연속 시간 초기화
3. 다음 공격 일어나기 전까지 붕대감기 check
4. 체력이 0보다 같거나 작으면 바로 return
*/
public class PCCP_1_붕대감기 {
    public static void main(String[] args) {
        int[] bandage = {3, 2, 7};

        int health = 20;

        int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};
        Solution.solution(bandage, health, attacks);
    }

    static class Solution {
        public static int solution(int[] bandage, int health, int[][] attacks) {
            int answer = 0;

            int t = bandage[0];
            int x = bandage[1];
            int y = bandage[2];

            int continuous = 0;
            int current_health = health;
            for(int i=0; i < attacks.length; i++){
                // 붕대감기. 연속 성공한 초만큼 체력 더하기, 추가 회복량도 계산해서 더하기
                if(i==0){
                    current_health += ((attacks[i][0]-1)/t)*y + (attacks[i][0]-1)*x ;
                }else{
                    current_health += ((attacks[i][0]-attacks[i-1][0]-1)/t)*y + (attacks[i][0]-attacks[i-1][0]-1)*x ;
                }

                if(current_health > health ){   // 최대 체력 넘어가면 제한하기
                    current_health = health;
                }
                System.out.println("현재 체력1 : " + current_health);
                current_health -= attacks[i][1];    // 몬스터 공격
                if(current_health<=0){  // 죽었으면 끝내기
                    current_health = -1;
                    break;
                }
                System.out.println("현재 체력 : " + current_health);
            }


            answer = current_health;
            return answer;
        }
    }
}
