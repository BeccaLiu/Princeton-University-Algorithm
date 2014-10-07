package Percolation;

public class Percolation {

	private boolean[] sites;
	private WeightedQuickUnionUF uf; // Percolation union-find
    //private WeightedQuickUnionUF fuf; // Full union-find
	private int ufSize;

	// public int openSitesCount;

	public Percolation(int N) { // create N-by-N grid, with all sites blocked
		if (N <= 0)
			throw new IllegalArgumentException();
		sites = new boolean[N*N+1];
		//puf = new WeightedQuickUnionUF(N*N+2);
        uf = new WeightedQuickUnionUF(N*N+1);
		ufSize = N;
		sites[N*N]=true;
		// openSitesCount=0;
	}

	public void open(int i, int j) { // open site (row i, column j) if it is not
										// already
		if (i < 1 || i > ufSize || j < 1 || j > ufSize)
			throw new IndexOutOfBoundsException();
		if (isOpen(i, j))
			return;
		sites[(i - 1)*ufSize+j - 1] = true;
		// openSitesCount++;
		if(i==1)
			uf.union((i-1)*ufSize+j-1, ufSize*ufSize);
		if (i > 1 && isOpen(i - 1, j)) // top
			uf.union((i - 1) * ufSize + (j - 1), (i - 2) * ufSize + (j - 1));
		if (i < ufSize && isOpen(i + 1, j))// bottom
			uf.union((i - 1) * ufSize + (j - 1), (i) * ufSize + (j - 1));
		if (j > 1 && isOpen(i, j - 1))// right
			uf.union(ufSize * (i - 1) + (j - 1), ufSize * (i - 1) + (j - 2));
		if (j < ufSize && isOpen(i, j + 1))// left
			uf.union(ufSize * (i - 1) + (j - 1), ufSize * (i - 1) + j);
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		if (i < 1 || i > ufSize || j < 1 || j > ufSize)
			throw new IndexOutOfBoundsException();
		return sites[(i - 1)*ufSize+j - 1];
	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		if (i < 1 || i > ufSize || j < 1 || j > ufSize)
			throw new IndexOutOfBoundsException();
		if (!isOpen(i, j))
			return false;
		return uf.connected(ufSize*ufSize, (i - 1) * ufSize + j - 1);
//		for (int m = 0; m < ufSize; m++) {
//			if (isOpen(1, m + 1) && uf.connected(m, (i - 1) * ufSize + j - 1))
//				return true;
//		}
		
	}

	public boolean percolates() {
		// does the system percolate?
//		for (int i = 0; i < ufSize; i++) {
//			for (int j = 0; j < ufSize; j++)
//				if (isOpen(1, i + 1) && isOpen(ufSize, j + 1)
//						&& uf.connected(i, ufSize * (ufSize - 1) + j))
//					return true;
//		}
		 for(int i=1;i<=ufSize;i++){
		
		 if(uf.connected(ufSize*ufSize, (ufSize - 1) * ufSize + i - 1));
		 }
		return false;
	}

	public static void main(String[] args) {
		// test client, optional
	}
}
