package magicball.model.math.spec;

import java.util.function.Predicate;
import java.util.stream.Stream;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class BooleanFunctionOperatorForLambda implements SpecEngine<Func,FunctionLambdaExpression>,
		BooleanFunctionBasicProperty.Operator
{
	public BooleanFunctionOperatorForLambda() {
	}


	private < I, O > FunctionLambdaExpression<I,O> cast( Func<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	private < I > Predicate<I> lambda( Func<I,Boolean> func_ ) {
		FunctionLambdaExpression<I,Boolean> func = cast(func_);
		return func.getLambdaFunction()::apply;
	}

	private < I > Func<I,Boolean> function( Predicate<I> lambda ) {
		return new FunctionLambdaExpression<I,Boolean>(lambda::test);
	}


	// operator
	@Override
	public < I > Func<I,Boolean> not( Func<I,Boolean> func ) {
		return function(lambda(func).negate());
	}

	@Override
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		return function(lambda(func1).and(lambda(func2).negate()));
	}

	@Override
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		return function(lambda(func1).and(lambda(func2)));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce(Predicate::and)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		return function(lambda(func1).or(lambda(func2)));
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce(Predicate::or)
			.map(this::function)
			.get();
	}

	@Override
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 ) {
		Predicate<I> func1_ = lambda(func1);
		Predicate<I> func2_ = lambda(func2);
		Predicate<I> func12_ = func1_.or(func2_).and(func1_.and(func2_).negate());
		return function(func12_);
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs ) {
		return Stream.of(funcs)
			.map(this::lambda)
			.reduce((p1, p2) -> p1.or(p2).and(p1.and(p2).negate()))
			.map(this::function)
			.get();
	}
}
