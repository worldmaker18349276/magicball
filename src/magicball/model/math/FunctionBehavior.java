package magicball.model.math;

import magicball.model.*;


public interface FunctionBehavior extends Engine<Function>
{
	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
}
