package imag;
import org.apache.commons.math3.analysis.function.Gaussian;
public class gauss {
public static void main(String args[])
{
	Gaussian v=new Gaussian(1,1);
	double x=v.value(3.3);
	System.out.println(x);
}
}
