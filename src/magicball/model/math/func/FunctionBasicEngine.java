package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;


public class FunctionBasicEngine implements FunctionEngine
{

	protected < I, O > FunctionLambdaExpression<I,O> cast( Function<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	public < I, O > LambdaFunction<I,O> getLambdaFunction( Function<I,O> func_ ) {
		FunctionLambdaExpression<I,O> func = cast(func_);
		return func.getLambdaFunction();
	}

	public < I, M, O > Function<I,O> compose( Function<I,M> func1_, Function<M,O> func2_ ) {
		final LambdaFunction<I,M> func1 = getLambdaFunction(func1_);
		final LambdaFunction<M,O> func2 = getLambdaFunction(func2_);
		return createFunctionByLambda(new LambdaFunction<I,O>() {
			public O apply( I in ) {
				return func2.apply(func1.apply(in));
			}
		});
	}

	public < I, O > Function<I,O> createFunctionByLambda( LambdaFunction<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}

	public < I > Function<I,I> createIdentityFunction() {
		return createFunctionByLambda(new LambdaFunction<I,I>() {
			public I apply( I in ) {
				return in;
			}
		});
	}

	public < I, O > Function<I,O> createConstantFunction( final O c ) {
		return createFunctionByLambda(new LambdaFunction<I,O>() {
			public O apply( I in ) {
				return c;
			}
		});
	}

	public < I, O > O applies( Function<I,O> func, I in ) {
		return getLambdaFunction(func).apply(in);
	}

	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Set<O> outs = new java.util.HashSet<O>();
		for ( I in : ins )
			outs.add(applies(func,in));
		return outs;
	}

	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in ) {
		return new java.util.AbstractMap.SimpleEntry<I,O>(in,applies(func,in));
	}

	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins ) {
		java.util.Map<I,O> outs = new java.util.HashMap<I,O>();
		for ( I in : ins )
			outs.put(in,applies(func,in));
		return outs;
	}


	public < I, O > Function<I,O> equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}
	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}
}
