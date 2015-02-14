package magicball.model.math.basic;

import java.util.stream.*;

import magicball.model.*;
import magicball.model.math.*;


// base on function expression
public class SetBasicEngine implements SetEngine
{
	protected FunctionEngine funcEngine;

	public SetBasicEngine( FunctionEngine funcEng ) {
		this.funcEngine = funcEng;
	}

	public SetBasicEngine( EngineProvider provider ) {
		this.funcEngine = provider.getFunctionEngine();
	}

	@Override
	public SetBasicEngine clone() {
		return new SetBasicEngine(this.funcEngine);
	}

	protected < E > SetFunctionExpression<E> cast( Set<E> set ) {
		try {
			return (SetFunctionExpression<E>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(set.getClass());
		}
	}

	// creater
	@Override
	public < E > Set<E> createSetByFunction( Function<E,Boolean> func ) {
		return new SetFunctionExpression<E>(func);
	}

	@Override
	public < E > Set<E> createEmptySet() {
		return createSetByFunction(this.funcEngine.<E,Boolean>createConstantFunction(false));
	}

	@Override
	public < E > Set<E> createUniversalSet() {
		return createSetByFunction(this.funcEngine.<E,Boolean>createConstantFunction(true));
	}


	// attribute
	@Override
	public < E > Function<E,Boolean> getIntensionFunction( Set<E> set_ ) {
		SetFunctionExpression<E> set = cast(set_);
		return set.getFunction();
	}

	private < E > Function<E,Boolean> function( Set<E> set_ ) {
		return getIntensionFunction(set_);
	}

	@Override
	public < E > boolean contains( Set<E> set, E e ) {
		return this.funcEngine.applies(function(set),e);
	}

	@Override
	public < E > boolean containsAll( Set<E> set1, Set<E> set2 ) {
		return this.funcEngine.implies(function(set2),function(set1));
	}


	// operator
	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < E > Set<E> union( Set<E>... sets ) {
		return Stream.of(sets)
			.map(this::function)
			.reduce(this.funcEngine::or)
			.map(this::createSetByFunction)
			.get();
	}

	@Override
	public < E > Set<E> union( Set<E> set1, Set<E> set2 ) {
		return createSetByFunction( funcEngine.or(function(set1),function(set2)) );
	}

	@Override
	@SuppressWarnings({"unchecked", "varargs"})
	public < E > Set<E> intersect( Set<E>... sets ) {
		return Stream.of(sets)
			.map(this::function)
			.reduce(this.funcEngine::and)
			.map(this::createSetByFunction)
			.get();
	}

	@Override
	public < E > Set<E> intersect( Set<E> set1, Set<E> set2 ) {
		return createSetByFunction( funcEngine.and(function(set1),function(set2)) );
	}

	@Override
	public < E > Set<E> complement( Set<E> set1, Set<E> set2 ) {
		return createSetByFunction( funcEngine.not(function(set1),function(set2)) );
	}

	@Override
	public < E > Set<E> complement( Set<E> set ) {
		return createSetByFunction( funcEngine.not(function(set)) );
	}

	@Override
	public < E > boolean isEmpty( Set<E> set ) {
		return funcEngine.isAlwaysFalse(function(set));
	}

	@Override
	public < E > boolean isUniversal( Set<E> set ) {
		return funcEngine.isAlwaysTrue(function(set));
	}

	@Override
	public < E > boolean equals( Set<E> set1, Set<E> set2 ) {
		return funcEngine.equals(function(set1), function(set2));
	}
}
