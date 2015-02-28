package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionBasicOperatorForLambda implements FunctionBasicOperator, SpecEnigne<Function,FunctionLambdaExpression>
{
	// dependent:
	//  create function, bifunction
	private FunctionBasicCreator funcCreator;

	public FunctionBasicOperatorForLambda() {
		super();
	}

	public FunctionBasicOperatorForLambda( FunctionBasicCreator funcC ) {
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

	private < I, O > java.util.function.Function<I,O> lambda( Function<I,O> func_ ) {
		return cast(func_).getLambdaFunction();
	}

	private < I, O > Function<I,O> function( java.util.function.Function<I,O> lambda ) {
		return funcCreator.createFunctionByLambda(lambda);
	}

	// accept:
	//  func1_ instanceof FunctionLambdaExpression
	//  func2_ instanceof FunctionLambdaExpression
	@Override
	public < I, M, O > Function<I,O> compose( Function<I,M> func1_, Function<M,O> func2_ ) {
		return function(lambda(func1_).andThen(lambda(func2_)));
	}
	
	@Override
	public < I, O > Function<O,I> invert( Function<I,O> func_ ) {
		throw new UnsupportedAlgorithmException();
	}
}
