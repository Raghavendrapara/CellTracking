package imag;

import java.io.File;
import java.util.*;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.Wand;
import java.util.regex.*;
import ij.process.ImageProcessor;




//A Sample use Case for Wand Tool

public class RoiExtractWand {
	private static List<String> images=new ArrayList<>();
	public static void main(String[] args) {
		//find no. of entries in the directory 
         int stackSize=loadImages("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG"); 
         
        //displaying the entries 
         
          //  System.out.println(arr[i]); }
		//System.out.println(x);
		
		new ImageJ();
		for (int frame = 0; frame < stackSize ; frame++) {
		IJ.log("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/"+images.get(frame));
		//IJ.run("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		ImagePlus temp=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/"+images.get(frame));
	    ImageProcessor ip=temp.getProcessor();
		//int width=ip.getWidth();
		//int height=ip.getHeight();
		//fi.show();
		//System.out.println(width);
		Wand wand=new Wand(ip);
		HashSet<ArrayList<Integer>> xPoints=new HashSet<>();
		HashSet<ArrayList<Integer>> yPoints=new HashSet<>();
		HashSet<String> hashXYpoints=new HashSet<>();
		
		//HashSet<Integer>
		//To add technique for filling using subtract or fill Polygon
		for(int i1=0;i1<696;i1++) //Iterate over X axis
			for(int j=0;j<520;j++) { //Iterate over Y-Axis
		        if(ip.getValue(i1, j)>0) {
				wand.autoOutline(i1,j);
		        int f=0;
		      for(int i=0;i<wand.xpoints.length && (wand.ypoints[i]>0 && wand.xpoints[i]>0);i++)
		      { 
		      String tupleXY=wand.ypoints[i]+""+wand.xpoints[i];
		      if(hashXYpoints.contains(tupleXY))
		      { f=1;break;}
		    	  else {hashXYpoints.add(tupleXY);}
		      if(f!=1)
				     if((wand.xpoints)!=null) {
				      xPoints.add(convert(wand.xpoints));
				      yPoints.add(convert(wand.ypoints));}}
		        }
		    
		      }
		System.out.println(xPoints.size());
		System.out.println(yPoints.size());
		}
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
	private static int loadImages(String directory)
	{
		images.clear();
		File folder = new File(directory);
		File[] image = sortImages(folder.listFiles());

		for (File file : image) {
			if (file.isFile()) {
				images.add(file.getName());
				//System.out.println(file.getName());
			}
		}
		//ImageStack on=loadFeatureStack(images[0].getName());
		return images.size();
	}
	private static File[] sortImages(File[] images) {
		final Pattern p = Pattern.compile("\\d+");
		Arrays.sort(images, new  Comparator<File>(){
			@Override public int compare(File o1, File o2) {
				Matcher m = p.matcher(o1.getName());
				Integer number1 = null;
				if (!m.find()) {
					return o1.getName().compareTo(o2.getName());
				}
				else {
					Integer number2 = null;
					number1 = Integer.parseInt(m.group());
					m = p.matcher(o2.getName());
					if (!m.find()) {
						return o1.getName().compareTo(o2.getName());
					}
					else {
						number2 = Integer.parseInt(m.group());
						int comparison = number1.compareTo(number2);
						if (comparison != 0) {
							return comparison;
						}
						else {
							return o1.getName().compareTo(o2.getName());
						}
					}
				}
			}}
				);
		return images;
	}
	

}



