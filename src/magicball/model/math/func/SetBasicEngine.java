package magicball.model.math.func;

import magicball.model.*;
import magicball.model.math.*;

public class SetBasicEngine implements SetEngine
{
	@SafeVarargs
	final public < E > Set<E> union( final Set<E>... sets ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				for ( Set<E> set : sets )
					if ( set.isElement(element) )
						return true;
				return false;
			}
		};
	}

	public < E > Set<E> union( final Set<E> set1, final Set<E> set2 ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return set1.isElement(element) || set2.isElement(element);
			}
		};
	}

	@SafeVarargs
	final public < E > Set<E> intersect( final Set<E>... sets ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				for ( Set<E> set : sets )
					if ( !set.isElement(element) )
						return false;
				return true;
			}
		};
	}

	public < E > Set<E> intersect( final Set<E> set1, final Set<E> set2 ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return set1.isElement(element) && set2.isElement(element);
			}
		};
	}

	public < E > Set<E> complement( final Set<E> set1, final Set<E> set2 ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				if ( set2.isElement(element) )
					return false;
				return set1.isElement(element);
			}
		};
	}

	public < E > Set<E> complement( final Set<E> set2 ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return !set2.isElement(element);
			}
		};
	}

	public < E > Set<E> createEmptySet() {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return false;
			}
		};
	}

	public < E > Set<E> createUniversalSet() {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return true;
			}
		};
	}

	public < E > Set<E> createSetByIntensionalDefinition( final Function<E,Boolean> func ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				return func.apply(element);
			}
		};
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
		return set.isElement(e);
	}
}
