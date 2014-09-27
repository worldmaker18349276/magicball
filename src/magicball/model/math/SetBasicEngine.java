package magicball.model.math;


public interface SetBasicEngine
{
	@SuppressWarnings({"unchecked"})
	public < E > Set<E> union( Set<E>... sets );
	public < E > Set<E> union( Set<E> set1, Set<E> set2 );
	@SuppressWarnings({"unchecked"})
	public < E > Set<E> intersect( Set<E>... sets );
	public < E > Set<E> intersect( Set<E> set1, Set<E> set2 );
	public < E > Set<E> complement( final Set<E> set1, final Set<E> set2 );
	public < E > Set<E> complement( final Set<E> set2 );
	public < E > Set<E> createEmptySet();
	public < E > Set<E> createUniverseSet();
	public < E > Set<E> createSetByIntensionalDefinition( final Function<E,Boolean> func );
}
