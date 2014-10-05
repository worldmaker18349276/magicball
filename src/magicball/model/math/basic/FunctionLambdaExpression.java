package magicball.model.math.basic;

import magicball.model.math.*;


public class FunctionLambdaExpression < I, O > extends Function<I,O>
{
	protected LambdaFunction<I,O> lambda;

	public FunctionLambdaExpression( LambdaFunction<I,O> lam ) {
		this.lambda = lam;
	}

	public LambdaFunction<I,O> getLambdaFunction() {
		return this.lambda;
	}
}
