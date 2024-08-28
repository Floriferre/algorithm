package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

@author 본인이름
@since 2023. 8. 1.
@see
@git
@youtube
@performance
@category #
@note 

*/

public class SWEA_Flatten {
	static int dump;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer	st;
		
		int [] box = new int [100];
		
		// 테스트 케이스 10개
		for(int t = 0 ; t < 10; t++) {
			st = new StringTokenizer(br.readLine());
			dump = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 100; i++) {
				box[i] = Integer.parseInt(st.nextToken());
			}
//			Arrays.sort(box);
			// 상자 정렬 후 높이 맞추기
//			for(int i = 0; i < dump; i++) {
//				if (box[99] - box[0] >= 1) {					
//					box[0] +=1;
//					box[99] -= 1;
//					Arrays.sort(box);
//				}else {
//					break;
//				}
//			}
			flatten(box, 0);
			
			
			System.out.printf("#%d %d\n", t+1, box[99] - box[0]);
		}
		
	}
	
	public static void flatten(int [] arr, int num) {
		Arrays.sort(arr);
		if((arr[0] == arr[99]) | (num == dump)) {
			return;
		}
		
		arr[0] += 1;
		arr[99] -= 1;
		flatten(arr, num+1);
	}

}
