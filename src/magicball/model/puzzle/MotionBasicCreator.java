package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface MotionBasicCreator
{
	// creater
	public Motion createSimpleMotionByTransformation( Transformation trans );
	public RegionalMotion createRegionalMotion( Region reg, Motion move );
}
