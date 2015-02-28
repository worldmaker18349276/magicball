package magicball.model.geometry;

import magicball.model.*;


public interface TransformationBasicPredicate extends Engine<Transformation>
{
	// predicate
	public boolean isIdentity( Transformation trans );
	public boolean equals( Transformation trans1, Transformation trans2 );
}
