package Programmers;
/*
    <문제>
    물류센터 n개
    로봇 x개
    1초마다 한칸 움직임. 다음 포인트로 이동할 때 최단경로로 이동, r좌표를 먼저 이동
    마지막 포인트에 도착하면 운송 마치고 물류센터 벗어남
    움직이는 동안 충돌 횟수(2대 이상 모이는 것)

    <입력>
    points : 운송 포인트 n개의 좌표를 담은 2차원 정수배열
    routes : 로봇 x대의 운송 경로를 담은 2차원 정수배열

    <출력>
    충돌횟수

    <풀이>
    1. 차례로 움직이기
        1-1. 목표랑 비교해서 r좌표 먼저 이동하고 r좌표 같으면 c좌표 이동하기! (1base index인 것을 잊지 말자)
    2. 위치 기록하기 (2차원 배열 Boolean으로 할 것)
    3. 2차원 배열에서 0 아닌 것 찾아서 충돌 횟수 기억하기, 하고 리셋

*/
public class PCCP_3_충돌위험찾기 {
    class point{    // 좌표 나타낼 것
        int x;
        int y;

        int destX;
        int destY;

        public point(int x, int y, int destX, int destY){
            this.x = x;
            this.y = y;
            this.destX = destX;
            this.destY = destY;
        }
    }



    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        point[] robots = new point[routes.length];  // 로봇의 좌표를 담아둘 것







        return answer;
    }
}
