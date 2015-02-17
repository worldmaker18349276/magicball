package magicball.model.math;


public interface FunctionBasicCreator
{
	// creater
	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );
	
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );
}
