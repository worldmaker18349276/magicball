package magicball.model.geometry;

import magicball.model.math.Num;
import magicball.model.*;


public interface TransformationAdvancedOperator extends Engine<Transformation>
{
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Num divisor );
	public Transformation transformsBy( Transformation t, Transformation p ); // = p * t * p^-1
}
