package magicball.model.math;

import magicball.model.*;


public interface BooleanFunctionOperator extends Engine<Func>
{
	public < I > Func<I,Boolean> not( Func<I,Boolean> func );
	public < I > Func<I,Boolean> not( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> and( Func<I,Boolean>... funcs );
	public < I > Func<I,Boolean> and( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> or( Func<I,Boolean>... funcs );
	public < I > Func<I,Boolean> or( Func<I,Boolean> func1, Func<I,Boolean> func2 );
	@SuppressWarnings({"unchecked", "varargs"})
	public < I > Func<I,Boolean> xor( Func<I,Boolean>... funcs );
	public < I > Func<I,Boolean> xor( Func<I,Boolean> func1, Func<I,Boolean> func2 );
}
