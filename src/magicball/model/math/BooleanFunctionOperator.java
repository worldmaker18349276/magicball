package magicball.model.math;

import io.netty.util.AttributeKey;


public interface BooleanFunctionOperator
{
	public static AttributeKey<BooleanFunctionOperator> KEY = AttributeKey.<BooleanFunctionOperator>valueOf("BooleanFunctionOperator");
	// operator
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
}
