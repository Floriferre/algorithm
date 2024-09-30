package Programmers.level1;
    /*
    <문제>
    2차원 격자 보드판
    한 칸을 골랐을 때 위/아래/왼쪽/오른쪽 칸 중 같은 색깔로 칠해진 칸의 개수 구하기

    <입력>
    board : 각 칸에 필해진 색깔 이름
    h, w : 고른 칸의 위치

    <출력>
    같은 칸으로 칠해져있는 칸의 개수

    <풀이>
    걍... 같은 색인지 사방으로 세기
    int []dx = {-1, 1, 0, 0}
    int []dy = {0, 0, -1, 1}

    문제는 h나 w가 딱 경계인 경우 -> if문으로 잘 걸러줄 것
*/

public class PCCE_9_이웃한칸 {




    class Solution {
        public int solution(String[][] board, int h, int w) {
            int answer = 0;

            // 상하좌우
            int []dx = {-1, 1, 0, 0};
            int []dy = {0, 0, -1, 1};


            // 상하좌우 체크하기
            for(int d=0; d < 4; d++){
                int nx = h + dx[d];
                int ny = w + dy[d];

                if(isIn(nx, ny, board.length, board.length)){
                    if(board[nx][ny].equals(board[h][w])){
                        answer++;
                    }
                }

            }


            return answer;
        }

        public static boolean isIn(int nx, int ny, int r, int c){
            return nx >=0 && nx < r && ny>=0 && ny <c;
        }
    }
}
