package magicball.model.math;

import magicball.model.*;


public interface FunctionPredicate extends Engine<Func>
{
	public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 );
	public < I, O > boolean isConstantFunction( Func<I,O> func );

	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value );
}
