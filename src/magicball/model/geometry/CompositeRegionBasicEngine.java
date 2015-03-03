package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public class CompositeRegionBasicEngine extends DefaultCompositeEngine<Region> implements RegionBasicEngine
{
	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionCreator ) {

			try {
				return ((RegionCreator)engine).createRegionByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createUniversalRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionCreator ) {

			try {
				return ((RegionCreator)engine).createUniversalRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createEmptyRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionCreator ) {

			try {
				return ((RegionCreator)engine).createEmptyRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public boolean contains( Region reg, Number[] point ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBehavior ) {

			try {
				return ((RegionBehavior)engine).contains(reg,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Region intersect( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionOperator ) {

			try {
				return ((RegionOperator)engine).intersect(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region union( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionOperator ) {

			try {
				return ((RegionOperator)engine).union(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionOperator ) {

			try {
				return ((RegionOperator)engine).complement(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionOperator ) {

			try {
				return ((RegionOperator)engine).complement(reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region transformsBy( Region reg, Transformation trans ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionOperator ) {

			try {
				return ((RegionOperator)engine).transformsBy(reg,trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isEmpty( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionPredicate ) {

			try {
				return ((RegionPredicate)engine).isEmpty(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isUniversal( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionPredicate ) {

			try {
				return ((RegionPredicate)engine).isUniversal(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean containsAll( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionPredicate ) {

			try {
				return ((RegionPredicate)engine).containsAll(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionPredicate ) {

			try {
				return ((RegionPredicate)engine).equals(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

}
