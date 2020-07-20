package imag;

import javax.swing.JFrame;

import smile.plot.Dendrogram;
import smile.plot.PlotCanvas;

public class LineageDendrogram {

	static PlotCanvas pc;
	
	public static void main(String args[])
	{
/*      merge - an n-1 by 2 matrix of which row i describes the merging of clusters at step i of the clustering. 
 *      If an element j in the row is less than n, then observation j was merged at this stage. 
 *      If j ≥ n then the merge was with the cluster formed at the (earlier) stage j-n of the algorithm.
        
 *      height - a set of n-1 non-decreasing real values, which are the clustering height, i.e.
 *      the value of the criterion associated with the clustering method for the particular agglomeration.
*/

		int merge[][]= {{1,2},{2,3},{5,4},{5,6},{8,9},{10,11},{12,13}};
		double height[]= {0.1,0.2,0.99,1.5,1.9,1.99,5.6};
	
		
		//Dendrogram dd=new Dendrogram(merge, height, Color.RED);
		//System.out.println(dd.getHeight());
		pc=Dendrogram.plot( merge, height);
		pc.setVisible(true);
		
		JFrame display=new JFrame();
		display.setBounds(0, 0, 400, 700);
		display.add(pc);
		display.setVisible(true);
		
		//   PlotCanvas pp=Dendrogram.plot(merge,height);
	  //  pp.print();
	}
	
}
