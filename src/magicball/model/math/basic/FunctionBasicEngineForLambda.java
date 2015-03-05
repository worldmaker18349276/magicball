package magicball.model.math.basic;

import java.util.Optional;
import java.util.function.Function;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionBasicEngineForLambda implements SpecEngine<Func,FunctionLambdaExpression>,
		FunctionBasic.Behavior,
		FunctionBasic.Creator,
		FunctionBasic.Attribute,
		FunctionBasic.Operator
{
	public FunctionBasicEngineForLambda() {
	}

	private < I, O > FunctionLambdaExpression<I,O> cast( Func<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}


	private < I, O > Function<I,O> lambda( Func<I,O> func_ ) {
		return cast(func_).getLambdaFunction();
	}

	private < I, O > Func<I,O> function( Function<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}


	// behavior
	@Override
	public < I, O > O applyTo( Func<I,O> func_, I in ) {
		return lambda(func_).apply(in);
	}


	// creater
	@Override
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda ) {
		return function(lambda);
	}

	@Override
	public < I > Func<I,I> createIdentityFunction() {
		return function(i->i);
	}

	@Override
	public < I, O > Func<I,O> createConstantFunctionWithValue( O constant ) {
		return function(i->constant);
	}


	// attribute
	@Override
	public < I, O > Optional<O> getConstantValueOf( Func<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}


	// operator
	@Override
	public < I, M, O > Func<I,O> compose( Func<I,M> func1_, Func<M,O> func2_ ) {
		return function(lambda(func1_).andThen(lambda(func2_)));
	}
	
	@Override
	public < I, O > Optional<Func<O,I>> invert( Func<I,O> func_ ) {
		throw new UnsupportedAlgorithmException();
	}
}
