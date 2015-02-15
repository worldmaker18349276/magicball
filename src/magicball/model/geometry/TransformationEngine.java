package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationEngine
{
	public TransformationEngine clone();


	// creater
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func );
	public Transformation createIdentityTransformation();
	public Transformation createLinearTransformationByMatrix( Number[][] mat );
	public Transformation createRotationByVector( Number[] rvec );
	public Transformation createReflectionByVector( Number[] fvec );
	public Transformation createTranslationByVector( Number[] sh );
	public Transformation createScalingByFactor( Number factor );
	// shear...


	// attribute
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans );
	public Number[][] getTransformationMatrix( Transformation trans );
	public Number[] getRotationVector( Transformation trans );
	public Number[] getReflectionVector( Transformation trans );
	public Number[] getTranslationVector( Transformation trans );
	public Number getScalingFactor( Transformation trans );
	
	public Number[] applies( Transformation trans, Number[] point );


	// operator
	public Transformation compose( Transformation... trans );
	public Transformation pow( Transformation trans, int exp );
	public Transformation dividedBy( Transformation trans, Number divisor );
	public Transformation invert( Transformation trans );
	public Transformation transformsBy( Transformation t, Transformation p ); // = p * t * p^-1

	public boolean isAffine( Transformation trans ); // f(x) = Mx+b
	public boolean isLinear( Transformation trans ); // f(x) = Mx
	public boolean isSimilar( Transformation trans ); // f(x) = Rfx+b
	public boolean isIsometric( Transformation trans ); // f(x) = Rx+b
	public boolean isRigid( Transformation trans ); // f(x) = Rx+b, |R|==1

	public boolean isIdentity( Transformation trans ); // f(x) = x
	public boolean isRotation( Transformation trans ); // f(x) = Rx, |R|==1
	public boolean isReflection( Transformation trans ); // f(x) = Px
	public boolean isTranslation( Transformation trans ); // f(x) = x+b
	public boolean isScaling( Transformation trans ); // f(x) = fx

	public boolean equals( Transformation trans1, Transformation trans2 );
}
