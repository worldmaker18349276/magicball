package magicball.model.geometry;

import io.netty.util.AttributeKey;

import magicball.model.math.Function;


public interface TransformationBasicCreator
{
	public static AttributeKey<TransformationBasicCreator> KEY = AttributeKey.<TransformationBasicCreator>valueOf("TransformationBasicCreator");
	// creater
	public Transformation createIdentityTransformation();
	
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func );
}
