package modelling;

public class Arc {
	
	Node mStart,mEnd;
	
	Arc(Node aStart,Node aEnd)
	{
		mStart=aStart;
		mEnd=aEnd;
	}
	
	Node GetStart()
	{
		return mStart;
	}
	
	Node GetEnd()
	{
		return mEnd;
	}
	double score()
	{
	return 1.0;	
	}
	

}
