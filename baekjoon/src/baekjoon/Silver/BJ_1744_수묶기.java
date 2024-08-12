package baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BJ_1744_수묶기 {
	static int N;
	static List<Integer> nn = new ArrayList<>();
	static List<Integer> pn = new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n > 0)
				pn.add(n);
			else
				nn.add(n);
		}

		Collections.sort(pn, Collections.reverseOrder());
		Collections.sort(nn);

		int sum = 0;
		int i = 0;
		while (i < pn.size()) {
			if (i + 1 < pn.size() && pn.get(i) != 1 && pn.get(i + 1) != 1)
				sum += pn.get(i++) * pn.get(i++);
			else
				sum += pn.get(i++);
		}

		i = 0;
		while (i < nn.size()) {
			if (i + 1 < nn.size() && nn.get(i) != 1 && nn.get(i + 1) != 1)
				sum += nn.get(i++) * nn.get(i++);
			else
				sum += nn.get(i++);
		}

		sb.append(sum + "\n");
		System.out.println(sb);

	}

}
