package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionBasicAttributeForLambda implements FunctionBasicAttribute, SpecEngine<Function,FunctionLambdaExpression>
{
	public FunctionBasicAttributeForLambda() {
		super();
	}


	// attribute

	// accept:
	//  func instanceof FunctionLambdaExpression
	private < I, O > FunctionLambdaExpression<I,O> cast( Function<I,O> func ) {
		try {
			return (FunctionLambdaExpression<I,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	private < I, O > java.util.function.Function<I,O> lambda( Function<I,O> func_ ) {
		FunctionLambdaExpression<I,O> func = cast(func_);
		return func.getLambdaFunction();
	}

	@Override
	public < I, O > O applies( Function<I,O> func_, I in ) {
		return lambda(func_).apply(in);
	}
}
