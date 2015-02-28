package magicball.model.math;

import magicball.model.*;


public interface FunctionBasicPredicate extends Engine<Function>
{
	// predicate
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value );
}
