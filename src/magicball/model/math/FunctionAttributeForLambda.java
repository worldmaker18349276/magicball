package magicball.model.math;


public interface FunctionAttributeForLambda
{
	// attribute
	public < I, O > java.util.function.Function<I,O> getLambdaFunction( Function<I,O> func );
}
