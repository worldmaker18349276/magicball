package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationAttributeForFunc
{
	// attribute
	public Function<Number[],Number[]> getTransformationFunction( Transformation trans );
}
