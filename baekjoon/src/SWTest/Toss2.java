package SWTest;

public class Toss2 {

    public static void main(String[] args) {

    }

    public int solution(String s) {
        int answer = -1;

        int left = 0;
        int right = 0;

        int length = 0; // 현재 가장 긴 길이
        int current = -1;   // 현재의 가장 큰 문자열의 수
        String result = "";
        while(left <= right && right < s.length()){

            // 문자가 같으면 길이 업데이트
            if(s.charAt(left) == s.charAt(right)){
                if(length <= right - left){  // 길이 업데이트 할 수 있으면
                    length = right - left;
                    if(current < (s.charAt(right) - '0')){  // 만약 가장 큰 수도 바뀌면
                        current = s.charAt(right) - '0';
                        result = s.substring(left, right);
                    }
                }
                right++;
            }else{
                left++;
            }
        }

        if(length == 0){
            answer = -1;
        }else{
            if(current == 0){
                answer = 0;
            }else{
                answer = Integer.parseInt(result);
//                answer = current;
//                for(int i=1; i < length+1; i++){
//                    answer += Math.pow(10, i)*current;
//                }
            }
        }

        return answer;
    }


}
