package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;

public class SetEngineSampleAlgorithm < E > extends SetBasicEngine
{
	protected java.util.Set<E> samples;

	public SetEngineSampleAlgorithm( java.util.Set<E> sam ) {
		this.samples = sam;
	}

	@SuppressWarnings({"unchecked"})
	private < E_ > Set<E> cast( Set<E_> set ) {
		try {
			return (Set<E>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedAlgorithmException();
		}
	}

	private < E_ > java.util.Set<E_> filtersBy( java.util.Set<E_> samples, Function<E_,Boolean> filter ) {
		java.util.Set<E_> subsamples = new java.util.HashSet<E_>();
		for ( E_ e : samples )
			if ( filter.apply(e) )
				subsamples.add(e);
		return subsamples;
	}

	public < E_ > Function<E_,Boolean> createIntensionalFunction( final Set<E_> set ) {
		return new Function<E_,Boolean>() {
			public Boolean apply( E_ in ) {
				return set.isElement(in);
			}
		};
	}

	@SuppressWarnings({"unchecked"})
	public < E_ > java.util.Set<E_> createSampleSetOf( Set<E_> set_ ) {
		Set<E> set = cast(set_);
		return (java.util.Set<E_>) filtersBy(this.samples,createIntensionalFunction(set));
	}


	public < E_ > boolean isEmpty( Set<E_> set ) {
		return equals(set,this.<E_>createEmptySet());
	}

	public < E_ > boolean isUniversal( Set<E_> set ) {
		return equals(set,this.<E_>createUniversalSet());
	}

	public < E_ > boolean equals( Set<E_> set1_, Set<E_> set2_ ) {
		Set<E> set1 = cast(set1_);
		Set<E> set2 = cast(set2_);

		java.util.Set<E> sample1 = createSampleSetOf(set1);
		java.util.Set<E> sample2 = createSampleSetOf(set2);
		return sample1.equals(sample2);
	}

	public < E_ > boolean containsAll( Set<E_> set1_, Set<E_> set2_ ) {
		Set<E> set1 = cast(set1_);
		Set<E> set2 = cast(set2_);

		java.util.Set<E> sample2 = createSampleSetOf(set2);
		for ( E e : sample2 )
			if ( !contains(set1,e) )
				return false;
		return true;
	}

}
