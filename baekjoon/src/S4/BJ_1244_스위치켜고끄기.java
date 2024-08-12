package S4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 1.
@see
@git
@youtube
@performance
@category #
@note 
	스위치 개수 : N
	1 : 켜져있음 / 0 : 꺼져있음


*/

public class BJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st = new StringTokenizer(br.readLine());
		
		
		// 스위치 갯수
		int num = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 스위치 상태
		int [] switches = new int[num];
		
		st = new StringTokenizer(br.readLine());
		// 학생수
		int student = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		// 학생 성별 + 학생이 받은 수가 주어짐 -> 여학생 남학생 나누어 배열을 받을 것! 맵이 나으려나? 
		
		
	}

}
