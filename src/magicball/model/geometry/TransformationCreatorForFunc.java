package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationCreatorForFunc
{
	// creater
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func );
}
