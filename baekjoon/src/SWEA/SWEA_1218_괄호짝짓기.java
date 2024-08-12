package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
@author 정여민
@since 2023. 8. 4.
@see
@git
@youtube
@performance
@category #
@note 
*/

public class SWEA_1218_괄호짝짓기 {

	static Stack<Character> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
//		int T = Integer.parseInt(st.nextToken());
		
		
		for(int t = 0; t < 10; t++) {
			boolean check = true;
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());

			
			String str = st.nextToken();
			
			
			// 괄호를 저장할 스택 
			stack = new Stack<>();
			
			sb.append("#").append(t+1).append(" ");
			
			for(int i = 0; i < str.length(); i++) {
				char temp = str.charAt(i);
				if(temp == '{' || temp == '[' || temp == '('|| temp == '<') {
					stack.push(str.charAt(i));
					continue;	
				}
				else {
					// 스택이 비어있을 때
					if(stack.isEmpty()) {
						check = false;
						break;
					}
				
					// 스택의 최상단과 짝이 맞을 때
					if(temp == '}' && stack.peek()=='{') {
						stack.pop();
						continue;
					}else if(temp == ']' && stack.peek()== '[') {
						stack.pop();
						continue;
					}else if(temp == ')' && stack.peek()== '(') {
						stack.pop();
						continue;
					}else if(temp == '>' && stack.peek()== '<') {
						stack.pop();
						continue;
					}else {
						check = false;
						break;
					}
					
					
				}
			}
			// 스택이 정상일 때 
			if(check) {
				sb.append(1 + "\n");
			}else if(!check) {
				sb.append(0+ "\n");
			}else if(!stack.isEmpty()) {	//스택에 남아있는 게 있으면 유효하지 X
				sb.append(0+ "\n");
			}
			
			
			
		}
		
		System.out.println(sb);
		
		
	}

}
