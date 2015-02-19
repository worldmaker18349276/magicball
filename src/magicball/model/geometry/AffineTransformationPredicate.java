package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface AffineTransformationPredicate
{
	public static AttributeKey<AffineTransformationPredicate> KEY = AttributeKey.<AffineTransformationPredicate>valueOf("AffineTransformationPredicate");
	// predicate
	public boolean isAffine( Transformation trans ); // f(x) = Mx+b
	public boolean isLinear( Transformation trans ); // f(x) = Mx
	public boolean isSimilar( Transformation trans ); // f(x) = Rfx+b
	public boolean isIsometric( Transformation trans ); // f(x) = Rx+b
	public boolean isRigid( Transformation trans ); // f(x) = Rx+b, |R|==1
	public boolean isTranslation( Transformation trans ); // f(x) = x+b
}
