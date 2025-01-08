/*
@author 
@since 2025-01-6, 월, 11:2
@see
@git
@youtube
@performance
@category #
@note
    <문제>
    길이가 N인 수식.
    수식 : 0~9 정수, 연산자(+, -, x)
    연산자 우선 순위는 모두 동일. 왼쪽에서부터 순서대로 계산

    수식에 괄호 추가 -> 괄호 안의 식을 먼저 계산
    중첩된 괄호 사용 X
    괄호 안에는 연산자가 1개만 들어가야 함

    수식이 주어졌을 때 괄호를 적절히 추가하여 만들 수 있는 식의 결과의 최댓값 구하기
    추가하는 괄호의 개수 제한 X, 추가하지 않아도 된다

    <입력>
    첫째줄 : 수식의 길이 N (1~19)
    둘째줄 : 수식
            정수로 시작, 정수와 연산자가 번갈아 나옴

    <출력>
    첫째줄에 괄호를 적절하게 추가하여 얻을 수 있는 결과의 최댓값 출력

    정답은 -2^31 ~ 2^31 : 정수 범위 안이다


    <풀이>
    수식의 길이가 1~19면 정수 = 연산자 +1
    대충 정수 10개까지니까 괄호가 등장할 수 있는 구역은 5개? => 충분히 재귀로 풀이 가능할 듯!

    1. 수식 입력 받기 : 정수랑 연산자 같이 입력받기
        - 포인터(인덱스) 사용하여 어느 위치인지 확인
    2. 괄호
        2-1. 없을 때 : 현재값 + 연산자 -> 2칸 뒤로 & 큐에 넣기  + 포인터 2칸 뒤로 이동
        2-2. 있을 때 : 현재값 연산자 정수 한 번에 묶어서 계산 & 큐에 넣기 + 포인터 3칸 뒤로 이동
    3. 마지막에 전부 돌면서 계산

*/


import java.util.*;
import java.io.*;

public class Main {
    static int N;   // 수식의 길이
    static int answer = Integer.MIN_VALUE;  // 결과값
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        
        // 수식 입력받기
        String [] items = new String[N];    // 수식 담을 배열



        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();

        for(int i=0; i < s.length(); i++){
            items[i] = s.charAt(i) + "";
        }

        solution(0,  new ArrayDeque<String>(), items);

        sb.append(answer);
        System.out.println(sb);
    
    }
    
    // 풀이를 위한 재귀함수
    // index : 현재 어느 위치인지
    // result : 현재까지의 계산값
    // q : 현재까지의 계산을 담은 큐
    // items : 수식
    public static void solution(int index,  Queue<String> q, String [] items){
        // 종료조건
        if(index > N-1){

            // 큐에 있는 계산 하기
            int res = Integer.parseInt(q.poll());
            while(!q.isEmpty()){  // 이후로 하나씩 돌리기
                String t = q.poll();

                switch(t.charAt(0)){
                    case '+':
                        res = res + Integer.parseInt(q.poll());
                        break;
                    case '-':
                        String k = q.poll();
                        try{
//                           res = res - Integer.parseInt(q.poll());
                            res -= Integer.parseInt(k);
                        }
                        catch (Exception e){
                            System.out.println(k + ": 여기에서 문제");
                        }
                        break;
                    case '*':
                        res = res * Integer.parseInt(q.poll());
                        break;
                }
            }

            answer = Math.max(answer, res);
            return;

        }


        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        for(String c : q){
            q1.add(c);
            q2.add(c);
        }
        q.clear();  // 다 쓴 큐는 삭제

        // 괄호 없을 때
        q1.add(items[index]);
        if(index != N-1){   // 연산자 남았을 때
            q1.add(items[index+1]);
        }
        solution(index + 2, q1, items);

        // 괄호 있을 때
        if(index != N-1){
            int temp = 0;
            switch (items[index+1].charAt(0)){
                case '+':
                    temp = Integer.parseInt(items[index]+"") + Integer.parseInt(items[index+2]+"");
                    break;
                case '-':
                    temp = Integer.parseInt(items[index]+"") - Integer.parseInt(items[index+2]+"");
                    break;
                case '*':
                    temp = Integer.parseInt(items[index]+"") * Integer.parseInt(items[index+2]+"");
                    break;
            }
            q2.add(temp + "");
            if(index+3 < N){
                q2.add(items[index+3] + "");
            }
            solution(index+4, q2, items);
        }else{
            q2.add(items[index]);
            solution(index+1, q2, items);
//            q2.add(items[index+1]);
        }



    }
    
    
}