package magicball.model.math;


public interface FunctionCreatorForLambda
{
	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );
}
