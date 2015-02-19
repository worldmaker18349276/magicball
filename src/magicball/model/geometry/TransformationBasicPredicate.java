package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface TransformationBasicPredicate
{
	public static AttributeKey<TransformationBasicPredicate> KEY = AttributeKey.<TransformationBasicPredicate>valueOf("TransformationBasicPredicate");
	// predicate
	public boolean isIdentity( Transformation trans );
	public boolean equals( Transformation trans1, Transformation trans2 );
}
