package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class BooleanFunctionOperatorForLambda implements BooleanFunctionOperator, SpecEngine<Function,FunctionLambdaExpression>
{
	private FunctionBasicCreator funcCreator;

	public BooleanFunctionOperatorForLambda() {
		super();
	}

	public BooleanFunctionOperatorForLambda( FunctionBasicCreator funcC ) {
		super();
		setEngine(funcC);
	}

	public void setEngine( FunctionBasicCreator funcC ) {
		funcCreator = funcC;
	}


	// operator
	private < I, O > FunctionLambdaExpression<I,O> cast( Function<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	private < I > java.util.function.Predicate<I> lambda( Function<I,Boolean> func_ ) {
		FunctionLambdaExpression<I,Boolean> func = cast(func_);
		return func.getLambdaFunction()::apply;
	}

	private < I > Function<I,Boolean> function( java.util.function.Predicate<I> lambda ) {
		return funcCreator.createFunctionByLambda(lambda::test);
	}

	@Override
	public < I > Function<I,Boolean> not( Function<I,Boolean> func ) {
		return function(lambda(func).negate());
	}

	@Override
	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(lambda(func1).and(lambda(func2).negate()));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce(java.util.function.Predicate::and)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(lambda(func1).and(lambda(func2)));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce(java.util.function.Predicate::or)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		return function(lambda(func1).or(lambda(func2)));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> xor( Function<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce((p1, p2) -> p1.or(p2).and(p1.and(p2).negate()))
			.map(this::function)
			.get();
	}

	@Override
	public < I > Function<I,Boolean> xor( Function<I,Boolean> func1, Function<I,Boolean> func2 ) {
		java.util.function.Predicate<I> func1_ = lambda(func1);
		java.util.function.Predicate<I> func2_ = lambda(func2);
		java.util.function.Predicate<I> func12_ = func1_.or(func2_).and(func1_.and(func2_).negate());
		return function(func12_);
	}
}
