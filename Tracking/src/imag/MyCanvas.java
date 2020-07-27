package imag;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

import ij.IJ;
import ij.ImagePlus;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;

public class MyCanvas extends ImageCanvas  {

	int ii;
	MyCanvas(ImagePlus imp,int i) {
		super(imp);
		this.ii=i;
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g)
	{
		
		ImagePlus next=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		//ImageCanvas icc=next.getCanvas();
        Image i=next.getImage();
		i=i.getScaledInstance(ii, 400, Image.SCALE_SMOOTH);
		g.drawImage(i,000,000,this);
		
	}

}
