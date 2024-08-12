package Lecture.stack;

public class StackTest {

	public static void main(String[] args) {
		IStack<String> stack =  new LinkedListStack<>();

		System.out.println(stack.size() + " // " + stack.isEmpty());
		
		stack.push("SJS");
		stack.push("KSH");
		stack.push("FOREVER");
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.size() + " // " + stack.isEmpty());
		System.out.println(stack.pop());
	
	}

}
