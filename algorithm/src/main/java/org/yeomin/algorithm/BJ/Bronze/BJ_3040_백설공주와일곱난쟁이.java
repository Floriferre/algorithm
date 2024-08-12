package baekjoon.Bronze;

import java.util.Arrays;
import java.util.Scanner;


/**
 * @author 정여민
 * @since 2023. 8. 11.
 * @see https://www.acmicpc.net/problem/3040
 * @git
 * @youtube
 * @performance
 * @category #
 * @note 9난쟁이 중 7난쟁이의 모자에 적힌 숫자의 합이 100이 되도록 하는 것을 구하라
 */
public class BJ_3040_백설공주와일곱난쟁이 {

	static int[] smallP = new int[9];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < 9; i++) {
			smallP[i] = sc.nextInt();
		}

		Combination(0, 0, new int[7]);

	}

	public static void Combination(int nth, int StartIndex, int [] choosed) {
		if(nth == choosed.length) {
			int sum = 0;
			for(int i = 0; i < 7; i++) {
				sum += choosed[i];
			}

			if(sum == 100) {
				for(int i = 0; i < 7; i++) {
					System.out.println(choosed[i]);
				}
			}	
//			System.out.println(Arrays.toString(choosed));
			return;
			
		}
		
		for (int i = StartIndex; i < smallP.length; i++) {
			choosed[nth] = smallP[i];
			Combination(nth + 1, i + 1, choosed);
		}
	
	}

}
