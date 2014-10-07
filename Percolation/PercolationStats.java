
import algs4.StdRandom;
import stdlib.StdStats;

import java.sql.Time;

public class PercolationStats {
	
	private int T = 0;
	private double[] fraction = null;
	//private double mean = 0.0;
	//private double stderr = 0.0;

	public PercolationStats(int N, int T) { // perform T independent
											// computational experiments
											// on an
											// N-by-N grid
		if (N <= 0 || T <= 0)
			throw new IllegalArgumentException();
		this.T = T;
		fraction = new double[T];
		Percolation aPercolation;
        
		for (int t = 0; t < T; t++) {
			long begintime = System.currentTimeMillis();
			int openSitesCount = 0;
			aPercolation = new Percolation(N);
			while (!aPercolation.percolates()) {
				int i, j;
				do {
					i = StdRandom.uniform(1, N + 1);
					j = StdRandom.uniform(1, N + 1);
				} while (aPercolation.isOpen(i, j));
				aPercolation.open(i, j);
				openSitesCount++;

			}

			fraction[t] = ((double) openSitesCount) / (N * N);
			long endtime = System.currentTimeMillis();
			System.out.println(endtime - begintime);

		}

	}

	public double mean() {
		// sample mean of percolation threshold
		
		return StdStats.mean(fraction);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		if (fraction.length <= 1)
			return Double.NaN;
		
		return StdStats.stddev(fraction);
	}

	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return mean() + (-1) * (1.96 * stddev()) / Math.sqrt(T);
	}

	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return mean() + (1.96 * stddev()) / Math.sqrt(T);
	}

	public static void main(String[] args) {
		// test client, described below
		
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		PercolationStats p = new PercolationStats(n, t);
		System.out.println("mean                    = " + p.mean());
		System.out.println("stddev                  = " + p.stddev());
		System.out.println("95% confidence interval = " + p.confidenceLo()
				+ ", " + p.confidenceHi());

	}
}
