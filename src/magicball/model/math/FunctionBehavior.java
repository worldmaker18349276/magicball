package magicball.model.math;

import magicball.model.*;


public interface FunctionBehavior extends Engine<Func>
{
	public < I, O > O applies( Func<I,O> func, I in );
}