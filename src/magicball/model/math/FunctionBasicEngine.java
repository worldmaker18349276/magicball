package magicball.model.math;


public interface FunctionBasicEngine
{
	public < I, M, O > Function<I,O> compose( Function<I,M> func1, Function<M,O> func2 );
	public < I > Function<I,I> createIdentityFunction();

	public < I, O > Function<I,O> equals( Function<I,O> func1, Function<I,O> func2 );
	public < I, O > Function<O,I> invert( Function<I,O> func );
	public < I, O > O applies( Function<I,O> func, I in );
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins );
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in );
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins );
}
