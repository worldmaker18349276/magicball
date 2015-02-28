package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on lambda expression
public class FunctionBasicPredicateWithSampleAlgorithm < E > implements FunctionBasicPredicate, SpecEnigne<Function,Function>
{
	protected java.util.Set<E> samples;
	private FunctionBasicAttribute funcAttribute;

	public FunctionBasicPredicateWithSampleAlgorithm( java.util.Set<E> sam ) {
		super();
		this.samples = sam;
	}

	public FunctionBasicPredicateWithSampleAlgorithm( java.util.Set<E> sam, FunctionBasicAttribute funcAttr ) {
		super();
		this.samples = sam;
		setEngine(funcAttr);
	}


	public void setEngine( FunctionBasicAttribute funcAttr ) {
		funcAttribute = funcAttr;
	}


	// operator
	@SuppressWarnings({"unchecked"})
	private < O > Function<E,O> castToE( Function<?,O> func ) {
		try {
			return (Function<E,O>) func;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(func.getClass());
		}
	}

	@Override
	public < I, O > boolean equals( Function<I,O> func1_, Function<I,O> func2_ ) {
		Function<E,O> func1 = this.castToE(func1_);
		Function<E,O> func2 = this.castToE(func2_);
		return samples.stream()
			.allMatch(e -> funcAttribute.applies(func1,e).equals(funcAttribute.applies(func2,e)));
	}

	@Override
	public < I, O > boolean isAlwaysEqualTo( Function<I,O> func_, O value ) {
		Function<E,O> func = this.castToE(func_);
		return samples.stream()
			.map(e -> funcAttribute.applies(func,e))
			.allMatch(value::equals);
	}
}
