package magicball.model.math;


public interface FunctionEngine
{
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );
	public < I, O > Function<I,O> createFunctionByLambda( LambdaFunction<I,O> lambda );

	public < I, O > Function<I,O> equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	public < I, O > O applies( Function<I,O> func, I in );
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins );
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in );
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins );
	// public < I, O > Set<I> domainOf( Function<I,O> func );
	// public < I, O > Set<O> codomainOf( Function<I,O> func );
}
