package magicball.model.math;

import java.util.Optional;

import magicball.model.*;


public interface FunctionAttribute extends Engine<Func>
{
	public < I, O > Optional<O> getConstantValueOf( Func<I,O> func );
}
