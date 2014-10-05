package magicball.basic.math;

import java.util.Arrays;
import magicball.model.*;
import magicball.model.math.*;

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

	@Override
	public < E > boolean contains( Set<E> set, E e ) {
		return this.funcEngine.applies(getIntensionFunction(set),e);
	}

	@Override
	public < E > boolean containsAll( Set<E> set1, Set<E> set2 ) {
		throw new UnsupportedAlgorithmException();
	}


	// operator
	@Override
	@SafeVarargs
	final public < E > Set<E> union( final Set<E>... sets ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				for ( Set<E> set : sets )
					if ( setEng.contains(set,element) )
						return true;
				return false;
			}
		}));
	}

	@Override
	public < E > Set<E> union( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				return setEng.contains(set1,element) || setEng.contains(set2,element);
			}
		}));
	}

	@Override
	@SafeVarargs
	final public < E > Set<E> intersect( final Set<E>... sets ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				for ( Set<E> set : sets )
					if ( !setEng.contains(set,element) )
						return false;
				return true;
			}
		}));
	}

	@Override
	public < E > Set<E> intersect( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				return setEng.contains(set1,element) && setEng.contains(set2,element);
			}
		}));
	}

	@Override
	public < E > Set<E> complement( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				if ( setEng.contains(set2,element) )
					return false;
				return setEng.contains(set1,element);
			}
		}));
	}

	@Override
	public < E > Set<E> complement( final Set<E> set ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.function(new LambdaFunction<E,Boolean>() {
			@Override
			public Boolean apply( E element ) {
				return !setEng.contains(set,element);
			}
		}));
	}

	@Override
	public < E > boolean isEmpty( Set<E> set ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < E > boolean isUniversal( Set<E> set ) {
		throw new UnsupportedAlgorithmException();
	}

	@Override
	public < E > boolean equals( Set<E> set1, Set<E> set2 ) {
		throw new UnsupportedAlgorithmException();
	}
}
