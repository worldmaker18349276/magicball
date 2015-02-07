package magicball.model.math;


public interface FunctionEngine
{
	public FunctionEngine clone();


	// creater
	public < I, O > Function<I,O> function( java.util.function.Function<I,O> lambda );

	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );


	// attribute
	public < I, O > java.util.function.Function<I,O> getLambdaFunction( Function<I,O> func );

	public < I, O > O applies( Function<I,O> func, I in );
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins );
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in );
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins );


	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 );

	public < I > Function<I,Boolean> negate( Function<I,Boolean> func );
	@SuppressWarnings({"unchecked"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... func1s );
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}
