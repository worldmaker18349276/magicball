package magicball.model.math;


public interface SetBasicEngine
{
	@SuppressWarnings({"unchecked"})
	public < E > Set<E> union( Set<E>... sets );
	public < E > Set<E> union( Set<E> set1, Set<E> set2 );
	@SuppressWarnings({"unchecked"})
	public < E > Set<E> intersect( Set<E>... sets );
	public < E > Set<E> intersect( Set<E> set1, Set<E> set2 );
	public < E > Set<E> complement( Set<E> set1, Set<E> set2 );
	public < E > Set<E> complement( Set<E> set2 );
	public < E > Set<E> createEmptySet();
	public < E > Set<E> createUniversalSet();
	public < E > Set<E> createSetByIntensionalDefinition( Function<E,Boolean> func );

	public < E > boolean isEmpty( Set<E> set );
	public < E > boolean isUniversal( Set<E> set );
	public < E > boolean equals( Set<E> set1, Set<E> set2 );
	public < E > boolean containsAll( Set<E> set1, Set<E> set2 );
	public < E > boolean contains( Set<E> set1, E e );
}
