package magicball.model.math;

import java.util.function.Function;
import java.util.Optional;


public interface FunctionBasicEngine extends
		ArbitraryFunctionBasicProperty.Behavior,
		ArbitraryFunctionBasicProperty.Creator,
		ArbitraryFunctionBasicProperty.Attribute,
		ArbitraryFunctionBasicProperty.Operator,
		ArbitraryFunctionBasicProperty.Predicate,
		BooleanFunctionBasicProperty.Operator,
		BooleanFunctionBasicProperty.Predicate
{
	// behavior
	@Override /* ArbitraryFunctionBasicProperty.Behavior */
	public < I, O > O applyTo( Func<I,O> func, I in );


	// creater
	@Override /* ArbitraryFunctionBasicProperty.Creator */
	public < I, O > Func<I,O> createFunctionByLambda( Function<I,O> lambda );

	@Override /* ArbitraryFunctionBasicProperty.Creator */
	public < I > Func<I,I> createIdentityFunction();
	@Override /* ArbitraryFunctionBasicProperty.Creator */
	public < I, O > Func<I,O> createConstantFunctionWithValue( O constant );


	// attribute
	@Override /* ArbitraryFunctionBasicProperty.Attribute */
	public < I, O > Optional<O> getConstantValueOf( Func<I,O> func );


	// operator
	@Override /* ArbitraryFunctionBasicProperty.Operator */
	public < I, M, O > Func<I,O> compose( Func<I,M> func1, Func<M,O> func2 );
	@Override /* ArbitraryFunctionBasicProperty.Operator */
	public < I, O > Optional<Func<O,I>> invert( Func<I,O> func );
	
	@Override /* BooleanFunctionBasicProperty.Operator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasicProperty.Operator */
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasicProperty.Operator */
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasicProperty.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionBasicProperty.Operator */
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasicProperty.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs );
	@Override /* BooleanFunctionBasicProperty.Operator */
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@Override /* BooleanFunctionBasicProperty.Operator */
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs );


	// predicate
	@Override /* ArbitraryFunctionBasicProperty.Predicate */
	public < I, O > boolean equals( Func<I,O> func1, Func<I,O> func2 );
	@Override /* ArbitraryFunctionBasicProperty.Predicate */
	public < I, O > boolean isInvertible( Func<I,O> func );
	@Override /* ArbitraryFunctionBasicProperty.Predicate */
	public < I, O > boolean isIdentityFunction( Func<I,O> func );
	@Override /* ArbitraryFunctionBasicProperty.Predicate */
	public < I, O > boolean isConstantFunction( Func<I,O> func );
	@Override /* ArbitraryFunctionBasicProperty.Predicate */
	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func, O value );

	@Override /* BooleanFunctionBasicProperty.Predicate */
	public < I > boolean isAlwaysTrue( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasicProperty.Predicate */
	public < I > boolean isAlwaysFalse( Func<I,Boolean> func );
	@Override /* BooleanFunctionBasicProperty.Predicate */
	public < I > boolean implies( Func<I,Boolean> func1, Func<I,Boolean> func2 );
}
