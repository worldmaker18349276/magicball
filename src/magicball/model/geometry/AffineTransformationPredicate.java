package magicball.model.geometry;

import magicball.model.math.Function;


public interface AffineTransformationPredicate
{
	// predicate
	public boolean isAffine( Transformation trans ); // f(x) = Mx+b
	public boolean isLinear( Transformation trans ); // f(x) = Mx
	public boolean isSimilar( Transformation trans ); // f(x) = Rfx+b
	public boolean isIsometric( Transformation trans ); // f(x) = Rx+b
	public boolean isRigid( Transformation trans ); // f(x) = Rx+b, |R|==1

	public boolean isRotation( Transformation trans ); // f(x) = Rx, |R|==1
	public boolean isReflection( Transformation trans ); // f(x) = Px
	public boolean isTranslation( Transformation trans ); // f(x) = x+b
	public boolean isScaling( Transformation trans ); // f(x) = fx
}
