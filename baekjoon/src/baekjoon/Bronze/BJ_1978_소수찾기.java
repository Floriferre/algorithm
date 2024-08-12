package baekjoon.Bronze;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-4, 월, 16:36
@see https://www.acmicpc.net/problem/1978
@git
@youtube
@performance
@category #
@note
    <문제>
    주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

    <입력>
    첫째줄 : 수의 개수 N (1<= N <= 100)
    둘째줄 : N개의 수 (1 <= 수 <= 1,000)

    <출력>
    소수의 개수 출력

    <풀이>
    에라토스테네스의 체 원리를 사용해야 한다.
    - 소수를 판별할 범위만큼 배열을 할당하여, 해당하는 값을 넣어주고, 이후에 하나씩 지워나가는 방법을 이용한다.
    1. 배열을 생성하여 초기화한다.
    2. 2부터 시작해서 특정 수의 배수에 해당하는 수를 모두 지운다.(지울 때 자기자신은 지우지 않고, 이미 지워진 수는 건너뛴다.)
    3. 2부터 시작하여 남아있는 수를 모두 출력한다.

*/
public class BJ_1978_소수찾기 {

    public static boolean [] prime = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        List<Integer> nums = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }

        nums.sort((o1, o2) -> o2 - o1);

        prime = new boolean[nums.get(0) + 1];
        prime = eratosthenes(nums.get(0));

        int count = 0;
        for (Integer num : nums) {
//            System.out.println("num : " + num);
            if(prime[num] == true){
                count++;
            }
        }
//        System.out.println(Arrays.toString(prime));
        System.out.print(count);
    }


    public static boolean[] eratosthenes(int n){

        //0부터 n까지의 수이기 때문에 n+1을 할당한다.
        boolean[] prime = new boolean[n+1];
        Arrays.fill(prime, true);
        // 0과 1은 소수가 아니기 때문에 false 저장
        prime[0] = false;
        prime[1] = false;

        // n의 제곱근까지 나눈다.
        for (int i = 2; i <= Math.sqrt(n); i++) {
            // 소수가 아니라면 뒤의 코드 스킵
            if(prime[i] == false){
                continue;
            }

            // 2부터 배수에 해당하는 수를 false로 변환
            for (int j = i*i; j <= n; j += i) {
                prime[j] = false;
            }
        }
        return prime;
    }

}
