package magicball.model.geometry;

import magicball.model.math.Function;


public interface AffineTransformationAttribute
{
	// attribute
	public Number[][] getTransformationMatrix( Transformation trans );
	public Number[] getRotationVector( Transformation trans );
	public Number[] getReflectionVector( Transformation trans );
	public Number[] getTranslationVector( Transformation trans );
	public Number getScalingFactor( Transformation trans );
}
