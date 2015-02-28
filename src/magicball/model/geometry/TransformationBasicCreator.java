package magicball.model.geometry;

import magicball.model.math.Function;


public interface TransformationBasicCreator
{
	// creater
	public Transformation createIdentityTransformation();
	
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func );
}
