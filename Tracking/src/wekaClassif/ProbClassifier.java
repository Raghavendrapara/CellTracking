package wekaClassif;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.analysis.function.Gaussian;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.SimpleLogistic;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class ProbClassifier {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	
			DataSource source = new DataSource("/home/raghavendra/Desktop/diabete.arff");
			 Instances instances = source.getDataSet();
			 instances.setClassIndex(instances.numAttributes()-1);
			 DataSource	source1 = new DataSource("/home/raghavendra/Desktop/diabe.arff");
				 Instances instancestest = source1.getDataSet();
				 //instancestest.setClassIndex(1);
			instancestest.setClassIndex(instances.numAttributes() - 1);
			 System.out.println("\nDataset:\n");
			 System.out.println(instances);
			 Classifier classifier = new weka.classifiers.functions.Logistic();
			 classifier.buildClassifier(instances);
			 System.out.println(classifier.toString());
			 Evaluation eval = new Evaluation(instances);
		eval.evaluateModel(classifier,instancestest);
				/** Print the algorithm summary */
				System.out.println("** Linear Regression Evaluation with Datasets **");
				System.out.println(eval.toSummaryString());
				System.out.print(" the expression for the input data as per alogorithm is ");
				System.out.println(classifier);
		//double[][] x=ob.coefficients();
				DataSource xx=new DataSource("/home/raghavendra/Desktop/diabetes.arff");
				Instances xy=xx.getDataSet();
				xy.setClassIndex(instances.numAttributes() - 1);
				Instance predicationDataSet = xy.lastInstance();
				double value = classifier.classifyInstance(predicationDataSet);
				/** Prediction Output */
				System.out.println(value);
	
		}

		}


	
