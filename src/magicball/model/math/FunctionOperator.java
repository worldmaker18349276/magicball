package magicball.model.math;

import magicball.model.*;


public interface FunctionOperator extends Engine<Func>
{
	public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 );
	public < I, O > Func<O,I> invert( Func<I,O> func );
}
