package wekaClassif;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ij.ImageJ;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import imag.Features;
import imag.GroundTruthExtractor;
public class DatasetWeka {

	private static Instances WekaTrain;

	//Attributes WEka
	public static void main(String args[]) throws IOException 
	{
		
	GroundTruthExtractor gb=new  GroundTruthExtractor();
	//int j=getNumFrame();
	new ImageJ();
	String path="/home/raghavendra/Desktop/SEG";
	ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	ArrayList<Features> feat=gb.featureSet;
	for(String ky:feat.get(0).featureNames.values())
        attributes.add(new Attribute(ky));
    
	List<String> classList=new ArrayList<>();
    classList.add("0");classList.add("1");
	attributes.add(new Attribute("class",classList));
	//System.out.println(attributes.toString());
	WekaTrain=new Instances("Tracking",attributes,1);
	WekaTrain.setClassIndex(WekaTrain.numAttributes()-1);
	//System.out.println(feat.size());
	for(int i=0;i<feat.size();i++)
	{
		HashMap<Integer,Double> featureValueSet=feat.get(i).featureValues;
		Collection arr=featureValueSet.values();
		String featuresAsArray=arr.toString();
		////System.out.println(arr);
		String str[]=featuresAsArray.split(" ");
	//for(int k=0;k<str.length;k++)
	//System.out.print(str[k]+" ");

WekaTrain.add(new DenseInstance(1.0,convert(str)));
	


	
	}
System.out.println(WekaTrain.toString());
		
	}
	static double[] convert(String []values)
	{
		double arr[]=new double[values.length];
		for(int k=0;k<values.length;k++)
		{
		
			String regex="([0-9]+[.][0-9]+)";
			String input= values[k];

			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(input);
			while(matcher.find()) {
			arr[k]=Double.valueOf(matcher.group());
		}}
		return arr;
	}
}
		
