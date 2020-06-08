package imag;

import java.io.File;
import java.util.*;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.Roi;
import ij.gui.Wand;
import java.util.regex.*;
import ij.gui.TextRoi;
import ij.io.RoiDecoder;
import ij.io.RoiEncoder;
import ij.plugin.filter.RankFilters;
import ij.plugin.frame.RoiManager;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
public class WandTo {
	private List<String> images=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       //File a=new File("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG");
		//String arr[]=a.list(); 
		  
        //find no. of entries in the directory 
       // int n=arr.length; 

        //displaying the entries 
        //for (int i = 0; i < n ; i++) { 
          //  System.out.println(arr[i]); }
		//System.out.println(x);
		
		new ImageJ();
		IJ.log("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		//IJ.run("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		ImagePlus fi=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
	    ImageProcessor ip=fi.getProcessor();
		//int width=ip.getWidth();
		//int height=ip.getHeight();
		//fi.show();
		//System.out.println(width);
		Wand wa=new Wand(ip);
		wa.autoOutline(275, 164);
		for(int i=0;i<wa.xpoints.length && (wa.xpoints[i]>0 || wa.ypoints[i]>0);i++)
		{
			System.out.println(wa.xpoints[i]+" "+wa.ypoints[i]);
		}
		
		//System.out.println(IJ.doWand(275, 164,1,"4-connected"));
		
			}
}



