package imag;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.util.*;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.Line;
import ij.gui.Overlay;
import ij.gui.Roi;
import ij.gui.Wand;
import ij.plugin.frame.RoiManager;

import java.util.regex.*;


import ij.process.ImageProcessor;




//A Sample use Case for Wand Tool

public class RoiExtractWand {
	private static List<String> images=new ArrayList<>();
	public static void main(String[] args) {
		//find no. of entries in the directory 
         int stackSize=loadImages("/home/raghavendra/Downloads/PhC-C2DH-U373/01_GT/SEG"); 
         
        //displaying the entries 
         
          //  System.out.println(arr[i]); }
		//System.out.println(x);

         ImageStack display=new ImageStack();
 		ImagePlus dispTrack=new ImagePlus();
 		RoiManager roiManager=new RoiManager();
 		ArrayList<Roi> tempRois=new ArrayList<>();
 /*		  Graphics2D g = bgImage.createGraphics();
 	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
 	                RenderingHints.VALUE_ANTIALIAS_ON);

 	        float alpha = 0.33f;
 	        g.setComposite(AlphaComposite.SrcOver);
 	        g.drawImage(bgImage, 0, 0, null);
 	        g.setComposite(AlphaComposite.SrcOver.derive(alpha));
 	        g.drawImage(fgImage, 0, 0, null);

 	        g.dispose();
 */
 		ArrayList<Color> colors=new ArrayList<>();
 		colors.add(Color.BLUE);
 		colors.add(Color.CYAN);
 		colors.add(Color.DARK_GRAY);
 		colors.add(Color.LIGHT_GRAY);
 		colors.add(Color.MAGENTA);
 		colors.add(Color.PINK);
 		colors.add(Color.BLACK);
 		colors.add(Color.ORANGE);
 		colors.add(Color.RED);
		new ImageJ();
		ArrayList<ArrayList<Roi>> arr=new ArrayList<>();
		Overlay overlay=new Overlay();
		for (int frame = 0; frame < stackSize ; frame++) {
		IJ.log("/home/raghavendra/Downloads/PhC-C2DH-U373/01_GT/SEG/"+images.get(frame));
	
		ImagePlus temp=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_GT/SEG/"+images.get(frame));
	    tempRois=GroundTruthExtractor.runextracter(temp, roiManager, 1, 255, frame);
	    ImageProcessor ip=temp.getProcessor();
	    ImagePlus ip1 = IJ.createImage("label", "8-bit white", 712, 712, 1);
	    Overlay over=new Overlay();
	    int key=0;
	    arr.add(tempRois);
	    
	    for(Roi roi:tempRois)
		{
	
		    ip1.getProcessor().setRoi(roi);
	/*	 Line  line1 = new Line(150,100,150,400);
		    line1.setStrokeWidth(5);
		    line1.setStrokeColor(Color.red); 
		   // roi.drawOverlay(g);
		    line1.setPosition(5);
		    over.add(line1);
		    over.add(roi);
		*/    over.add(roi);
		    over.setFillColor(colors.get(key));
		   
		    overlay.add(roi);
		    ip1.getProcessor().draw(roi);
		  //  roi.setFillColor(Color.GRAY);
		  //  roi.setColor(Color.BLACK);
		//    roi.setStrokeColor(Color.CYAN);
		    ip1.show();
		    ip1.getCanvas().setOverlay(over);
		    ip1.setOverlay(over);
		    ip1.getProcessor().setOverlay(over);
		
		}
       
	    display.addSlice(ip1.getProcessor());
		 
		  
		
		}    
		dispTrack=new ImagePlus("cdd",display);
		
	/*	int index=0;
		for(ArrayList<Roi> temp:arr)
		{
			
			Overlay overl=new Overlay();
			for(Roi r:temp)
			{overl.add(r);
			dispTrack.setOverlay(r, colors.get(index), 5, Color.CYAN);
			}
			index++;
			//dispTrack.setOverlay(overl);
		}
		
		*/
		
		dispTrack.show();
	//	dispTrack.getCanvas().setOverlay(overlay);
		//int width=ip.getWidth();
		//int height=ip.getHeight();
		//fi.show();
		//System.out.println(width);
/*		Wand wand=new Wand(ip);
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
	*/	
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


			
//	IJ.log("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/"+images.get(frame));
	//IJ.run("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
	//ImagePlus temp=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/"+images.get(frame));
//    tempRois=GroundTruthExtractor.runextracter(temp, roiManager, 1, 255,1);
	
	


