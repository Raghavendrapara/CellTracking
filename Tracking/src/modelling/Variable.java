package modelling;

import java.util.ArrayList;
import java.util.Collections;

public class Variable {

	int mValue;				// Value of the Variable. Usually the number of times that an event occurs.
	int mNumScores;			// The number of predefined scores.
	ArrayList<Double> mScore;
	Variable(int aValue, int aNumScores, ArrayList<Double> aScores)
	{
		mScore=new ArrayList<>(aScores.size());
		mValue=aValue;
		mNumScores=aNumScores;
		Collections.copy(mScore,aScores);
		
		
	}
	double GetPlusScore()
	{
		if (mValue < mNumScores-1) {
			return mScore.get(mValue+1) - mScore.get(mValue);
		} else {
			// 120406 - Changed from 0.0 to -10.0, to get rid of duplicated tracks.
			return Math.min(mScore.get(mNumScores-1) - mScore.get(mNumScores-2), 0.0);
		}
	}

	// Returns the score associated with decreasing the value by 1, or 0.0 if the value is 0.
	double GetMinusScore()
	{
	
		if (mValue < mNumScores) {
		return mScore.get(mValue-1) - mScore.get(mValue);
	    } 
		else {
		
		return Math.max(mScore.get(mNumScores-2) - mScore.get(mNumScores-1), 0.0);
	    }
		
	}

	// Increases the value by 1.
    void plus()
    {
    	mValue++;
    }

	// Decreases the value by 1. The value must not be 0 before the function call.
	void minus()
	{
		if(mValue>0)
			mValue=Math.max(mValue-1,0);
	}

    int getValue()
    {
    	return mValue; 
    }
}