package cn.sjtunic.algorithm;
import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {
	
	private boolean[][] P; //
	private int start, end;
	private QuickFindUF uf;
	private int size;
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		uf = new QuickFindUF(n*n +2);
		size = n;
		// only need to recognize the union between start and end
		start = n*n;
		end = n*n +1;
		P = new boolean[n][n];
		for (int i=0; i<n; i++)
			for (int j=0; j<n; j++)
				P[i][j] = false;
	}
	public void open(int row, int col) {
		// open site (row, col) if it is not open already, false represents not open, true represents open
		P[row-1][col-1] = true;
		if(row==1)
			uf.union((row-1)*size+col-1, start);
		if(row!=1 && isOpen(row-1, col))
			uf.union((row-1)*size+col-1, (row-2)*size+col-1);
		if(row==size)
			uf.union((row-1)*size+col-1, end);
		if(row!=size && isOpen(row+1, col))
			uf.union((row-1)*size+col-1, row*size+col-1);
		if(col!=1 && isOpen(row, col-1))
			uf.union((row-1)*size+col-1, (row-1)*size+col-2);
		if(col!=size && isOpen(row, col+1))
			uf.union((row-1)*size+col-1, (row-1)*size+col);		
	}
	public boolean isOpen(int row, int col) {
		// is site (row, col) open?
		return P[row-1][col-1];
	}
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		if(isOpen(row, col)) {
			return uf.connected((row-1)*size+col-1, start);
		} else 
			return false;
	}
	public boolean percolates()	{
		// does the system percolates?
		return uf.connected(start, end);
	}

	public static void main(String[] args) {
		// test client (optional)
		Percolation p = new Percolation(4);
		p.open(1, 1);
		p.open(2, 2);
		p.open(3, 1);
		p.open(4, 1);
		
		StdOut.println(p.percolates());
	}
}