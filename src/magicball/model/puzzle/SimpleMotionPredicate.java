package magicball.model.puzzle;

import magicball.model.*;


public interface SimpleMotionPredicate extends Engine<Motion>
{
	// predicate
	public boolean isSimpleMotion( Motion move );
}
