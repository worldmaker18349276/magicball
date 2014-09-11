package magicball.model.math;


public class SetBasicEngine
{
	public < E > Set<E> union( final Set<E>[] sets ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				for ( Set<E> set : sets )
					if ( set.isElement(element) )
						return true;
				return false;
			}
		};
	}

	public < E > Set<E> intersect( final Set<E>[] sets ) {
		return new Set<E>() {
			public boolean isElement( E element ) {
				for ( Set<E> set : sets )
					if ( !set.isElement(element) )
						return false;
				return true;
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

	public < E > Set<E> createUniverseSet() {
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
}
