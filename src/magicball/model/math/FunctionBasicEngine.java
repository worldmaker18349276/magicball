package magicball.model.math;

import java.util.function.Function;
import java.util.Optional;


public interface FunctionBasicEngine extends
		FunctionBehavior,
		FunctionCreator,
		FunctionAttribute,
		FunctionOperator,
		FunctionPredicate,
		BooleanFunctionOperator,
		BooleanFunctionPredicate
{
	// behavior
	@Override /* FunctionBehavior */
	public < I, O > O applies( Func<I,O> func, I in );


	// creater
	@Override /* FunctionCreator */
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda );

	@Override /* FunctionCreator */
	public < I > Func<I,I> createIdentityFunction();
	@Override /* FunctionCreator */
	public < I, O > Func<I,O> createConstantFunction( O c );


	// attribute
	@Override /* FunctionAttribute */
	public < I, O > Optional<O> getConstant( Func<I,O> func );


	// operator
	@Override /* FunctionOperator */
	public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 );
	@Override /* FunctionOperator */
	public < I, O > Func<O,I> invert( Func<I,O> func );
	
	@Override /* BooleanFunctionOperator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func );
	@Override /* BooleanFunctionOperator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionOperator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionOperator */
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionOperator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionOperator */
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionOperator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionOperator */
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 );


	// predicate
	@Override /* FunctionPredicate */
	public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 );
	@Override /* FunctionPredicate */
	public < I, O > boolean isConstantFunction( Func<I,O> func );
	@Override /* FunctionPredicate */
	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value );

	@Override /* BooleanFunctionPredicate */
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func );
	@Override /* BooleanFunctionPredicate */
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func );
	@Override /* BooleanFunctionPredicate */
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 );
}
