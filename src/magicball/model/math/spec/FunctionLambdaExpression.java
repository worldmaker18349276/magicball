package magicball.model.math.spec;

import java.util.function.Function;

import magicball.model.math.*;


public class FunctionLambdaExpression < I, O > extends Func<I,O>
{
	protected Function<I,O> lambda;

	public FunctionLambdaExpression( Function<I,O> lam ) {
		this.lambda = lam;
	}

	public Function<I,O> getLambdaFunction() {
		return this.lambda;
	}
}
