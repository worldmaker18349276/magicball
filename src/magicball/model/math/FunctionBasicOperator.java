package magicball.model.math;


public interface FunctionBasicOperator
{
	// operator
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
}
