/*
    <문제>
    n개의 퍼즐 
     
    diff : 현재 퍼즐 난이도
    time_cur : 현재 퍼즐 소요 시간
    time_prev : 이전 퍼즐 소요 시간
    level : 숙련도
    
    1. diff <= level : 퍼즐 틀리지 X, time_cur 걸림
    2. diff > level : 퍼즐 총 diff - level 틀림, time_cur씩 사용, time_prev 사용해 이전 퍼즐 다시 풀기(이건 안 틀림)
        diff-level번 틀린 후에 다시 풀면 time_cur 시간 사용하여 해결
    
    <입력>
    diff : 퍼즐 난이도, 1차원 정수 배열
    times : 소요시간, 1차원 정수 배열
    limit : 전체 제한 시간
    
    <출력>
    제한 시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최솟값을 정수로 return 

    <풀이>
    1. 브루트포스 (1부터 레벨 하나씩 증가) : 일부 케이스에서 시간 초과
    2. 이진탐색
        left = 1
        right = diff에서 가장 큰 거 찾기
*/


class Solution {
    
       public int solution(int[] diffs, int[] times, long limit) {

        // int level = 0;
        long time_remain = limit;
        // // 1. 문제 개수 만큼 돌리기
        // for(int i = 0; i < diff.length; i++){
        //     if(diff <= level){  // 문제가 쉬우면
        //         time_remain -= times[i];
        //     }else{  // 문제가 어려우면
        //         time_remain -= (diff-level)*(times[i-1] + times[i]) + times[i];
        //     }
        // }


        // do{
        //     level++;
        //     time_remain = limit;
        //     time_remain = sol(diffs, times, limit, level, time_remain);
        // }while(time_remain < 0);

        // 1. diff에서 가장 큰 값 찾기
       int max_diff = 0;
       for(int i : diffs){
           max_diff = Math.max(max_diff, i);
       }
           
        int answer = Integer.MAX_VALUE;
        // 2. 이진탐색 가보자고
        int left = 1;
        int right = max_diff;
        int level = (left + right )/2;   
           
        while(left <= right){
            time_remain = limit;
            time_remain = sol(diffs, times, limit, level, time_remain);
            if(time_remain < 0){    // level이 부족
                left = level + 1;
                level = (left + right )/2; 
            }else{  // answer 업데이트 하고, answer이 더 작은 값이면 계속 탐색, 아니면 종료
             if(level < answer){
                    answer = level;
            }
                right = level -1 ;
                level = (left + right )/2;
                
            }
        }

        return answer;
    }

    public static long sol(int[] diff, int[] times, long limit, int level, long time_remain ){
        // 1. 문제 개수 만큼 돌리기
        for(int i = 0; i < diff.length; i++){
            if(diff[i] <= level){  // 문제가 쉬우면
                time_remain -= times[i];
            }else{  // 문제가 어려우면
                time_remain -= (diff[i]-level)*(times[i-1] + times[i]) + times[i];
            }
        }
        return time_remain;
    }
}