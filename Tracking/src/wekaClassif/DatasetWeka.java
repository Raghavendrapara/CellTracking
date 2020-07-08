package wekaClassif;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	gb.getValues(0);
	ArrayList<Attribute> attributes = new ArrayList<Attribute>();
	ArrayList<Features> feat=gb.FeatureSet;
	for(String ky:feat.get(0).featureNames.values())
        attributes.add(new Attribute(ky));
List<String> l=new ArrayList<>();
l.add("Mig");l.add("NotMig");
	attributes.add(new Attribute("class",l));
	//System.out.println(attributes.toString());
	WekaTrain=new Instances("Tracking",attributes,1);
	WekaTrain.setClassIndex(WekaTrain.numAttributes()-1);
	//System.out.println(feat.size());
	for(int i=0;i<feat.size();i++)
	{
		HashMap<Integer,Double> hh=feat.get(i).featureValues;
		Collection arr=hh.values();
		String ar=arr.toString();
		////System.out.println(arr);
		String str[]=ar.split(" ");
	//for(int k=0;k<str.length;k++)
	//System.out.print(str[k]+" ");

WekaTrain.add(new DenseInstance(1.0,convert(str)));
	


	}
System.out.println(WekaTrain.toString());
		
	}
	static double[] convert(String []ar)
	{
		double arr[]=new double[ar.length];
		for(int k=0;k<ar.length;k++)
		{
		
			String regex="([0-9]+[.][0-9]+)";
			String input= ar[k];

			Pattern pattern=Pattern.compile(regex);
			Matcher matcher=pattern.matcher(input);
			while(matcher.find()) {
			arr[k]=Double.valueOf(matcher.group());
		}}
		return arr;
	}
}
		
