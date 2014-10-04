package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationEngine
{
	public TransformationEngine clone();


	// creater
	public Transformation createTransformationByVectors( Number[] rvec, Number[] sh );
	public Transformation createRotationByVector( Number[] rvec );
	public Transformation createShiftByVector( Number[] sh );
	public Transformation createIdentityTransformation();

	public Reflection createReflectionByPlane( Surface plane );


	// attribute
	public Number[][] getRotationMatrix( Transformation trans );
	public Number[] getShiftVector( Transformation trans );
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans );
	public Function<Number[],Number[]> getReflectionFunction( Reflection ref );


	// operator
	public Transformation compose( Transformation... trans );
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Number divisor );
	public Transformation invert( Transformation trans );

	public boolean isIdentity( Transformation trans );
	public boolean isRotation( Transformation trans );
	public boolean isShift( Transformation trans );
	public boolean equals( Transformation trans1, Transformation trans2 );
}
