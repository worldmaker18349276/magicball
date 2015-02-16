package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationBasicAttribute
{
	// attribute
	public Number[] applies( Transformation trans, Number[] point );
}
