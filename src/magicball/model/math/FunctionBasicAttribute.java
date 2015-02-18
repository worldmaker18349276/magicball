package magicball.model.math;


public interface FunctionBasicAttribute
{
	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
	public < I1, I2, O > O applies( Function<I1,Function<I2,O>> func, I1 in1, I2 in2 );
}
