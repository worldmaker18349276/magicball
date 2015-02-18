package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionEngineForLambda implements FunctionAdvancedEngine, Engine<FunctionLambdaExpression>
{
	public FunctionEngineForLambda() { }

	@Override
	public FunctionEngineForLambda clone() {
		return new FunctionEngineForLambda();
	}

	public < I, O > FunctionLambdaExpression<I,O> cast( Function<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}


	// creater
	@Override
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}

	protected < I, O > Function<I,O> function( java.util.function.Function<I,O> lambda ) {
		return createFunctionByLambda(lambda);
	}

	protected < I1, I2, O > Function<I1,Function<I2,O>> function( java.util.function.BiFunction<I1,I2,O> lambda ) {
		return createFunctionByLambda(in1 -> createFunctionByLambda(in2 -> lambda.apply(in1, in2)));
	}

	@Override
	public < I > Function<I,I> createIdentityFunction() {
		return function(i->i);
	}

	@Override
	public < I, O > Function<I,O> createConstantFunction( O c ) {
		return function(i->c);
	}

	@Override
	public < I > Function<I,Boolean> createEqualToFunction( I in ) {
		return function(i -> in.equals(i));
	}

	@Override
	public Function<Boolean,Boolean> createNotFunction() {
		return function(p -> !p);
	}

	public Function<Boolean,Function<Boolean,Boolean>> createNotToFunction() {
		return function((p, q) -> p&&!q);
	}

	public Function<Boolean,Function<Boolean,Boolean>> createAndFunction() {
		return function(Boolean::logicalAnd);
	}

	public Function<Boolean,Function<Boolean,Boolean>> createOrFunction() {
		return function(Boolean::logicalOr);
	}

	public Function<Boolean,Function<Boolean,Boolean>> createXorFunction() {
		return function(Boolean::logicalXor);
	}


	// attribute
	private < I, O > java.util.function.Function<I,O> lambda( Function<I,O> func_ ) {
		FunctionLambdaExpression<I,O> func = cast(func_);
		return func.getLambdaFunction();
	}

	@Override
	public < I, O > O applies( Function<I,O> func, I in ) {
		return lambda(func).apply(in);
	}

	@Override
	public < I1, I2, O > O applies( Function<I1,Function<I2,O>> func, I1 in1, I2 in2 ) {
		return applies(applies(func, in1), in2);
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
	public < I1, I2, O > Function<I2,Function<I1,O>> swap( Function<I1,Function<I2,O>> func_ ) {
		return function((in1, in2) -> applies(func_, in2, in1));
	}

	@Override
	public < I, O > Function<I,O> duplicateInput( Function<I,Function<I,O>> func_ ) {
		return function(in -> applies(func_, in, in));
	}

	@Override
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func_, O value ) {
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

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> xor( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.map(this::cast)
			.reduce(( p1, p2 ) -> p1.or(p2).and(p1.and(p2).negate()))
			.map(this::cast)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> xor( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		java.util.function.Predicate<I> func1_ = cast(lambda(func1));
		java.util.function.Predicate<I> func2_ = cast(lambda(func2));
		java.util.function.Predicate<I> func12_ = func1_.or(func2_).and(func1_.and(func2_).negate());
		return function(cast(func12_));
	}
}
