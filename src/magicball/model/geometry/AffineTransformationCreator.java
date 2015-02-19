package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface AffineTransformationCreator
{
	public static AttributeKey<AffineTransformationCreator> KEY = AttributeKey.<AffineTransformationCreator>valueOf("AffineTransformationCreator");
	// creater
	public Transformation createAffineTransformationByAugmentedMatrix( Number[][] mat );
	public Transformation createAffineTransformationByMatrixAndVector( Number[][] mat, Number[] vec );
	public Transformation createLinearTransformationByMatrix( Number[][] mat );
	public Transformation createRotationByVector( Number[] rvec );
	public Transformation createReflectionByVector( Number[] fvec );
	public Transformation createTranslationByVector( Number[] sh );
	public Transformation createScalingByFactor( Number factor );
	public Transformation createShearingByOffsets( Number a, Number b ); // x'=x+az, y'=y+bz
}
