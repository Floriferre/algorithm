package basecode;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 좋은저녁_0823 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	static int N, M, V;
	static LinkNode[] graph;

	public static void main(String[] args) {

	}

	static class LinkNode {
		int i;
		int depth;
		LinkNode next;
		LinkNode parent;

		public LinkNode(int i, int depth, LinkNode next, LinkNode parent) {
			this.i = i;
			this.next = next;
		}

		@Override
		public String toString() {
			return "LinkNode [i=" + i + ", depth=" + depth + ", next=" + next + ", parent=" + parent + "]";
		}
		
	}

}
