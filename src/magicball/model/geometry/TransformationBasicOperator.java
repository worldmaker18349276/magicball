package magicball.model.geometry;

import magicball.model.*;


public interface TransformationBasicOperator extends Engine<Transformation>
{
	// operator
	public Transformation compose( Transformation... trans );
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Number divisor );
	public Transformation invert( Transformation trans );
	public Transformation transformsBy( Transformation t, Transformation p ); // = p * t * p^-1
}
