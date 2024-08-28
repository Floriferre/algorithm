package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
@author 정여민
@since ${2023.08.01}
@see https://www.acmicpc.net/problem/11729
@git
@youtube
@performance
@category # 재귀
@note 
*/

public class BJ_11729_하노이탑 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// 하노이 탑 - 재귀를 사용하여 풀기
		// 원판을 다른 곳으로 옮겨야 함
		// start, mid, end 막대
		// start에 있는 것을 end로 옮긴다 
		// start -> mid로 제일 큰 원판을 제외하고 옮기고, 제일 큰 원판을 end로 올긴다
		// mid에 있는 원판을 end로 옮기는 것과 똑같음
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = br.read();
		
		// 총 이동 횟수 
		int count = 0;
		
		
	}
	
	public int Hanoi(int N, int start, int mid, int end) {
		// 종료 조건 - 원판이 하나일 때 그것만 옮기면 끝!
		if(N==1) {
			System.out.println(start + " " +  end);
		}
		
		return 0;
	}

}
