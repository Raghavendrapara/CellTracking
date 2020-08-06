package imag;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ij.CompositeImage;
import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.LookUpTable;
import ij.Prefs;
import ij.gui.Roi;
import ij.measure.ResultsTable;
import ij.plugin.filter.Analyzer;
import ij.plugin.filter.ParticleAnalyzer;
import ij.plugin.frame.RoiManager;
import ij.process.ByteProcessor;

import ij.process.ImageProcessor;

//Implement Using ROI getStatistics-ImageStatistics

public class GroundTruthExtractor {

	//static Roi roi[][];
	public static ArrayList<Features> featureSet=new ArrayList<>();
	static int trainCnt;
	
	public GroundTruthExtractor() {
		// TODO Auto-generated constructor stub
	}
	
	public GroundTruthExtractor(int trainCount)
	{
		trainCnt=trainCount;
	}

	public void getValues(String path) throws IOException {
	
		// TODO Auto-generated method stub
	    //Change into formal form
		String inputPath=path; 
		
		GroundTruthExtractor extracter= new GroundTruthExtractor();
		List<String> images=extracter.loadImages(inputPath);
		
		//System.out.println(images.size());
	     RoiManager roiManager= new RoiManager();
		//roi=new Roi[images.size()][10];
		for(int frameNum=0;frameNum<trainCnt;frameNum++) {
		ImagePlus currentImage= IJ.openImage(inputPath+images.get(frameNum));	
		 extracter.runextracter(currentImage, roiManager, 0,255,frameNum);
		}
		// break;
		 
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

	public  List<String> loadImages(String directory){
		List<String> imageList= new ArrayList<String>();
		File folder = new File(directory);
		File[] images = sortImages(folder.listFiles());
		for (File file : images) {
			if (file.isFile()) {
				imageList.add(file.getName());
			}
		}
		return imageList;
	}
	
	public void runextracter(ImagePlus currentImage, RoiManager roiManager, int minThreshold, int maxThreshold,int frameNum) throws IOException{
        ResultsTable tempResults=new ResultsTable();
		for(int threshold=minThreshold; threshold<=maxThreshold; threshold++) {
			ImagePlus copyImage= currentImage.duplicate();
			
			ImageStack inputStack=copyImage.getStack();
			//System.out.println(inputStack.getSize());
			for(int j=0; j<inputStack.getSize();j++) {
				ImageProcessor ip=inputStack.getProcessor(j+1);
				
				ImagePlus temp=new ImagePlus("1.0",ip);
				ip.setThreshold(threshold, threshold, ImageProcessor.RED_LUT);
				applyShortOrFloatThreshold(temp);
				ip.invert();
			//	temp.show();
				ParticleAnalyzer.setRoiManager(roiManager);
				ParticleAnalyzer analyzer= new ParticleAnalyzer(ParticleAnalyzer.ADD_TO_MANAGER,Analyzer.getMeasurements(),tempResults,1.0, Double.POSITIVE_INFINITY);
				analyzer.analyze(temp);
				Analyzer.setMeasurements(65536-1);
				tempResults=Analyzer.getResultsTable();
				}
			
		}
		
	
     	String arr[]=tempResults.getHeadings();
  //   System.out.println(Arrays.toString(arr));
		int attributesLength=arr.length;
	//	System.out.println(n+"  "+xx.size());
	    double val[]=new double[attributesLength];
		
		for(int k=0;k<tempResults.size();k++)
		{
			String tempRow=tempResults.getRowAsString(k);
			tempRow=tempRow.replace("	"," ");
			tempRow=tempRow.substring(tempRow.indexOf(" "));
			tempRow.trim();
			
			//System.out.println(ss);
			StringTokenizer valuesString=new StringTokenizer(tempRow);
		
			for(int k1=0;k1<attributesLength;k1++)
				if(k1==0)
					{val[k1]=frameNum;String discardToken=valuesString.nextToken();}
				else val[k1]=Double.valueOf(valuesString.nextToken());
				
	//		System.out.println(val.length);
			Features tempFeature=new Features(arr,val);
		//	System.out.println(xy.getFeatureName().get(1));
			featureSet.add(tempFeature);
		}
		
		//xx.show("Table");
	//	for(int k1=0;k1<n;k1++)
		//	System.out.print(arr[k1]+"    ");
		//System.out.println();
		//for(int k1=0;k1<xx.size();k1++) {
	    	//for(int k=0;k<val[0].length;k++)
			//System.out.print(val[k1][k]+" ");
			//System.out.println();}
		
		
		tempResults.reset();

	/*	Roi r[]=roiManager.getRoisAsArray();
		roiManager.reset();
		for(int k=0;k<r.length;k++)
			roi[i][k]=r[k];
			*/
		
	}
	
	/* ImageJ thresholding  for float and Short Images*/
	public void applyShortOrFloatThreshold(ImagePlus imp) {
		if (!imp.lock()) return;
		int width = imp.getWidth();
		int height = imp.getHeight();
		int size = width*height;
		int nSlices = imp.getStackSize();
		ImageStack stack1 = imp.getStack();
		ImageStack stack2 = new ImageStack(width, height);
		ImageProcessor ip = imp.getProcessor();
		float t1 = (float)ip.getMinThreshold();
		float t2 = (float)ip.getMaxThreshold();
		if (t1==ImageProcessor.NO_THRESHOLD) {
			double min = ip.getMin();
			double max = ip.getMax();
			ip = ip.convertToByte(true);
			ip.setAutoThreshold(ImageProcessor.ISODATA2, ImageProcessor.NO_LUT_UPDATE);
			double minThreshold = ip.getMinThreshold();
			double maxThreshold = ip.getMaxThreshold();
			t1 = (float)(min + (max-min)*(minThreshold/255.0));
			t2 = (float)(min + (max-min)*(maxThreshold/255.0));
		}
		float value;
		ImageProcessor ip1, ip2;
		IJ.showStatus("Converting to mask");
		for (int i=1; i<=nSlices; i++) {
			IJ.showProgress(i, nSlices);
			String label = stack1.getSliceLabel(i);
			ip1 = stack1.getProcessor(i);
			ip2 = new ByteProcessor(width, height);
			for (int j=0; j<size; j++) {
				value = ip1.getf(j);
				if (value>=t1 && value<=t2)
					ip2.set(j, 255);
				else
					ip2.set(j, 0);
			}
			stack2.addSlice(label, ip2);
		}
		imp.setStack(null, stack2);
		ImageStack stack = imp.getStack();
		stack.setColorModel(LookUpTable.createGrayscaleColorModel(!Prefs.blackBackground));
		imp.setStack(null, stack);
		if (imp.isComposite()) {
			CompositeImage ci = (CompositeImage)imp;
			ci.setMode(IJ.GRAYSCALE);
			ci.resetDisplayRanges();
			ci.updateAndDraw();
		}
		imp.getProcessor().setThreshold(255, 255, ImageProcessor.NO_LUT_UPDATE);
		if (IJ.debugMode) IJ.log("Thresholder16: 255-255 ("+(Prefs.blackBackground?"black":"white")+" background)");
		IJ.showStatus("");
		imp.unlock();
	}

	

}