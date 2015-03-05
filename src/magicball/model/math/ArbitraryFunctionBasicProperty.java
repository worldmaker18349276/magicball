package magicball.model.math;

import java.util.function.Function;
import java.util.Optional;

import magicball.model.*;


public class ArbitraryFunctionBasicProperty
{
	static public interface Behavior extends Engine<Func>
	{
		public < I, O > O applyTo( Func<I,O> func, I in );
	}

	static public interface Creator extends Engine<Func>
	{
		public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda );
		public < I > Func<I,I> createIdentityFunction();
		public < I, O > Func<I,O> createConstantFunctionWithValue( O constant );
	}

	static public interface Attribute extends Engine<Func>
	{
		public < I, O > Optional<O> getConstantValueOf( Func<I,O> func );
	}

	static public interface Operator extends Engine<Func>
	{
		public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 );
		public < I, O > Optional<Func<O,I>> invert( Func<I,O> func );
	}

	static public interface Predicate extends Engine<Func>
	{
		public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 );
		public < I, O > boolean isInvertible( Func<I,O> func );
		public < I, O > boolean isIdentityFunction( Func<I,O> func );
		public < I, O > boolean isConstantFunction( Func<I,O> func );
		public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value );
	}
}
