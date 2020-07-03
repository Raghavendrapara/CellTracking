package wekaClassif;

import weka.classifiers.Classifier;

public class Classify {
	
private Classifier Type;	
Classify(Classifier ob)
{
	Type=ob;
}
Classifier getClassif()	
{
	return Type;
}

}
