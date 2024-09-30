/* 
    <문제>
    돗자리 깔기
    돗자리 한변의 크기 5, 3, 2 중 가장 큰 거 깔기
    공원의 다른 사람과 겹치면 안 됨
    아무런 돗자리도 깔 수 없는 경우 -1 
    
    <입력>
    mats : 돗자리의 한 변의 길이
    park : 현재 공원 자리 배치도
    
    <출력>
    아무런 돗자리도 깔 수 없는 경우 -1 리턴
    
    <풀이>
    걍.. 첫칸부터 쭉 비교
    1. 빈칸을 발견하면 .equals("-1") 5칸짜리부터 비교 시작
    현재 위치 R + 5까지 
    현재 위치 C + 5까지 
    전부 비어있으면 그 돗자리 깔기
    2. 중간에 누군가 있으면 3칸, 2칸 비교 
    3. 만약 5칸짜리면 바로 리턴하고 아니면 쭉 비교해서 제일 큰 돗자리 찾기
    
*/
class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        
        boolean check = true;
        for(int m : mats){
        for(int i=0; i <= park.length -m; i++){
            if(answer > m){
                break;
            }
            for(int j=0; j <= park[0].length -m; j++){
                if(answer > m){
                break;
                }
                if(park[i][j].equals("-1")){    // 빈 칸을 찾으면
                    check = true;
                     for(int r=i; r < i + m; r++ ){
                         for(int c=j; c< j+m; c++){
                             if(!park[r][c].equals("-1")){
                                 check = false;
                                 break;
                             }
                         }
                     }
                        // 다 체크했는데 check가 true면 
                        if(check){
                            if(answer < m){
                                answer = m;
                            }
                        }
                } 
                }
                      
            } 
        }
        if(answer == 0){
            return -1;
        }
        return answer;
    }
}