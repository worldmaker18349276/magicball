package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionBasicCreatorForLambda implements FunctionBasicCreator, SpecEnigne<Function,FunctionLambdaExpression>
{
	public FunctionBasicCreatorForLambda() {
		super();
	}


	// creater

	// result:
	//  return instanceof FunctionLambdaExpression
	@Override
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda ) {
		return new FunctionLambdaExpression<I,O>(lambda);
	}

	@Override
	public < I, O > Function<I,O> createFunctionByDescription( String syntax, String description ) {
		throw new UnsupportedAlgorithmException();
	}

	// result:
	//  return instanceof FunctionLambdaExpression
	@Override
	public < I > Function<I,I> createIdentityFunction() {
		return new FunctionLambdaExpression<I,I>(i->i);
	}

	// result:
	//  return instanceof FunctionLambdaExpression
	@Override
	public < I, O > Function<I,O> createConstantFunction( O c ) {
		return new FunctionLambdaExpression<I,O>(i->c);
	}
}
