package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public class CompositeRegionBasicEngine extends CompositeEngine<Region> implements RegionBasicEngine
{
	protected java.util.List<Engine<? extends Region>> engines;

	public CompositeRegionBasicEngine() {
		engines = new java.util.LinkedList<>();
	}

	public CompositeRegionBasicEngine( java.util.List<Engine<? extends Region>> eng ) {
		engines = eng;
	}

	public CompositeRegionBasicEngine clone() {
		return new CompositeRegionBasicEngine(new java.util.LinkedList<>(engines));
	}

	public void add( Engine<? extends Region> engine ) {
		engines.add(engine);
	}

	public void add( int index, Engine<? extends Region> engine ) {
		engines.add(index, engine);
	}


	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicCreator ) {

			try {
				return ((RegionBasicCreator)engine).createRegionByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createUniversalRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicCreator ) {

			try {
				return ((RegionBasicCreator)engine).createUniversalRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createEmptyRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicCreator ) {

			try {
				return ((RegionBasicCreator)engine).createEmptyRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public boolean contains( Region reg, Number[] point ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicAttribute ) {

			try {
				return ((RegionBasicAttribute)engine).contains(reg,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Region intersect( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicOperator ) {

			try {
				return ((RegionBasicOperator)engine).intersect(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region union( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicOperator ) {

			try {
				return ((RegionBasicOperator)engine).union(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicOperator ) {

			try {
				return ((RegionBasicOperator)engine).complement(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicOperator ) {

			try {
				return ((RegionBasicOperator)engine).complement(reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region transformsBy( Region reg, Transformation trans ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicOperator ) {

			try {
				return ((RegionBasicOperator)engine).transformsBy(reg,trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isEmpty( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicPredicate ) {

			try {
				return ((RegionBasicPredicate)engine).isEmpty(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isUniversal( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicPredicate ) {

			try {
				return ((RegionBasicPredicate)engine).isUniversal(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean containsAll( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicPredicate ) {

			try {
				return ((RegionBasicPredicate)engine).containsAll(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasicPredicate ) {

			try {
				return ((RegionBasicPredicate)engine).equals(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

}
