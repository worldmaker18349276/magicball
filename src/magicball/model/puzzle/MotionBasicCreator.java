package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface MotionBasicCreator extends Engine<Motion>
{
	// creater
	public Motion createSimpleMotionByTransformation( Transformation trans );
	public RegionalMotion createRegionalMotion( Region reg, Motion move );
}
