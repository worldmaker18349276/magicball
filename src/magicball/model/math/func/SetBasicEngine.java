package magicball.model.math.func;

import java.util.Arrays;
import magicball.model.*;
import magicball.model.math.*;

public class SetBasicEngine implements SetEngine
{
	protected FunctionEngine funcEngine;

	public SetBasicEngine( FunctionEngine funcEng ) {
		this.funcEngine = funcEng;
	}

	protected < E > SetFunctionExpression<E> cast( Set<E> set ) {
		try {
			return (SetFunctionExpression<E>) set;
		} catch ( ClassCastException e ) {
			throw new UnsupportedExpressionException(set.getClass());
		}
	}

	@SafeVarargs
	final public < E > Set<E> union( final Set<E>... sets ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				for ( Set<E> set : sets )
					if ( setEng.contains(set,element) )
						return true;
				return false;
			}
		}));
	}

	public < E > Set<E> union( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				return setEng.contains(set1,element) || setEng.contains(set2,element);
			}
		}));
	}

	@SafeVarargs
	final public < E > Set<E> intersect( final Set<E>... sets ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				for ( Set<E> set : sets )
					if ( !setEng.contains(set,element) )
						return false;
				return true;
			}
		}));
	}

	public < E > Set<E> intersect( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				return setEng.contains(set1,element) && setEng.contains(set2,element);
			}
		}));
	}

	public < E > Set<E> complement( final Set<E> set1, final Set<E> set2 ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				if ( setEng.contains(set2,element) )
					return false;
				return setEng.contains(set1,element);
			}
		}));
	}

	public < E > Set<E> complement( final Set<E> set ) {
		final SetEngine setEng = this;
		return createSetByFunction(this.funcEngine.createFunctionByLambda(new LambdaFunction<E,Boolean>() {
			public Boolean apply( E element ) {
				return !setEng.contains(set,element);
			}
		}));
	}

	public < E > Set<E> createEmptySet() {
		return createSetByFunction(this.funcEngine.<E,Boolean>createConstantFunction(false));
	}

	public < E > Set<E> createUniversalSet() {
		return createSetByFunction(this.funcEngine.<E,Boolean>createConstantFunction(true));
	}

	public < E > Set<E> createSetByFunction( Function<E,Boolean> func ) {
		return new SetFunctionExpression<E>(func);
	}

	public < E > Set<E> createSetByLambda( LambdaFunction<E,Boolean> lambda ) {
		return new SetFunctionExpression<E>(this.funcEngine.createFunctionByLambda(lambda));
	}

	public < E > boolean isEmpty( Set<E> set ) {
		throw new UnsupportedAlgorithmException();
	}
	public < E > boolean isUniversal( Set<E> set ) {
		throw new UnsupportedAlgorithmException();
	}
	public < E > boolean equals( Set<E> set1, Set<E> set2 ) {
		throw new UnsupportedAlgorithmException();
	}
	public < E > boolean containsAll( Set<E> set1, Set<E> set2 ) {
		throw new UnsupportedAlgorithmException();
	}

	public < E > boolean contains( Set<E> set, E e ) {
		return this.funcEngine.applies(cast(set).getFunction(),e);
	}
}
