package scores;

public class Count {
//From Paper and Theses
double pcnt1;//Probability for cell count 1 in a cluster
double pcnt2;//Probability for cell count 2 in a cluster
double pcnt3;//Probability for cell count 3 or more in a cluster
double alpha;//Parameter for pcnt3 determination using Geometric Progression
int k;//Length to assume for GP
Count()//From Classifier or GT	
{
//pcnt1=classifier.get(CountProb1);	
//pcnt2=classifier.get(CountProb2);	
//pcnt3=classifier.get(CountProb3);	



}

double Extrapolate()
{
	return alpha*(Math.pow(1-alpha,length-k));
	
}

}
