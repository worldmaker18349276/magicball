package magicball.model.math.basic;

import magicball.model.*;
import magicball.model.math.*;

public class SetEngineSampleAlgorithm < E > extends SetBasicEngine
{
	protected java.util.Set<E> samples;

	public SetEngineSampleAlgorithm( FunctionEngine funcEng, java.util.Set<E> sam ) {
		super(funcEng);
		this.samples = sam;
	}

	@Override
	public SetEngineSampleAlgorithm<E> clone() {
		return new SetEngineSampleAlgorithm<E>(this.funcEngine,this.samples);
	}

	@SuppressWarnings({"unchecked"})
	private < E_ > Set<E> castToE( Set<E_> set ) {
		try {
			return (Set<E>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedAlgorithmException();
		}
	}

	@SuppressWarnings({"unchecked"})
	public < E_ > java.util.Set<E_> createSampleSetOf( Set<E_> set_ ) {
		Function<E,Boolean> filter = getIntensionFunction(castToE(set_));
		java.util.Set<E> subsamples = new java.util.HashSet<E>();
		for ( E e : this.samples )
			if ( this.funcEngine.applies(filter,e) )
				subsamples.add(e);
		return (java.util.Set<E_>) subsamples;
	}


	// attribute
	@Override
	public < E_ > boolean containsAll( Set<E_> set1_, Set<E_> set2_ ) {
		Set<E> set1 = castToE(set1_);
		Set<E> set2 = castToE(set2_);

		java.util.Set<E> sample2 = createSampleSetOf(set2);
		for ( E e : sample2 )
			if ( !contains(set1,e) )
				return false;
		return true;
	}


	// operator
	@Override
	public < E_ > boolean isEmpty( Set<E_> set ) {
		return equals(set,this.<E_>createEmptySet());
	}

	@Override
	public < E_ > boolean isUniversal( Set<E_> set ) {
		return equals(set,this.<E_>createUniversalSet());
	}

	@Override
	public < E_ > boolean equals( Set<E_> set1_, Set<E_> set2_ ) {
		Set<E> set1 = castToE(set1_);
		Set<E> set2 = castToE(set2_);

		java.util.Set<E> sample1 = createSampleSetOf(set1);
		java.util.Set<E> sample2 = createSampleSetOf(set2);
		return sample1.equals(sample2);
	}
}
