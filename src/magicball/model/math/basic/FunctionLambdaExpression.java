package magicball.model.math.basic;

import magicball.model.math.*;


public class FunctionLambdaExpression < I, O > extends Function<I,O>
{
	protected java.util.function.Function<I,O> lambda;

	public FunctionLambdaExpression( java.util.function.Function<I,O> lam ) {
		this.lambda = lam;
	}

	public java.util.function.Function<I,O> getLambdaFunction() {
		return this.lambda;
	}
}
