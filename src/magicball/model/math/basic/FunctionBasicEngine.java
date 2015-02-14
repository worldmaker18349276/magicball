package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
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
	public < I, O > Function<I,O> function( java.util.function.Function<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}

	@Override
	public < I > Function<I,I> createIdentityFunction() {
		return function(i->i);
	}

	@Override
	public < I, O > Function<I,O> createConstantFunction( O c ) {
		return function(i->c);
	}


	// attribute
	@Override
	public < I, O > java.util.function.Function<I,O> getLambdaFunction( Function<I,O> func_ ) {
		FunctionLambdaExpression<I,O> func = cast(func_);
		return func.getLambdaFunction();
	}

	private < I, O > java.util.function.Function<I,O> lambda( Function<I,O> func_ ) {
		return getLambdaFunction(func_);
	}

	@Override
	public < I, O > O applies( Function<I,O> func, I in ) {
		return lambda(func).apply(in);
	}

	@Override
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins ) {
		return ins.stream()
			.map(lambda(func))
			.collect(Collectors.toSet());
	}

	@Override
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in ) {
		return new java.util.AbstractMap.SimpleEntry<I,O>(in,applies(func,in));
	}

	@Override
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins ) {
		return ins.stream()
			.collect(Collectors.toMap(i->i, lambda(func)));
	}


	// operator
	@Override
	public < I, M, O > Function<I,O> compose( Function<I,M> func1_, Function<M,O> func2_ ) {
		return function(lambda(func1_).andThen(lambda(func2_)));
	}
	
	@Override
	public < I, O > Function<O,I> invert( Function<I,O> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		throw new UnsupportedAlgorithmException();
	}


	private < I > java.util.function.Predicate<I> cast( java.util.function.Function<I,Boolean> func ) {
		return func::apply;
	}

	private < I > java.util.function.Function<I,Boolean> cast( java.util.function.Predicate<I> func ) {
		return func::test;
	}

	@Override
	public < I > Function<I,Boolean> not( Function<I,Boolean> func ) {
		return function(cast( cast(lambda(func)).negate() ));
	}

	@Override
	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(cast( cast(lambda(func1)).and(cast(lambda(func2)).negate()) ));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.map(this::cast)
			.reduce(java.util.function.Predicate::and)
			.map(this::cast)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(cast( cast(lambda(func1)).and(cast(lambda(func2))) ));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.map(this::cast)
			.reduce(java.util.function.Predicate::or)
			.map(this::cast)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(cast( cast(lambda(func1)).or(cast(lambda(func2))) ));
	}
}
