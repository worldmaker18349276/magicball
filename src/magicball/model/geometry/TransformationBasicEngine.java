package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationBasicEngine
{
	public TransformationBasicEngine clone();
	public Transformation compose( Transformation[] trans );
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Number divisor );
	public Transformation invert( Transformation trans );
	public Transformation createIdentityTransformation();
	public Function<Number[],Number[]> createTransformationFunction( Transformation trans );
	public boolean isIdentity( Transformation trans );
	public boolean isRotation( Transformation trans );
	public boolean isShift( Transformation trans );
	public boolean equals( Transformation trans1, Transformation trans2 );
}
