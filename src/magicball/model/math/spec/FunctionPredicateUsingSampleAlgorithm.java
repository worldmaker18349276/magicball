package magicball.model.math.spec;

import java.util.function.BiPredicate;
import java.util.Set;

import magicball.model.*;
import magicball.model.math.*;


public class FunctionPredicateUsingSampleAlgorithm < E, R > implements SpecEngine<Func,Func>,
		ArbitraryFunctionBasicProperty.Predicate
{
	protected Set<E> samples;
	protected BiPredicate<R,R> comparator;
	protected ArbitraryFunctionBasicProperty.Behavior funcBehavior;


	public FunctionPredicateUsingSampleAlgorithm( Set<E> sam, BiPredicate<R,R> comp ) {
		this.samples = sam;
		this.comparator = comp;
	}

	public FunctionPredicateUsingSampleAlgorithm( Set<E> sam, BiPredicate<R,R> comp,
			ArbitraryFunctionBasicProperty.Behavior funcB ) {
		this.samples = sam;
		this.comparator = comp;
		setEngine(funcB);
	}


	public void setEngine( ArbitraryFunctionBasicProperty.Behavior funcB ) {
		funcBehavior = funcB;
	}


	// operator
	@SuppressWarnings({"unchecked"})
	private Func<E,R> castToER( Func<?,?> func ) {
		try {
			return (Func<E,R>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	@Override
	public < I, O > boolean equals( Func<I,O> func1_, Func<I,O> func2_ ) {
		Func<E,R> func1 = this.castToER(func1_);
		Func<E,R> func2 = this.castToER(func2_);
		return samples.stream()
			.allMatch(e -> comparator.test(
					funcBehavior.applyTo(func1,e),
					funcBehavior.applyTo(func2,e)));
	}

	@Override
	public < I, O > boolean isInvertible( Func<I,O> func_ ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	@SuppressWarnings("unchecked")
	public < I, O > boolean isIdentityFunction( Func<I,O> func_ ) {
		Func<E,R> func = this.castToER(func_);
		try {
			return samples.stream()
				.allMatch(e -> comparator.test(funcBehavior.applyTo(func,e),(R)e));
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func_.getClass());
		}
	}

	@Override
	public < I, O > boolean isConstantFunction( Func<I,O> func_ ) {
		Func<E,R> func = this.castToER(func_);
		R value = funcBehavior.applyTo(func, samples.iterator().next());
		return samples.stream()
			.map(e -> funcBehavior.applyTo(func,e))
			.allMatch(r -> comparator.test(value,r));
	}

	@Override
	@SuppressWarnings("unchecked")
	public < I, O > boolean isAlwaysEqualTo( Func<I,O> func_, O value ) {
		Func<E,R> func = this.castToER(func_);
		return samples.stream()
			.map(e -> funcBehavior.applyTo(func,e))
			.allMatch(r -> comparator.test((R)value,r));
	}
}
