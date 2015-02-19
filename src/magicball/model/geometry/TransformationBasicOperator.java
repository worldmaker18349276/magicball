package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface TransformationBasicOperator
{
	public static AttributeKey<TransformationBasicOperator> KEY = AttributeKey.<TransformationBasicOperator>valueOf("TransformationBasicOperator");
	// operator
	public Transformation compose( Transformation... trans );
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Number divisor );
	public Transformation invert( Transformation trans );
	public Transformation transformsBy( Transformation t, Transformation p ); // = p * t * p^-1
}
