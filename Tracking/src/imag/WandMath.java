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
public class WandMath {
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
		HashSet<ArrayList<Integer>> x=new HashSet<>();
		HashSet<ArrayList<Integer>> y=new HashSet<>();
		HashSet<String> s=new HashSet<>();
		HashSet<String> sy=new HashSet<>();
		//HashSet<Integer>
		//To add technique for filling using subtract or fill Polygon
		for(int i1=0;i1<696;i1++)
			for(int j=0;j<520;j++) {
		      wa.autoOutline(i1,j);
		        int f=0;
		      for(int i=0;i<wa.xpoints.length && (wa.ypoints[i]>0 && wa.xpoints[i]>0);i++)
		      { 
		      String ss=wa.ypoints[i]+""+wa.xpoints[i];
		      if(s.contains(ss))
		      { f=1;break;}
		    	  else {s.add(ss);}
		      if(f!=1)
				     if((wa.xpoints)!=null) {
				      x.add(convert(wa.xpoints));
				      y.add(convert(wa.ypoints));}}
		      
		    
		      }
		System.out.println(x.size());
		System.out.println(y.size());
		
		//System.out.println(IJ.doWand(275, 164,1,"4-connected"));
		
			}
	static ArrayList<Integer> convert(int arr[])
	{
		ArrayList<Integer> x=new ArrayList<>();
		for(int i:arr)
			x.add(i);
		return x;
	}
	static boolean isEmpty(int arr[])
	{
		for(int i:arr)
			if(i>0)
				return false;
	
			return true;
	}
}



