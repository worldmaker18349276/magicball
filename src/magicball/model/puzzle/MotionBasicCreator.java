package magicball.model.puzzle;

import io.netty.util.AttributeKey;

import magicball.model.geometry.*;


public interface MotionBasicCreator
{
	public static AttributeKey<MotionBasicCreator> KEY = AttributeKey.<MotionBasicCreator>valueOf("MotionBasicCreator");
	// creater
	public Motion createSimpleMotionByTransformation( Transformation trans );
	public RegionalMotion createRegionalMotion( Region reg, Motion move );
}
