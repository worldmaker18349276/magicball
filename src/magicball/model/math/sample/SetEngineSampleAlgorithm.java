package magicball.model.math.sample;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


public class SetEngineSampleAlgorithm < E > implements SetEngine
{
	protected FunctionEngine funcEngine;
	protected java.util.Set<E> samples;

	public SetEngineSampleAlgorithm( FunctionEngine funcEng, java.util.Set<E> sam ) {
		this.funcEngine = funcEng;
		this.samples = sam;
	}

	public SetEngineSampleAlgorithm( EngineProvider provider, java.util.Set<E> sam ) {
		this.funcEngine = provider.getFunctionEngine();
		this.samples = sam;
	}

	@Override
	public SetEngineSampleAlgorithm<E> clone() {
		return new SetEngineSampleAlgorithm<E>(funcEngine, this.samples);
	}

	@SuppressWarnings({"unchecked"})
	protected < E_ > SetSampleExpression<E> cast( Set<E_> set ) {
		try {
			return (SetSampleExpression<E>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(set.getClass());
		}
	}

	@SuppressWarnings({"unchecked"})
	protected < E_ > Set<E_> cast( SetSampleExpression<E> set ) {
		try {
			return (Set<E_>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException();
		}
	}


	// creater
	@Override
	@SuppressWarnings({"unchecked"})
	public < E_ > Set<E_> createSetByFunction( Function<E_,Boolean> func ) {
		try {

			java.util.Set<E> subsamples = samples.stream()
				.filter(e -> funcEngine.applies(func,(E_)e))
				.collect(Collectors.toSet());
			return cast(new SetSampleExpression<E>(subsamples));

		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException();
		}
	}

	@Override
	public < E_ > Set<E_> createEmptySet() {
		return new SetSampleExpression<E_>(new java.util.HashSet<E_>());
	}

	@Override
	public < E_ > Set<E_> createUniversalSet() {
		return cast(new SetSampleExpression<E>(samples));
	}


	// attribute
	@Override
	public < E_ > Function<E_,Boolean> getIntensionFunction( Set<E_> set_ ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < E_ > boolean contains( Set<E_> set_, E_ e_ ) {
		java.util.Set<E> sam = cast(set_).getSample();
		return sam.contains(e_);
	}

	@Override
	public < E_ > boolean containsAll( Set<E_> set1_, Set<E_> set2_ ) {
		java.util.Set<E> sam1 = cast(set1_).getSample();
		java.util.Set<E> sam2 = cast(set2_).getSample();
		return sam1.containsAll(sam2);
	}


	// operator
	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < E_ > Set<E_> union( Set<E_>... sets_ ) {
		return Stream.of(sets_)
			.map(this::cast)
			.map(SetSampleExpression::getSample)
			.reduce(this::union)
			.map(SetSampleExpression::new)
			.map(this::<E_>cast)
			.get();
	}

	@Override
	public < E_ > Set<E_> union( Set<E_> set1_, Set<E_> set2_ ) {
		java.util.Set<E> sam1 = cast(set1_).getSample();
		java.util.Set<E> sam2 = cast(set2_).getSample();
		java.util.Set<E> sam12 = union(sam1, sam2);
		return cast(new SetSampleExpression<E>(sam12));
	}

	protected java.util.Set<E> union( java.util.Set<E> sam1, java.util.Set<E> sam2 ) {
		java.util.Set<E> sam12 = new java.util.HashSet<E>(sam1);
		sam12.addAll(sam2);
		return sam12;
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < E_ > Set<E_> intersect( Set<E_>... sets_ ) {
		return Stream.of(sets_)
			.map(this::cast)
			.map(SetSampleExpression::getSample)
			.reduce(this::intersect)
			.map(SetSampleExpression::new)
			.map(this::<E_>cast)
			.get();
	}

	@Override
	public < E_ > Set<E_> intersect( Set<E_> set1_, Set<E_> set2_ ) {
		java.util.Set<E> sam1 = cast(set1_).getSample();
		java.util.Set<E> sam2 = cast(set2_).getSample();
		java.util.Set<E> sam12 = intersect(sam1, sam2);
		return cast(new SetSampleExpression<E>(sam12));
	}

	protected java.util.Set<E> intersect( java.util.Set<E> sam1, java.util.Set<E> sam2 ) {
		java.util.Set<E> sam12 = new java.util.HashSet<E>(sam1);
		sam12.retainAll(sam2);
		return sam12;
	}

	@Override
	public < E_ > Set<E_> complement( Set<E_> set1_, Set<E_> set2_ ) {
		java.util.Set<E> sam1 = cast(set1_).getSample();
		java.util.Set<E> sam2 = cast(set2_).getSample();
		java.util.Set<E> sam12 = new java.util.HashSet<E>(sam1);
		sam12.removeAll(sam2);
		return cast(new SetSampleExpression<E>(sam12));
	}

	@Override
	public < E_ > Set<E_> complement( Set<E_> set_ ) {
		java.util.Set<E> sam = cast(set_).getSample();
		java.util.Set<E> sam_ = new java.util.HashSet<E>(this.samples);
		sam_.removeAll(sam);
		return cast(new SetSampleExpression<E>(sam_));
	}

	@Override
	public < E_ > boolean isEmpty( Set<E_> set_ ) {
		return cast(set_).getSample().isEmpty();
	}

	@Override
	public < E_ > boolean isUniversal( Set<E_> set_ ) {
		java.util.Set<E> sam = cast(set_).getSample();
		return sam.containsAll(this.samples);
	}

	@Override
	public < E_ > boolean equals( Set<E_> set1_, Set<E_> set2_ ) {
		java.util.Set<E> sam1 = cast(set1_).getSample();
		java.util.Set<E> sam2 = cast(set2_).getSample();
		return sam1.equals(sam2);
	}
}
