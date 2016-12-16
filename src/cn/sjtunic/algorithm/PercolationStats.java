package cn.sjtunic.algorithm;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
	private double[] experiments;
	private Percolation P;
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		experiments = new double[trials];
		for(int i=0; i<trials; i++) {
			double count = 0;
			P = new Percolation(n);
			while(!P.percolates()) {
				int row = StdRandom.uniform(n)+1,
					col = StdRandom.uniform(n)+1;
				if(!P.isOpen(row, col)) {
					P.open(row, col);
					count++;
				}
					
			}
			experiments[i] = count/(n*n);
		}
	}
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(experiments);
	}
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(experiments);
	}
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		return mean()-1.96*stddev()/Math.sqrt(experiments.length);
	}
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean()+1.96*stddev()/Math.sqrt(experiments.length);
	}

	public static void main(String[] args) {
		// test client (described below)
		StdOut.println("start simulation......\n");
		Stopwatch timer = new Stopwatch();

		PercolationStats Ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		StdOut.println("mean                    = " + Ps.mean() + "\n");
		StdOut.println("stddev                  = " + Ps.stddev() + "\n");
		StdOut.println("95% confidence interval = " + Ps.confidenceLo() + ", " + Ps.confidenceLo() + "\n");
		StdOut.println("end simulation......\n");
		StdOut.println("the total time is " + timer.elapsedTime() + "milliseconds" + "\n");
		
	}
}