/*
    <문제>
    로봇 강아지 산책
    O : 지나다니는 길
    X : 장애물
    명령 : 방향 거리
    
    1. 주어진 방향으로 이동할 때 공원을 벗어나는 지 확인
    2. 주어진 방향으로 이동할 때 장애물을 만나는 지 확인
    -> 움직이지 X
    
    <입력>
    park : 공원을 나타내는 문자열 배열
    routes : 로봇 강아지가 수행할 명령이 담긴 문자열 배열
    
    <출력>
    모든 명령을 수행 후 놓인 위치 [세로 방향 좌표, 가로 방향 좌표]
    
    <풀이>
    1. 공원을 나타내는 배열을 2차원 배열로 변경
    2. dx = { -1, 1, 0, 0}, dy = { 0, 0, -1, 1} N, S, W, E 순
    3. 명령 수행하기
        3-1. 방향에 맞는 거리까지 장애물이 있거나 밖으로 벗어나는 지 확인
        3-2. 갈 수 있으면 이동, 없으면 다음 명령 수행
    4. 마지막 위치 배열에 담아 출력

*/

class Solution {
    
    // N S W E
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};
    
    public int[] solution(String[] park, String[] routes) {
        int[] answer = {};
        answer = new int [2];   // 세로, 가로
        
        
        // 1. 공원을 나타내는 배열을 2차원 배열로 변경
        String [][] map = new String[park.length][park[0].length()];
        
        int [] start = new int [2];
        
        for(int i=0; i < park.length; i++){
            for(int j=0; j < park[0].length(); j++){
                map[i][j] = park[i].charAt(j) + "";
                if(map[i][j].equals("S")){  // 시작지점 기록
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        
        // 명령 수행하기
        for( String s : routes){
            String [] currentRoutes = s.split(" ");
            

            int index = 0;
            switch(currentRoutes[0].charAt(0)){
                case 'N':
                    index = 0;
                    break;
                case 'S':
                    index = 1;
                    break;
                case 'W': 
                    index = 2;
                    break;
                case 'E':
                    index = 3;
                    break;
            }
            
            boolean check = false;
            for(int l=1; l<= Integer.parseInt(currentRoutes[1]); l++){
                int nx = start[0] + dx[index]*l;
                int ny = start[1] + dy[index]*l;
                
                if(!isIn(park.length, park[0].length(), nx, ny) || map[nx][ny].equals("X")){  // 범위 밖에 있고 장애물이면 이동 불가능  
                    check = false;
                    break;
                }else{
                    check = true;
                }
            }
            
            // 이동 가능하면 이동
            if(check){
                start[0] += dx[index]*Integer.parseInt(currentRoutes[1]);
                start[1] += dy[index]*Integer.parseInt(currentRoutes[1]);
            }
        }
        
        answer[0] = start[0];
        answer[1] = start[1];
        
        
        return answer;
    }
    
    public static boolean isIn(int w, int h, int nx, int ny){
        return nx >= 0 && nx < w && ny >= 0 && ny < h;
    }
}