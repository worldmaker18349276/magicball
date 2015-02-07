package magicball.model.math.sample;

import magicball.model.*;
import magicball.model.math.*;
import magicball.model.math.basic.*;

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
	private < E_ > java.util.Set<E_> castToE_( java.util.Set<E> set ) {
		try {
			return (java.util.Set<E_>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedAlgorithmException();
		}
	}

	private java.util.function.Function<E,Boolean> lambda( Set<E> set ) {
		return funcEngine.getLambdaFunction(getIntensionFunction(set));
	}

	public < E_ > java.util.Set<E_> createSampleSetOf( Set<E_> set_ ) {
		Set<E> set = castToE(set_);
		java.util.Set<E> subsamples = this.samples.stream()
			.filter(lambda(set))
			.collect(Collectors.toSet());
		return <E_>castToE_(subsamples);
	}


	// attribute
	@Override
	public < E_ > boolean containsAll( Set<E_> set1_, Set<E_> set2_ ) {
		Set<E> set1 = castToE(set1_);
		Set<E> set2 = castToE(set2_);

		return createSampleSetOf(set2).stream().allMatch(lambda(set1));
	}


	// operator
	@Override
	public < E_ > boolean isEmpty( Set<E_> set_ ) {
		Set<E> set = castToE(set_);
		return this.samples.stream()
			.noneMatch(lambda(set));
	}

	@Override
	public < E_ > boolean isUniversal( Set<E_> set_ ) {
		Set<E> set = castToE(set_);
		return this.samples.stream()
			.allMatch(lambda(set));
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
