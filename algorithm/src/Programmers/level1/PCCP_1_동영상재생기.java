package Programmers.level1;
/*
    prev : 10초 전으로 이동, 10초보다 덜 남았으면 0으로 이동
    next : 10초 후로 이동, 10초보다 덜 남았으면 마지막으로 이동
    재생 위치가 오프닝이면 오프닝 끝나는 위치로 이동

    1. 현재 비디오 위치 = mm:ss 형태인데 이걸 전부 초로 바꾸기
        op start, op end, video len 전부 초로 바꾸기
    2. 사용자 입력 처리
        2-1. prev : 초 - 10 (10초보다 덜 남았으면 0으로 이동)
            op 사이면 op 끝나는 위치로 이동
        2-2. next : 초 + 10 (10초보다 덜 남았으면 마지막으로 이동)
            op 사이면 op 끝나는 위치로 이동
    3. 결과를 mm:ss로 바꾸기
*/
public class PCCP_1_동영상재생기 {
    public static void main(String[] args) {

    }


    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        // 1. 전부 초로 바꾸기
        // 비디오 초
        int video_seconds = Integer.parseInt(video_len.split(":")[0])*60 + Integer.parseInt(video_len.split(":")[1]);

        // 현재 위치 초
        int pos_seconds = Integer.parseInt(pos.split(":")[0])*60 + Integer.parseInt(pos.split(":")[1]);

        // op 시작 초
        int opS_seconds = Integer.parseInt(op_start.split(":")[0])*60 + Integer.parseInt(op_start.split(":")[1]);

        // op 끝 초
        int opE_seconds = Integer.parseInt(op_end.split(":")[0])*60 + Integer.parseInt(op_end.split(":")[1]);

        // 사용자 입력 처리
        for(String s: commands){
            // op이면 건너뛰기
            if(pos_seconds >= opS_seconds && pos_seconds <= opE_seconds){
                pos_seconds = opE_seconds;
            }

            if(s.equals("prev")){ // 10초 앞으로 움직이기
                if(pos_seconds <= 10){
                    pos_seconds = 0;
                }else{
                    pos_seconds -= 10;
                }
            }else if(s.equals("next")){ // 10초 뒤로 움직이기
                if(video_seconds - pos_seconds <= 10){
                    pos_seconds = video_seconds;
                }else{
                    pos_seconds += 10;
                }
            }

            // op이면 건너뛰기
            if(pos_seconds >= opS_seconds && pos_seconds <= opE_seconds){
                pos_seconds = opE_seconds;
            }
        }

        String answer = "";
        if(pos_seconds/60 < 10){
            answer += "0";
        }
        answer += pos_seconds/60 + ":";
        if(pos_seconds%60 < 10){
            answer += "0";
        }
        answer += pos_seconds%60;
//        answer = pos_seconds/60 + ":" + pos_seconds%60;
        return answer;
    }
}
