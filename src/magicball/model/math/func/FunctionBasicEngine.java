package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;


public class FunctionBasicEngine implements FunctionEngine
{
	public FunctionBasicEngine() { }

	@Override
	public FunctionBasicEngine clone() {
		return new FunctionBasicEngine();
	}

	protected < I, O > FunctionLambdaExpression<I,O> cast( Function<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}


	// creater
	@Override
	public < I, O > Function<I,O> function( LambdaFunction<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}

	@Override
	public < I > Function<I,I> createIdentityFunction() {
		return function(new LambdaFunction<I,I>() {
			@Override
			public I apply( I in ) {
				return in;
			}
		});
	}

	@Override
	public < I, O > Function<I,O> createConstantFunction( final O c ) {
		return function(new LambdaFunction<I,O>() {
			@Override
			public O apply( I in ) {
				return c;
			}
		});
	}


	// attribute
	@Override
	public < I, O > LambdaFunction<I,O> getLambdaFunction( Function<I,O> func_ ) {
		FunctionLambdaExpression<I,O> func = cast(func_);
		return func.getLambdaFunction();
	}

	@Override
	public < I, O > O applies( Function<I,O> func, I in ) {
		return getLambdaFunction(func).apply(in);
	}

	@Override
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Set<O> outs = new java.util.HashSet<O>();
		for ( I in : ins )
			outs.add(applies(func,in));
		return outs;
	}

	@Override
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in ) {
		return new java.util.AbstractMap.SimpleEntry<I,O>(in,applies(func,in));
	}

	@Override
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Map<I,O> outs = new java.util.HashMap<I,O>();
		for ( I in : ins )
			outs.put(in,applies(func,in));
		return outs;
	}


	// operator
	@Override
	public < I, M, O > Function<I,O> compose( Function<I,M> func1_, Function<M,O> func2_ ) {
		final LambdaFunction<I,M> func1 = getLambdaFunction(func1_);
		final LambdaFunction<M,O> func2 = getLambdaFunction(func2_);
		return function(new LambdaFunction<I,O>() {
			@Override
			public O apply( I in ) {
				return func2.apply(func1.apply(in));
			}
		});
	}
	
	@Override
	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}
}
