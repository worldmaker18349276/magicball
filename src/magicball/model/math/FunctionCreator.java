package magicball.model.math;

import java.util.function.Function;

import magicball.model.*;


public interface FunctionCreator extends Engine<Func>
{
	public < I > Func<I,I> createIdentityFunction();
	public < I, O > Func<I,O> createConstantFunction( O c );
	
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda );
}
