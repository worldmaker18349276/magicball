package magicball.model.math;


public interface FunctionBasicEngine extends
		FunctionBasicCreator,
		FunctionBasicAttribute,
		FunctionBasicOperator,
		FunctionBasicPredicate,
		FunctionCreatorForLambda,
		FunctionAttributeForLambda,
		BooleanFunctionOperator,
		BooleanFunctionPredicate
{
	public FunctionBasicEngine clone();


	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );

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
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value );
	public < I > boolean isAlwaysTrue( Function<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func );
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 );

	public < I > Function<I,Boolean> not( Function<I,Boolean> func );
	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}