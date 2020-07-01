package imag;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.analysis.function.Gaussian;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class gauss {
public static void main(String args[]) throws Exception
{
	DataSource source = new DataSource("/home/raghavendra/Desktop/diabetes.arff");
	 Instances instances = source.getDataSet();

	 instances.setClassIndex(instances.numAttributes() - 1);
	 System.out.println("\nDataset:\n");
	 System.out.println(instances);
SimpleLogistic ob=new SimpleLogistic();
ob.buildClassifier(instances);
//double[][] x=ob.coefficients();
System.out.print(ob.toString());
}

}
