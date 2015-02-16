package magicball.model.math;


public interface FunctionBasicAttribute
{
	// attribute
	public < I, O > O applies( Function<I,O> func, I in );
	public < I, O > java.util.Set<O> appliesAll( Function<I,O> func, java.util.Set<I> ins );
	public < I, O > java.util.Map.Entry<I,O> maps( Function<I,O> func, I in );
	public < I, O > java.util.Map<I,O> mapsAll( Function<I,O> func, java.util.Set<I> ins );
}
