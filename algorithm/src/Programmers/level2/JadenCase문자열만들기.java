package Programmers.level2;
/*
    풀이 : 공백을 기준으로 잘라서 맨 앞글자 비교해서 알파벳이 아니면 그냥 붙이고, 알파벳이면 대문자로 바꿔주기!
            공백까지 toUpperCase / toLowerCase 해버리면 공백이 지워진다...ㅠㅠ
*/
public class JadenCase문자열만들기 {
    public String solution(String s) {
        String answer = "";

        StringBuilder sb = new StringBuilder();

        for(int i=0; i < s.length(); i++){

            if(i==0 || (s.charAt(i-1) == ' ' && s.charAt(i) != ' ')){ // 공백이 주어지고, 문자가 나오면
                if(s.charAt(i) == ' '){
                    sb.append(" ");
                }else{
                    sb.append((s.charAt(i) + "").toUpperCase());

                }

            }
            else{
                sb.append((s.charAt(i) + "").toLowerCase());
            }
        }

        answer = sb.toString();

        return answer;
    }
}
