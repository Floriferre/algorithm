package basecode;

public class UnionFInd_0828 {
	static int N;
	static int parents[];
	
	// 부모를 자기 자신으로 초기화
	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	// find
	private static int find(int x) {
		if(parents[x] == x) {
			return x;
		}
		
		return parents[x] = find(parents[x]);
	}
	
	
	public static void main(String[] args) {
		

	}

}
