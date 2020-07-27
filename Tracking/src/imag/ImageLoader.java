package imag;


import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.util.*;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;
import ij.gui.Roi;
import ij.gui.Wand;
import java.util.regex.*;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ij.gui.TextRoi;
import ij.io.RoiDecoder;
import ij.io.RoiEncoder;
import ij.plugin.filter.RankFilters;
import ij.plugin.frame.RoiManager;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
public class ImageLoader {
	private List<String> images=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
    /*   ImageLoader ob=new ImageLoader();
		int x=ob.loadImages("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG");
		//File a=new File("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG");
		//String arr[]=a.list(); 
		  
        //find no. of entries in the directory 
       // int n=arr.length; 

        //displaying the entries 
        //for (int i = 0; i < n ; i++) { 
          //  System.out.println(arr[i]); }
		System.out.println(x);
	    JFrame frame=new JFrame();
		ImagePlus next=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		ImageProcessor nn=next.getProcessor();
		ImageWindow iw=new ImageWindow(next);
		iw.setSize(400, 400);
		
	  
        nn.scale(0.5, 0.5);
        next=iw.getImagePlus();
        next.resize(400,400,"options");
	   // ImageCanvas x1=new ImageCanvas(next);
	    Image xx=next.getImage();
       ImageIcon jj=new ImageIcon(xx);
       JLabel c=new JLabel(jj);
       JPanel cc=new JPanel();
       cc.setLayout(new BorderLayout());
       /*
       c.setBounds(0, 0, 400, 400);
       cc.add(c,BorderLayout.CENTER);
       cc.setSize(400, 400);
       cc.setBounds(0, 0, 200, 200);
       frame.add(cc);
       frame.setSize(400, 400);
      */ ImagePlus next=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		JFrame frame=new JFrame();
	    MyCanvas mc=new MyCanvas(next,400);
		frame.add(mc);
		frame.setSize(1000,1000);
	//	frame.add(new JLabel(jj));
		frame.setVisible(true);
	
		new ImageJ();
		IJ.log("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
		//IJ.run("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
/*		ImagePlus fi=IJ.openImage("/home/raghavendra/Downloads/PhC-C2DH-U373/01_ST/SEG/man_seg000.tif");
	    ImageProcessor ip=fi.getProcessor();
		int width=ip.getWidth();
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
	*/	
			}
	

private int loadImages(String directory)
{
	this.images.clear();
	File folder = new File(directory);
	File[] images = sortImages(folder.listFiles());

	for (File file : images) {
		if (file.isFile()) {
			this.images.add(file.getName());
			//System.out.println(file.getName());
		}
	}
	//ImageStack on=loadFeatureStack(images[0].getName());
	return this.images.size();
}

private File[] sortImages(File[] images) {
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


/*private ImageStack loadFeatureStack(String imageName){
	String localPath=imageName.substring(0, imageName.lastIndexOf("."));
	//System.out.println(featurePath+images.get(0).substring(0, images.get(0).lastIndexOf(".")));

	
	File[] images=sortImages(new File(localPath).listFiles());
	ImagePlus firstImage=IJ.openImage(localPath+"/"+images[0].getName());
	ImageStack featureStack = new ImageStack(firstImage.getWidth(), firstImage.getHeight());
	for(File file : images){
		if (file.isFile()) {
			//System.out.println(file.getName());
			IJ.log(file.getName());
			ImagePlus image=IJ.openImage(localPath+"/"+file.getName());

			featureStack.addSlice(image.getTitle(), image.getProcessor());

		}
	}
	System.out.println(featureStack);
	return featureStack;
}*/

}
