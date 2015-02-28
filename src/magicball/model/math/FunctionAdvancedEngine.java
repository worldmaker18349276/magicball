package magicball.model.math;


public interface FunctionAdvancedEngine extends
		FunctionBasicCreator,
		FunctionBasicAttribute,
		FunctionBasicOperator,
		FunctionBasicPredicate,
		BooleanFunctionOperator,
		BooleanFunctionPredicate
{
	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );
	public < I, O > Function<I,O> createFunctionByDescription( String syntax, String description );

	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );


	// attribute
	public < I, O > O applies( Function<I,O> func, I in );


	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	
	public < I > Function<I,Boolean> not( Function<I,Boolean> func );
	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> xor( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> xor( Function<I,Boolean> func1, Function<I,Boolean> func2 );


	// predicate
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value );

	public < I > boolean isAlwaysTrue( Function<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func );
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}
