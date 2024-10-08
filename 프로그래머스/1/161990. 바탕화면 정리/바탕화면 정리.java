/*
    <문제>
    코드를 바탕화면 아무데나 저장 
    저장한 파일들 전부 삭제
    
    바탕화면 : 정사각형 격자판 
    가장 왼쪽 위 : 0, 0
    . : 빈칸
    # : 파일이 있는 칸

    최소한의 이동거리를 갖는 한 번의 드래그로 모든 파일을 선택해서 지우려고 함
    
    드래그 방법 
    : 격자점 S를 마우스 왼쪽 버튼으로 클릭한 상태로 격자점 E로 이동 후 버튼 떼는 행동
    드래그한 거리 : |x좌표 차| + |y좌표 차|
    
    <입력>
    wallpaper : 컴퓨터 바탕화면의 상태를 나타내는 문자열 배열 
    
    <출력>
    바탕화면의 파일들을 한 번에 삭제하기 위해 최소한의 이동거리를 갖는 드래그의 시작점과 끝점을 담은 정수 배열
    
    <풀이>
    가장 왼쪽 위 : 파일들 중 x좌표 제일 작은 것, y좌표 가장 작은 것
    가장 오른쪽 아래 : 파일들 중 x좌표 제일 큰 것, y좌표 가장 큰 것
    
*/

class Solution {
    public int[] solution(String[] wallpaper) {
        int[] answer = {};
        answer = new int[4];
        
        int lux = Integer.MAX_VALUE;
        int luy = Integer.MAX_VALUE;
        int rdx = Integer.MIN_VALUE;
        int rdy = Integer.MIN_VALUE;
        for(int i=0; i < wallpaper.length; i++){
            for(int j=0; j < wallpaper[0].length(); j++){
                if(wallpaper[i].charAt(j) == '#'){
                    lux = Math.min(lux, i);
                    luy = Math.min(luy, j);
                    rdx = Math.max(rdx, i+1);
                    rdy = Math.max(rdy, j+1);
                }
            }
        }
        
        answer[0] = lux;
        answer[1] = luy;
        answer[2] = rdx;
        answer[3] = rdy;
        
        return answer;
    }
}