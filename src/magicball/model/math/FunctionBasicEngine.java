package magicball.model.math;


public interface FunctionBasicEngine
{
	public < I, M, O > Function<I,O> compose( final Function<I,M> func1, final Function<M,O> func2 );
	public < I > Function<I,I> createIdentityFunction();
}
