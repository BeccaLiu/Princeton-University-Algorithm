package Percolation;
import java.util.Arrays;
import java.util.Scanner;

public class UnionFind {

	public static void main(String[] args) {
		WeightedQuickUnionUF  uf= new WeightedQuickUnionUF(10);
		Scanner s=new Scanner(System.in);
		while(s.hasNextLine()){
			int a=s.nextInt();
			int b=s.nextInt();
			if(!uf.connected(a, b)){
			 uf.union(a, b);
			 System.out.println(Arrays.toString(uf.id));
			 System.out.println(Arrays.toString(uf.sz));
			}			
		}
	}

}

//class UF {
//	protected UF(int N) {
//
//	}
//
//	public void union(int a, int b) {
//
//	}
//
//	public boolean connected(int a, int b) {
//		return false;
//	}
//}

class QuickFindUF { // eager approach,store the group id in array
	// if have N union command on N objects, it will take quadratic time.
	int[] id;

	public QuickFindUF(int N) {// initial O(N)
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	public boolean connected(int a, int b) { // check O(1)
		return id[a] == id[b];
	}

	public void union(int a, int b) { // union O(N)
		int aid = id[a];
		int bid = id[b];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == aid)
				id[i] = bid;
		}
	}
}

class QuickUnionUF {// lazy approach, store the id of the parents node
	// find is to expensive
	int[] id;

	public QuickUnionUF(int N) { // initial O(N)
		id = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
		}
	}

	private int root(int a) {
		while (a != id[a]) {
			a = id[a];
		}
		return a;
	}

	public boolean connected(int a, int b) { // O(N)
		return root(a) == root(b);
	}

	public void union(int a, int b) { // O(N) ,can get very tall tree
		int i = root(a);
		int j = root(b);
		id[i] = j;

	}

}

class WeightedQuickUnionUF {// lazy approach  N+M lg N
	static int[] id;// store the id of the parents node
	static int[] sz; // store the size of sub node of the current node

	public WeightedQuickUnionUF(int N) { // initial O(N)
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; i++) {
			id[i] = i;
			sz[i]=1;
		}
	}

	private int root(int a) {
		while (a != id[a]) {
			a = id[a];
		}
		return a;
	}

	public boolean connected(int a, int b) { // O(lgN)
		return root(a) == root(b);
	}

	public void union(int a, int b) { // O(lgN) ,depth of the tree can be control
										// in the logarithm to the base 2 of N

		int i = root(a);
		int j = root(b);
		if(i==j)
			return;
			
		if (sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}

	}
	class PathCompressionUF {// any sequence of M union-find in N objects make<=c(N+M lg*N) access
		int[] id;// store the id of the parents node
		int[] sz; // store the size of sub node of the current node

		public PathCompressionUF(int N) { // initial O(N)
			id = new int[N];
			sz = new int[N];
			for (int i = 0; i < N; i++) {
				id[i] = i;
				sz[i]=1;
			}
		}

		private int root(int a) {
			while (a != id[a]) {
				
			    id[a]=id[id[a]];//make every other nod point to its grandpaent
				a = id[a];
			}
			return a;
		}

		public boolean connected(int a, int b) { // O(lgN)
			return root(a) == root(b);
		}

		public void union(int a, int b) { // O(lgN) ,depth of the tree can be control
											// in the logarithm to the base 2 of N

			int i = root(a);
			int j = root(b);
			if(i==j)
				return;
				
			if (sz[i] >= sz[j]) {
				id[j] = i;
				sz[i] += sz[j];
			} else {
				id[i] = j;
				sz[j] += sz[i];
			}

		}
	}
}


