package Programmers.level1;
/*
    <문제>
    영단어 카드 뭉치 2개
    원하는 순서의 단어 배열을 만들 수 있는가?

    1. 카드 뭉치에서 카드를 순서대로 한 장씩 사용
    2. 한 번 사용한 카드 다시 사용 X
    3. 카드를 사용하지 않고 다음 카드로 넘어갈 수 x
    4. 카드 뭉치의 단어 순서 변경 x

    <입력>
    cards1, cards2 : 문자열로 이루어진 배열
    goal : 원하는 단어 배열

    <출력>
    원하는 단어를 만들 수 있으면 Yes 아니면 No 리턴

    <풀이>
    걍.. 포인터 2개 만들어서 가능한지 하나씩 체크하면 될 듯?

*/
public class 카드뭉치 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";

        int one = 0;
        int two = 0;

        boolean check = false;
        for(String s : goal){



            if(one < cards1.length && cards1[one].equals(s)){
                one++;
            }else if(two < cards2.length && cards2[two].equals(s)){
                two++;
            }else{
                check = true;
                break;
            }
        }

        if(check){
            answer = "No";
        }
        else{
            answer = "Yes";
        }

        return answer;
    }
}
