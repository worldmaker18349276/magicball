package magicball.model.geometry;

import magicball.model.math.Function;


public interface AffineTransformationCreator
{
	// creater
	public Transformation createLinearTransformationByMatrix( Number[][] mat );
	public Transformation createRotationByVector( Number[] rvec );
	public Transformation createReflectionByVector( Number[] fvec );
	public Transformation createTranslationByVector( Number[] sh );
	public Transformation createScalingByFactor( Number factor );
	// shear...
}