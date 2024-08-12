package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 정여민
 * @since 2023. 8. 8.
 * @see https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
 * @git
 * @youtube
 * @performance
 * @category #
 * @note 사칙연산으로 구성되어 있는 식을 이진트리로 표현 임의의 정점에 연산자가 있으면 해당 연산자의 왼쪽 서브트리와 오른쪽 서브트리의
 *       결과를 사용해서 해당 연산자를 적용 사직연산 & 양의 정수로만 구성된 이진 트리 -> 적절한 식인지 확인, 계산이 가능하다면
 *       1, 불가하다면 0을 출력 <제약사랑> 1. Test case 10개 2. 총 노드의 갯수 200개를 넘어가지 X 3. 트리는
 *       완전 이진트리 형식, 노드당 하나의 연산자 또는 숫자만 저장 <입력> 첫 줄 : N (정점의 총 수) N줄 : 각각의 정점 정보
 *       (정점의 알파벳, 왼쪽 자식, 오른쪽 자식의 정점번호) 정점 번호 : 1~N까지 정수, 루트는 무조건 1 입력에서 이웃한 숫자,
 *       연산자, 자식 정점의 번호는 모두 공백 >> 클래스로 트리 만들어서 써야한다!
 * 
 */

public class SWEA_1233_사칙연산유효성검사 {
	static int[] visit;
	static int[][] edge;
	static String[] node;
	static int n;
	static Stack<Integer> stack;
	static int flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		
		// 테스트 케이스 10개
		for (int t = 1; t <= 10; t++) {
			// 초기화 노드 개수를 입력 받아 노드의 연산자(피연산자)와 간선, 방문 여부 등을 저장할 변수들 초기화
			n = Integer.parseInt(br.readLine());
			node = new String[n + 1];
			edge = new int[n + 1][n + 1];
			visit = new int[n + 1];
			stack = new Stack<>();
			flag = 1;

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				// 어떤 노드에 대한 정보인가?
				int from = Integer.parseInt(st.nextToken());
				node[i] = st.nextToken();

				// 자식 노드가 있을 경우 추가
				for (int j = 0; j < 2; j++) {
					if (st.hasMoreTokens()) {
						int to = Integer.parseInt(st.nextToken());
						edge[from][to] = 1;
					}
				}

			}
			// DFS 돌리기
			dfs(1); // 1번 노드부터 dfs 돌리기 
			if(stack.size()<1) {
				flag = 0;
			}
			sb.append("#" + t + " " + flag + "\n");
			
			
		}
		System.out.println(sb);

	}

	public static void operation(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
			// 스택에 숫자가 2개보다 적으면 연산 이상함
			if (stack.size() < 2) {
				flag = 0;
				return;
			}
			int a = stack.pop();
			int b = stack.pop();

			if (s.equals("+"))
				stack.push(b + a);
			else if (s.equals("-"))
				stack.push(b - a);
			else if (s.equals("*"))
				stack.push(b * a);
			else if (s.equals("/")) {
				// divisor가 0인 경우
				if (a == 0) {
					a = 1;
				}

				stack.push(b / a);
			}
		} else { // 연산자가 아닐 경우 숫자로 변환하여 스택에 넣기
			stack.push(Integer.parseInt(s));
		}
	}

	public static void dfs(int x) {
		if (flag == 0) {
			return;
		}

		visit[x] = 1;
		for (int i = 1; i <= n; i++) {
			if (edge[x][i] == 1 && visit[i] == 0) {
				dfs(i);
			}
		}
		operation(node[x]);
	}

	public class CompleteBinaryTree<T> {
		Object[] nodes;
		int lastIndex = 0;
		int SIZE;

		public CompleteBinaryTree(int size) {
			this.SIZE = size;
			nodes = new Object[size + 1];
		}

		public boolean isEmpty() {
			return lastIndex == 0;
		}

		public boolean isFull() {
			return lastIndex == SIZE;
		}

		public boolean add(T data) {
			if (isFull()) {
				return false;
			}
			nodes[++lastIndex] = data;
			return true;
		}

	}

}
