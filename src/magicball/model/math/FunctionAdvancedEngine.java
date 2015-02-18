package magicball.model.math;


public interface FunctionAdvancedEngine extends
		FunctionBasicCreator,
		FunctionBasicAttribute,
		FunctionBasicOperator,
		FunctionBasicPredicate,
		BooleanFunctionCreator,
		BooleanFunctionOperator,
		BooleanFunctionPredicate
{
	public FunctionAdvancedEngine clone();


	// creater
	public < I, O > Function<I,O> createFunctionByLambda( java.util.function.Function<I,O> lambda );

	public < I > Function<I,I> createIdentityFunction();
	public < I, O > Function<I,O> createConstantFunction( O c );
	public < I > Function<I,Boolean> createEqualToFunction( I in );

	public Function<Boolean,Boolean> createNotFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createNotToFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createAndFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createOrFunction();
	public Function<Boolean,Function<Boolean,Boolean>> createXorFunction();


	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
	public < I1, I2, O > O applies( Function<I1,Function<I2,O>> func, I1 in1, I2 in2 );


	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	public < I1, I2, O > Function<I2,Function<I1,O>> swap( Function<I1,Function<I2,O>> func );
	public < I, O > Function<I,O> duplicateInput( Function<I,Function<I,O>> func );
	
	public < I > Function<I,Boolean> not( Function<I,Boolean> func );
	public < I > Function<I,Boolean> not( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> and( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> and( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Function<I,Boolean> or( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> or( Function<I,Boolean> func1, Function<I,Boolean> func2 );
	public < I > Function<I,Boolean> xor( Function<I,Boolean>... funcs );
	public < I > Function<I,Boolean> xor( Function<I,Boolean> func1, Function<I,Boolean> func2 );


	// predicate
	public < I, O > boolean equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func, O value );

	public < I > boolean isAlwaysTrue( Function<I,Boolean> func );
	public < I > boolean isAlwaysFalse( Function<I,Boolean> func );
	public < I > boolean implies( Function<I,Boolean> func1, Function<I,Boolean> func2 );
}
