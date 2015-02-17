package magicball.model.math;


public interface FunctionBasicAttribute
{
	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
}
