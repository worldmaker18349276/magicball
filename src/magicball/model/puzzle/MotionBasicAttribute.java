package magicball.model.puzzle;

import magicball.model.geometry.*;
import magicball.model.*;


public interface MotionBasicAttribute extends Engine<Motion>
{
	// attribute
	public Transformation getTransformation( Motion move );
}
