package Lecture.DAY2;

public class CompleteBinaryTreeTest {

	public static void main(String[] args) {
		
		int size = 9;
		
		CompleteBinaryTree<Character> tree = new CompleteBinaryTree<>(size);

		for(int i = 0; i < size; i++) {
			tree.add((char)(65+i));
		}
		
//		tree.bfs3();
		tree.dfsByPreorder(1);
		System.out.println();
//		tree.dfsByInorder(1);
//		System.out.println();
//		tree.dfsByPostorder(1);
//		System.out.println();
		tree.dfs();
		
	}

}
