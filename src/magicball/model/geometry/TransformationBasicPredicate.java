package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationBasicPredicate
{
	// predicate
	public boolean isIdentity( Transformation trans );
	public boolean equals( Transformation trans1, Transformation trans2 );
}
