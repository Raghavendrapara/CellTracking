package imag;

import java.awt.Color;

import javax.swing.JFrame;

import smile.plot.Dendrogram;
import smile.plot.PlotCanvas;

public class LineageDendrogram {

	static PlotCanvas pc;
	@SuppressWarnings("static-access")
	public static void main(String args[])
	{
		int merge[][]= {{1,2},{2,2},{2,4},{3,2},{3,1}};
		double height[]= {1.2,1.5,2.99,3.5,11.9};
	
		
		Dendrogram dd=new Dendrogram(merge, height, Color.PINK);
		System.out.println(dd.getHeight());
		pc=dd.plot("frf", merge, height);
		pc.setVisible(true);
		
		JFrame display=new JFrame();
		display.setBounds(0, 0, 1000, 1000);
		display.add(pc);
		display.setVisible(true);
		
		//   PlotCanvas pp=Dendrogram.plot(merge,height);
	  //  pp.print();
	}
	
}
