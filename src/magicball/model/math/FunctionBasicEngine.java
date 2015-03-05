package magicball.model.math;

import java.util.function.Function;
import java.util.Optional;


public interface FunctionBasicEngine extends
		FunctionBasic.Behavior,
		FunctionBasic.Creator,
		FunctionBasic.Attribute,
		FunctionBasic.Operator,
		FunctionBasic.Predicate,
		BooleanFunctionBasic.Operator,
		BooleanFunctionBasic.Predicate
{
	// behavior
	@Override /* FunctionBasic.Behavior */
	public < I, O > O applyTo( Func<I,O> func, I in );


	// creater
	@Override /* FunctionBasic.Creator */
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda );

	@Override /* FunctionBasic.Creator */
	public < I > Func<I,I> createIdentityFunction();
	@Override /* FunctionBasic.Creator */
	public < I, O > Func<I,O> createConstantFunctionWithValue( O constant );


	// attribute
	@Override /* FunctionBasic.Attribute */
	public < I, O > Optional<O> getConstantValueOf( Func<I,O> func );


	// operator
	@Override /* FunctionBasic.Operator */
	public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 );
	@Override /* FunctionBasic.Operator */
	public < I, O > Optional<Func<O,I>> invert( Func<I,O> func );
	
	@Override /* BooleanFunctionBasic.Operator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasic.Operator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasic.Operator */
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasic.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionBasic.Operator */
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasic.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionBasic.Operator */
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasic.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs );


	// predicate
	@Override /* FunctionBasic.Predicate */
	public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 );
	@Override /* FunctionBasic.Predicate */
	public < I, O > boolean isInvertible( Func<I,O> func );
	@Override /* FunctionBasic.Predicate */
	public < I, O > boolean isIdentityFunction( Func<I,O> func );
	@Override /* FunctionBasic.Predicate */
	public < I, O > boolean isConstantFunction( Func<I,O> func );
	@Override /* FunctionBasic.Predicate */
	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value );

	@Override /* BooleanFunctionBasic.Predicate */
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasic.Predicate */
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasic.Predicate */
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 );
}
