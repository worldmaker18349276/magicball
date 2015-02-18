package magicball.model.math;


public interface FunctionBasicOperator
{
	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	public < I1, I2, O > Function<I2,Function<I1,O>> swap( Function<I1,Function<I2,O>> func );
	public < I, O > Function<I,O> duplicateInput( Function<I,Function<I,O>> func );
}
