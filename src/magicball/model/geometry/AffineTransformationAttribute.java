package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface AffineTransformationAttribute
{
	public static AttributeKey<AffineTransformationAttribute> KEY = AttributeKey.<AffineTransformationAttribute>valueOf("AffineTransformationAttribute");
	// attribute
	public Number[][] getTransformationMatrix( Transformation trans );
	public Number[] getRotationVector( Transformation trans );
	public Number[] getReflectionVector( Transformation trans );
	public Number[] getTranslationVector( Transformation trans );
}
