package baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2024-03-6, 수, 10:59
@see https://www.acmicpc.net/problem/2504
@git
@youtube
@performance
@category #
@note
    <문제>
    괄호를 만드는 4개의 기호 (, ), [, ]
    올바른 괄호열 : () []

    1. () : 2
    2. [] : 3
    3. (X) : 2 x X
    4. [X] : 3 x X

    괄호열을 읽고 그 괄호값을 앞에서 정의한대로 계산하여 출력하기

    <입력>
    첫째줄 : 괄호열을 나타내는 문자열, 1 <= String <= 30

    <출력>
    첫째줄 : 괄호열의 값을 나타내는 정수를 출력, 올바르지 못한 괄호열이면 0 출력

    <풀이>
    괄호가 올바른지 아닌지 구하려면 stack을 쓰면 된다
    문자열을 하나씩 돌면서 짝이 맞는 괄호가 생기면 괄호 타입에 따라 2 혹은 3을 곱하고,
    스택이 비면 +로 연결한다
    만약 짝이 맞지 않는 괄호가 있으면 0을 출력한다.

    곱셉을 나중에 짝이 맞을 때 하는 게 아니라, 바로 해버리자
*/
public class BJ_2504_괄호의값 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		char[] bracket = st.nextToken().toCharArray();

		Deque<Character> brackets = new ArrayDeque();

		int result = 0;
		int mid = 1;

		for (int i = 0; i < bracket.length; i++) {
			if (bracket[i] == '(') {
				brackets.addLast(bracket[i]);
				mid *= 2;

			} else if (bracket[i] == '[') {
				brackets.addLast(bracket[i]);
				mid *= 3;
			} else if (bracket[i] == ')') {
				// 올바르지 않은 괄호이면
				if (brackets.isEmpty() || brackets.peekLast() != '(') {
					result = 0;
					break;
				} else if (bracket[i - 1] == '(') {
					result += mid;
				}
				brackets.pollLast();
				mid /= 2;   // 해당 괄호가 끝났으니

			} else if (bracket[i] == ']') {
				// 올바르지 않은 괄호이면
				if (brackets.isEmpty() || brackets.peekLast() != '[') {
					result = 0;
					break;
				} else if (bracket[i - 1] == '[') {
					result += mid;
				}
				brackets.pollLast();
				mid /= 3;   // 해당 괄호가 끝났으니
			}
		}
		if (!brackets.isEmpty()) {
			sb.append(0).append("\n");
		} else {
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
}
