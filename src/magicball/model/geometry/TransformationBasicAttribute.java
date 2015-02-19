package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface TransformationBasicAttribute
{
	public static AttributeKey<TransformationBasicAttribute> KEY = AttributeKey.<TransformationBasicAttribute>valueOf("TransformationBasicAttribute");
	// attribute
	public Number[] applies( Transformation trans, Number[] point );

	public Function<Number[],Number[]> getTransformationFunction( Transformation trans );
}
