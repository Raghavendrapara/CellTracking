package imag;

import java.util.HashMap;

public class Features {

	public HashMap<Integer,String> featureNames=new HashMap<>();
	public HashMap<Integer,Double> featureValues=new HashMap<>();
	
Features(String fnames[],double fvalues[])
{
	int n=fnames.length;

    for(int i=0;i<n;i++)
    {
	    
    	featureNames.put(i+1,fnames[i]);
    	featureValues.put(i+1,fvalues[i]);
    	
    }


}
HashMap<Integer,String> getFeatureName()
{
	return featureNames;
}

HashMap<Integer,Double> getFeatureValue()
{
	return featureValues;
}
}
