package imag;

import java.util.HashMap;

public class Features {

	public HashMap<Integer,String> FeatureNames=new HashMap<>();
	public HashMap<Integer,Double> FeatureValues=new HashMap<>();
	
Features(String a2[],double a3[])
{
	int n=a2.length;

    for(int i=0;i<n;i++)
    {
	    
    	FeatureNames.put(i+1,a2[i]);
    	FeatureValues.put(i+1,a3[i]);
    	
    }


}
HashMap<Integer,String> getFeatureName()
{
	return FeatureNames;
}

HashMap<Integer,Double> getFeatureValue()
{
	return FeatureValues;
}
}
