package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


public class FunctionEngineForLambdaWithSampleAlgorithm < E > extends FunctionEngineForLambda
{
	protected java.util.Set<E> samples;

	public FunctionEngineForLambdaWithSampleAlgorithm( java.util.Set<E> sam ) {
		this.samples = sam;
	}

	@SuppressWarnings({"unchecked"})
	private < O > Function<E,O> castToE( Function<?,O> func ) {
		try {
			return (Function<E,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}


	@Override
	public < I, O > Function<O,I> invert( Function<I,O> func_ ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean equals( Function<I,O> func1_, Function<I,O> func2_ ) {
		Function<E,O> func1 = this.castToE(func1_);
		Function<E,O> func2 = this.castToE(func2_);
		return samples.stream()
			.allMatch(e -> applies(func1,e).equals(applies(func2,e)));
	}

	@Override
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func_, O value ) {
		Function<E,O> func = this.castToE(func_);
		return samples.stream()
			.map(e -> applies(func,e))
			.allMatch(value::equals);
	}

	@Override
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func_ ) {
		Function<E,Boolean> func = this.castToE(func_);
		return samples.stream()
			.allMatch(e -> applies(func,e));
	}

	@Override
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func_ ) {
		Function<E,Boolean> func = this.castToE(func_);
		return samples.stream()
			.noneMatch(e -> applies(func,e));
	}

	@Override
	public < I > boolean implies( Function<I,Boolean> func1_, Function<I,Boolean> func2_ ) {
		Function<E,Boolean> func1 = this.castToE(func1_);
		Function<E,Boolean> func2 = this.castToE(func2_);
		return samples.stream()
			.allMatch(e -> applies(func2,e)||!applies(func1,e));
	}
}
